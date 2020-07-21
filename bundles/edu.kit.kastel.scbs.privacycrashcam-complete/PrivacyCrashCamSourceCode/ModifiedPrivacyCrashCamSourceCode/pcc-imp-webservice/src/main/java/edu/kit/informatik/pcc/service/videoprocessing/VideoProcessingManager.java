package edu.kit.informatik.pcc.service.videoprocessing;

import edu.kit.informatik.pcc.core.crypto.IVideoDecryptor;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Controller for editing videos. Takes editing requests and puts them into it's queue.
 * When resources are free it takes the taks and executes the processing.
 *
 * @author Josh Romanowski
 */
public class VideoProcessingManager implements IAsyncVideoProcessor {
	private IVideoDecryptor videoDecryptor;
	private IVideoProcessor videoProcessor;
	private VideoDataPersistor persistor;
	

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Size of the thread pool used.
     */
    private final static int POOL_SIZE = 4;
    /**
     * Maximum amount of accepted tasks.
     */
    private final static int QUEUE_SIZE = 10;

    /**
     * Executor that controls the execution of the tasks.
     */
    private ExecutorService executor;

    /* #############################################################################################
     *                                  constructors
     * ###########################################################################################*/

    /**
     * Sets up the queue and the executor. Defines what should happen if the queue is full
     * and a task is being inserted.
     */
//    public VideoProcessingManager() {
//        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(QUEUE_SIZE);
//        executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 30,
//                TimeUnit.SECONDS, queue, new RejectedExecutionHandler() {
//            @Override
//            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//            	((VideoTask)r).cleanupAfterFailure();
//                String errorMessage = "Inserting video in queue failed.";
//                if (executor.isShutdown()) {
//                    errorMessage += "Processing module is shut down.";
//                } else {
//                    errorMessage += "Queue is full.";
//                }
//
//                Logger.getGlobal().warning(errorMessage);
//            }
//        });
//    }
    
    public VideoProcessingManager(VideoDataPersistor persistor, IVideoDecryptor videoDecryptor, IVideoProcessor videoProcessor) {
    	this.persistor = persistor;
    	this.videoDecryptor = videoDecryptor;
    	this.videoProcessor = videoProcessor;
    	
    	  BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(QUEUE_SIZE);
          executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 30,
                  TimeUnit.SECONDS, queue, new RejectedExecutionHandler() {
              @Override
              public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
              	((VideoTask)r).cleanupAfterFailure();
                  String errorMessage = "Inserting video in queue failed.";
                  if (executor.isShutdown()) {
                      errorMessage += "Processing module is shut down.";
                  } else {
                      errorMessage += "Queue is full.";
                  }

                  Logger.getGlobal().warning(errorMessage);
              }
          });
    }

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    
	@Override
	public void processVideo(VideoProcessingContext context) {
		// TODO Auto-generated method stub
		assertCompletelySetup();
    	//copy input files to temp directory so that processing is independent of outside file handling
    	try {

    		VideoTask videoTask = new VideoTask(videoDecryptor, videoProcessor, persistor, context);
    		executor.execute(videoTask);
    	}
    	catch (IOException e) {
    		Logger.getGlobal().warning("Failed to process video " + context.getEncryptedVideo().getAbsolutePath());
    		return;
    	}
    	Logger.getGlobal().info("Inserted video " + context.getEncryptedVideo().getAbsolutePath() + " into queue.");
	}

    /**
     * Shuts down the queue. Waits 10 Seconds for termination of due tasks.
     */
    public void shutdown() {
        try {
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Logger.getGlobal().info("Got interrupted while waiting for shutdown");
        }
        Logger.getGlobal().info("Video processing manager stopped");
    }
    
    private void assertCompletelySetup() {
		assert persistor != null;
		assert videoDecryptor != null;
		assert videoProcessor != null;
	}
    
    private class VideoTask implements Runnable {
		private IVideoDecryptor videoDecryptor;
		private IVideoProcessor videoProcessor;
		private byte[] encryptedKeyData;
		private VideoProcessingContext context;
		private VideoDataPersistor persistor;
		
		
		private VideoTask(IVideoDecryptor videoDecryptor, IVideoProcessor videoProcessor, VideoDataPersistor persistor, VideoProcessingContext context) throws IOException {
			this.videoDecryptor = videoDecryptor;
			this.videoProcessor = videoProcessor;
			this.encryptedKeyData = context.getEncryptedKeyAsBytes();
			this.persistor = persistor;
			this.context = context;
		}
		
		@Override
	    public void run() {
	        Logger.getGlobal().info("Start editing video " + context.getEncryptedVideo().getAbsolutePath());

	        long startTime = System.currentTimeMillis();

	        
			videoDecryptor.decrypt(context.getEncryptedVideo(), context.getEncryptedMetadata(), encryptedKeyData, context.getDecryptedVideo(), context.getmetaData());
			
			
			
			Boolean success = videoProcessor.processVideo(context.getDecryptedVideo(), context.getAnonymizedVideo());
			if (!success) {
				persistor.clearProcessingContext(context);
			} else {
				persistor.persistVideoInformation(context);
				long duration = System.currentTimeMillis() - startTime;
		        Logger.getGlobal().info("Finished editing video "
		                + context.getEncryptedVideo().getAbsolutePath()
		                + ". It took " + (duration / 1000) + " seconds.");
				persistor.clearProcessingContext(context);
			
			}
	    }
		
		public void cleanupAfterFailure() {
			persistor.clearProcessingContext(context);
		}
	}


}
