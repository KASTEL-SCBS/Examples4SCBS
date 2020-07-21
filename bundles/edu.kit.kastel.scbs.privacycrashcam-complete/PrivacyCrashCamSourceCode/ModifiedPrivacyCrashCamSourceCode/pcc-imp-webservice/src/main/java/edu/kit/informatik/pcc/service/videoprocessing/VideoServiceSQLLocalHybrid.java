package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.core.crypto.IVideoDecryptor;
import edu.kit.informatik.pcc.core.data.IFileHierarchyManager;
import edu.kit.informatik.pcc.core.data.IFileManager;
import edu.kit.informatik.pcc.service.accesscontrol.AccessControl;
import edu.kit.informatik.pcc.service.server.Main;

public class VideoServiceSQLLocalHybrid extends VideoService{


	private VideoServiceSQLAccess sqlAccess;
	
	public VideoServiceSQLLocalHybrid(IFileHierarchyManager fileHierarchyManager, IFileManager temporaryFileManager,
                                      IAsyncVideoProcessor asyncVideoProcessor, AccessControl rbac, IVideoDecryptor videoDecryptor) {
		super(fileHierarchyManager, temporaryFileManager, asyncVideoProcessor, rbac, videoDecryptor);
		this.sqlAccess = new VideoServiceSQLAccess();
	}

	@Override
	public int[] getVideoIds(int userId) {
		ArrayList<Integer> tmpVideoIds = new ArrayList<Integer>();
		int[] videoIds = new int[0];
		
		 
			Connection connection = connectToDatabase();
			
			if(connection == null) {
				return null;
			}
			
			try{
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select \"id\" from \"video\" where \"video\".\"user_id\"='" + userId + "'");
				
				while(rs.next()) {
					tmpVideoIds.add(new Integer(rs.getInt("id")));
				}
		        stmt.close();
		        connection.close();
			} catch (SQLException | NullPointerException e) {
	            Logger.getGlobal().warning("SQL ");
	            return null;
	        }
			
			
			videoIds = new int[tmpVideoIds.size()];

			for(int i = 0; i < videoIds.length; i++) {
				videoIds[i] = tmpVideoIds.get(i);
			}

