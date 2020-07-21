package edu.kit.informatik.pcc.service.server;

import edu.kit.informatik.pcc.core.crypto.HybridVideoDecryptor;
import edu.kit.informatik.pcc.core.crypto.KeyStorage;
import edu.kit.informatik.pcc.core.crypto.VideoDecryptor;
import edu.kit.informatik.pcc.core.data.FileSystemManager;
import edu.kit.informatik.pcc.service.accesscontrol.AccessControl;
import edu.kit.informatik.pcc.service.authentication.UserSQLDB;
import edu.kit.informatik.pcc.service.authentication.UserService;
import edu.kit.informatik.pcc.service.data.LocationConfig;
import edu.kit.informatik.pcc.service.videoprocessing.EncryptedEnhancedPersistor;
import edu.kit.informatik.pcc.service.videoprocessing.IVideoProcessor;
import edu.kit.informatik.pcc.service.videoprocessing.VideoDataPersistor;
import edu.kit.informatik.pcc.service.videoprocessing.VideoChainProcessor;
import edu.kit.informatik.pcc.service.videoprocessing.VideoProcessingManager;
import edu.kit.informatik.pcc.service.videoprocessing.VideoService;
import edu.kit.informatik.pcc.service.videoprocessing.VideoServiceSQLAccess;
import edu.kit.informatik.pcc.service.videoprocessing.VideoServiceSQLLocalHybrid;
import edu.kit.informatik.pcc.service.videoprocessing.opencv.OpenCVAnonymizer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.*;

/**
 * Main entry point for the webserver.
 * Starts the server and sets up proxy for handling requests as well as
 * video processing manager for processing videos.
 *
 * @author Josh Romanowski, Fabian Wenzel
 */
public class Main {

    // constants
    private static final int PORT = 2222;
    private static final String REQUEST_LOCATION = "edu.kit.informatik.pcc.service.server";

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Server instance used for http access.
     */
    private static Server server;

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    /**
     * Sets the server up and starts it.
     *
     * @param args no args
     */
    public static void main(String[] args) {
    	setupComponents();
        startServer();
    }

    /**
     * Stops the server if it is still running. Also shuts down the video processing chain.
     */
    public static void stopServer() {

        if (server.isStopped()) {
            Logger.getGlobal().info("Server is already stopped");
            return;
        }

        Logger.getGlobal().info("Stopping Server");

        try {
            // shutdown server
            server.stop();
        } catch (Exception e) {
            Logger.getGlobal().warning("Stopping the server failed.");
        }

        // finish log
        for (Handler handler : Logger.getGlobal().getHandlers()) {
            handler.close();
        }
    }

    /* #############################################################################################
     *                                  helper methods
     * ###########################################################################################*/

    /**
     * Starts the server and sets up the proxy to handle requests.
     * Also creates all necessary directories for the server and sets up the logger.
     *
     * @return Returns if starting the server is successful
     */
    private static boolean startServer() {

        if (!setupDirectories() || !setupLogger()) {
            System.out.println("Setup failed");
            return false;
        }

        Logger.getGlobal().info("Starting Server");
        ResourceConfig config = new ResourceConfig();
        config.packages(REQUEST_LOCATION); //where to search for rest requests
        config.register(MultiPartFeature.class); //register feature for file upload (multipartfeature)
        ServletHolder servlet = new ServletHolder(new ServletContainer(config)); // add the config to the servletholder

        server = new Server(PORT);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");

        if (server.isStarted()) {
            Logger.getGlobal().info("Server already started");
            return true;
        }

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            Logger.getGlobal().warning("Server was interrupted during execution");
            return false;
        } catch (Exception e) {
            Logger.getGlobal().severe("Error in server");
            stopServer();
            return false;
        }
        return true;
    }

    /**
     * Sets up the logger so that there is one handling writing error logs and one
     * handler writing the full log.
     *
     * @return Returns whether setting up the logger was successful or not.
     */
    private static boolean setupLogger() {
        Logger logger = Logger.getGlobal();
        try {
            // output file = "error.log", max size = 1 MByte, 1 single logfile
            Handler fileErrorHandler = new FileHandler(
                    LocationConfig.LOG_DIR + File.separator + "error.log", 1024000, 1);
            fileErrorHandler.setFormatter(new SimpleFormatter());
            fileErrorHandler.setLevel(Level.WARNING);

            Handler fileInfoHandler = new FileHandler(
                    LocationConfig.LOG_DIR + File.separator + "server.log", 1024000, 1);
            fileInfoHandler.setFormatter(new SimpleFormatter());
            fileInfoHandler.setLevel(Level.INFO);

            logger.addHandler(fileInfoHandler);
            logger.addHandler(fileErrorHandler);
        } catch (SecurityException | IOException e) {
            return false;
        }
        logger.info("Logger setup");
        return true;
    }

    /**
     * Creates all necessary directions as long as they were not existing before.
     *
     * @return Returns whether setting was successful or not.
     */
    private static boolean setupDirectories() {
        boolean ret = true;

        File[] dirs = new File[]{
                new File(LocationConfig.LOG_DIR)
        };

        for (File dir : dirs) {
            if (!dir.exists()) {
                ret &= dir.mkdir();
            }
        }

        return ret;
    }
    
    private static void setupComponents() {
    	deleteDirectoryAndItsContent(new File(LocationConfig.TEMP_DIR));
    	
    	FileSystemManager keyManager = new FileSystemManager(LocationConfig.KEY_DIR);
    	FileSystemManager temporaryFilesManager = new FileSystemManager(LocationConfig.TEMP_DIR);
    	FileSystemManager videoFilesManager = new FileSystemManager(LocationConfig.DATA_DIR);
    	copyKeysIfNeeded(keyManager);
    	
    	
    	AccessControl rbac = new AccessControl();
    
    	UserSQLDB userSQLDB = new UserSQLDB();
    	UserService userService = new UserService(userSQLDB, userSQLDB, rbac);
    	
    	HybridVideoDecryptor hybridVideoDecryptor = new HybridVideoDecryptor(keyManager);
    
    	OpenCVAnonymizer openCVAnonymizer = new OpenCVAnonymizer();
    	VideoChainProcessor videoChainProcessor = new VideoChainProcessor(temporaryFilesManager, new IVideoProcessor[]{openCVAnonymizer});
    	
    	VideoDataPersistor persistor = new EncryptedEnhancedPersistor(temporaryFilesManager, videoFilesManager, new VideoServiceSQLAccess());
    	VideoProcessingManager videoProcessingManager = new VideoProcessingManager(persistor, hybridVideoDecryptor, videoChainProcessor);

    	
    	VideoService videoService = new VideoServiceSQLLocalHybrid(videoFilesManager, temporaryFilesManager, videoProcessingManager, rbac , hybridVideoDecryptor);

    	AnonymizationService webService = new AnonymizationService(userService, userService, videoService, hybridVideoDecryptor);
 
    	AnonymizationService.setGlobal(webService);
    	VideoServerProxy.setTemporaryFileManager(temporaryFilesManager, webService);
    }
    
    private static void deleteDirectoryAndItsContent(File dir) {
    	if (dir == null || !dir.exists()) {
            return;
        }
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				deleteDirectoryAndItsContent(file);
			}
			else {
				file.delete();
			}
		}
		dir.delete();
	}
    
    private static void copyKeysIfNeeded(FileSystemManager keyFileManager) {
    	if (keyFileManager.getAllFilesInDirectory(null).length >= 2) {
    		return;
    	}
    	try (ObjectInputStream publicInputStream = new ObjectInputStream(Main.class.getClassLoader().getResourceAsStream("public.key"));
    		ObjectInputStream privateInputStream = new ObjectInputStream(Main.class.getClassLoader().getResourceAsStream("private.key")))
    	{
    		PublicKey publicKey = (PublicKey)publicInputStream.readObject();
    		PrivateKey privateKey = (PrivateKey)privateInputStream.readObject();
    		
    		KeyStorage keyStorage = new KeyStorage(keyFileManager);
    		keyStorage.setFileManager(keyFileManager);
    		keyStorage.storeKey(VideoDecryptor.publicKeyId, publicKey);
    		keyStorage.storeKey(VideoDecryptor.privateKeyId, privateKey);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			Logger.getGlobal().warning("Failed to copy keys to correct directory");
		}
    	
    }
}