			return videoIds;
	}

	@Override
	public void deleteVideo(int videoId, int userId) {
		assertCompletelySetup();
		super.getFileHierarchyManager().deleteFile(getVideo(videoId, userId, userId, "ANONYMIZED"));
		super.getFileHierarchyManager().deleteFile(getVideo(videoId, userId, userId, "UNANONYMIZED"));
		super.getFileHierarchyManager().deleteFile(getMetadata(videoId,userId, userId));
		super.getFileHierarchyManager().deleteFile(getEncKey(videoId, userId));
		

		Connection connection = connectToDatabase();

		if (connection == null) {
			return;
		}
        try {
            Statement stmt = connection.createStatement();
            String sql = "delete from \"video\" where \"video\".\"id\"='" + videoId + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } 
        catch (NullPointerException | SQLException e) {
            Logger.getGlobal().warning("Error while deleting account in database");
        }
	}

	@Override
	public File getVideo(int videoId, int videoOwnerId, int accessorId, String videoType) {
		assertCompletelySetup();
		File video = null;
		switch(videoType) {
			case ANONYMIZED:
				video = super.getFileHierarchyManager().getExistingFile(super.videoFileName(videoId), super.videoDirectory(videoOwnerId));
				break;
			case UNANONYMIZED:
				
					video = super.getTemporaryFileManager().getOrCreateFile("decVid.mp4");
					File encVid = super.getFileHierarchyManager().getExistingFile(videoFileName(videoId), encVideoDirectory(videoOwnerId));
					File encKey = super.getFileHierarchyManager().getExistingFile(encKeyFileName(videoId), encKeyDirectory(videoOwnerId));
					try {
						video = super.getTemporaryFileManager().getOrCreateFile(UUID.randomUUID().toString());
						super.getVideoDecryptor().decryptVideo(encVid, Files.readAllBytes(encKey.toPath()), video);
					} catch (IOException e) {
						Logger.getGlobal().severe("Could not decrypt Encrypted Video");
					}
					
				
				break;
		}
		return video;
	}
	


	@Override
	public boolean postVideo(File encryptedVideo, File encryptedMetadata, File encryptedKey, int userId) {
		assertCompletelySetup();
		if (encryptedVideo == null || encryptedMetadata == null || 
				encryptedKey == null) {
			return false;
		}
		
		int newVideoId = generateUnusedVideoId(userId);
		
		File tempAnonymizedVideo = super.getTemporaryFileManager().getOrCreateFile(UUID.randomUUID().toString());
		File tempMetadata = super.getTemporaryFileManager().getOrCreateFile(UUID.randomUUID().toString());
		File tempDecryptedVideo = super.getTemporaryFileManager().getOrCreateFile(UUID.randomUUID().toString());
		
		VideoProcessingContext context = new VideoProcessingContext(encryptedVideo, encryptedMetadata, encryptedKey, tempAnonymizedVideo, tempMetadata,tempDecryptedVideo,newVideoId, userId);
		
		
		super.getAsyncVideoProcessor().processVideo(context);
		
		return true;
	}

	@Override
	public int[] getAllVideoIds(Collection<Integer> userIds, int accessorId) {
		
		ArrayList<Integer> tmpVideoIds = new ArrayList<Integer>();
		int[] videoIds = new int[0];
		
		if(super.getAccessControl().canAccessAllVideoInformation(accessorId)) {
			Connection connection = connectToDatabase();
			
			if(connection == null) {
				return null;
			}
			
			try{
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select \"id\" from \"video\" ");
				
				while(rs.next()) {
					tmpVideoIds.add(new Integer(rs.getInt("id")));
				}
		        stmt.close();
		        connection.close();
			} catch (SQLException | NullPointerException e) {
	            Logger.getGlobal().warning("SQL ");
	            return null;
	        }
			
			
			videoIds = new int[tmpVideoIds.size()];

			for(int i = 0; i < videoIds.length; i++) {
				videoIds[i] = tmpVideoIds.get(i);
			}

			return videoIds;
		}
		
		return null;
	}
	@Override
	public int getUserIdForVideoId(int videoId, int accessorId) {
		int userId = -1;
		if(super.getAccessControl().canAccessAllVideoInformation(accessorId)) {
			Connection connection = connectToDatabase();
			
			if(connection == null) {
				return userId;
			}
			
			try{
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select \"user_id\" from \"video\" where \"video\".\"id\" = '"+ videoId+ "'");
				
				while(rs.next()) {
					userId = rs.getInt("user_id");
				}
		        stmt.close();
		        connection.close();
			} catch (SQLException | NullPointerException e) {
	            Logger.getGlobal().warning("SQL ");
	            return -1;
	        }
		}
		
		return userId;
	}

	@Override
	public File getMetadata(int videoId, int targetUserId, int accessorId) {
		
		assertCompletelySetup();
		if(super.getAccessControl().canAccessAllVideoInformation(accessorId)) {
			return super.getFileHierarchyManager().getExistingFile(metaDataFileName(videoId), metadataDirectory(targetUserId));
		}
		return null;
	}
	
	   public static final String PORT = "5432";
	   public static final String HOST = "localhost";
	   public static final String DB_NAME = "PrivacyCrashCam";
	   public static final String USER = "demonstrator";
	   public static final String PASSWORD = "pccdata";
	   
		
		/**
	     * This method is used to open a connection to the database with the class attributes.
	     * If the connection fails, the server is shut down.
	     * <p>
	     * IMPORTANT: A Connection will be opened, but not closed. After calling this method,
	     * you have to close the returned connection.
	     * </p>
	     */
	    protected Connection connectToDatabase() {
	        try {
	            Class.forName("org.postgresql.Driver");
	            return DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME, USER, PASSWORD);
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            Logger.getGlobal().severe("No connection to database!");
	            Main.stopServer();
	            return null;
	        }
	    }
	    
	    @Override
	    protected int generateUnusedVideoId(int userId) {
	    	boolean found = false;
	    	int videoId;
	    	Random rndm = new Random();
	    	
	    	while(!found) {
	    		videoId = Math.abs(rndm.nextInt());
	    		if(!sqlAccess.videoIdOccupied(videoId)) {
	    			return videoId;
	    		}
	    	}
	    	return -1;
	    }
	    
}
