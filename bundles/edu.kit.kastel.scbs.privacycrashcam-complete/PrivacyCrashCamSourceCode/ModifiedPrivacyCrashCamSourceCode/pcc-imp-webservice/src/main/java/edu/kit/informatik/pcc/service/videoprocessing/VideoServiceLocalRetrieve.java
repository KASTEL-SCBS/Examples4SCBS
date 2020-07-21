package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.service.accesscontrol.AccessControl;
import org.apache.commons.io.FilenameUtils;

import edu.kit.informatik.pcc.core.crypto.IVideoDecryptor;
import edu.kit.informatik.pcc.core.data.IFileHierarchyManager;
import edu.kit.informatik.pcc.core.data.IFileManager;

public class VideoServiceLocalRetrieve extends VideoService implements IVideoService {

	

	
	public VideoServiceLocalRetrieve(IFileHierarchyManager fileHierarchyManager, IFileManager temporaryFileManager,
									 IAsyncVideoProcessor asyncVideoProcessor, AccessControl rbac, IVideoDecryptor videoDecryptor) {
		super(fileHierarchyManager, temporaryFileManager, asyncVideoProcessor, rbac, videoDecryptor);
	}

	@Override
	public int[] getVideoIds(int userId) {
		assertCompletelySetup();
		File[] videos = super.getFileHierarchyManager().getAllFilesInDirectory(videoDirectory(userId));
		List<Integer> videoIdList = new ArrayList<Integer>();
		for (File video: videos) {
			try {
				int videoId = Integer.parseInt(FilenameUtils.removeExtension(video.getName()));
				videoIdList.add(new Integer(videoId));
			}
			catch (NumberFormatException e) {
				Logger.getGlobal().warning("got video with invalid name " + video + " for user " + userId);
			}
		}
		int[] videoIds = new int[videoIdList.size()];
		for (int i = 0; i < videoIdList.size(); i++) {
			videoIds[i] = videoIdList.get(i).intValue();
		}
		return videoIds;
	}

	@Override
	public int[] getAllVideoIds(Collection<Integer> userIds, int accessorId) {
		
		int[] tempVideos;
		int[] result = new int[0];
		
		if(!super.getAccessControl().canAccessAllVideoInformation(accessorId)) {
			return null;
		}
		
		for(Integer userId : userIds) {
			tempVideos = getVideoIds(userId);
			result = appendIntArrays(result, tempVideos);
		}
		
		return result;
		
	}
	
	private int[] appendIntArrays(int[] a, int[] b) {
		int[] appended = new int[a.length + b.length]; 
		
		int appendingLocation = 0;
		
		for(;appendingLocation < a.length; appendingLocation++) {
			appended[appendingLocation] = a[appendingLocation];  
		}
		
		for(; appendingLocation < a.length + b.length; appendingLocation++) {
			appended[appendingLocation] = b[appendingLocation - a.length];
		}
		
		return appended;
		
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


	public File getVideo(int videoId, int userId, String videoType) {
		assertCompletelySetup();
		File video = null;
		switch(videoType) {
			case ANONYMIZED:
				video = super.getFileHierarchyManager().getExistingFile(videoFileName(videoId), videoDirectory(userId));
				break;
			case UNANONYMIZED:
				if(super.getAccessControl().canAccessUnanonymized(userId)) {
					video = super.getTemporaryFileManager().getOrCreateFile("decVid.mp4");
					File encVid = super.getFileHierarchyManager().getExistingFile(videoFileName(videoId), encVideoDirectory(userId));
					File encKey = super.getFileHierarchyManager().getExistingFile(encKeyFileName(videoId), encKeyDirectory(userId));
					try {
						video = super.getTemporaryFileManager().getOrCreateFile(UUID.randomUUID().toString());
						super.getVideoDecryptor().decryptVideo(encVid, Files.readAllBytes(encKey.toPath()), video);
					} catch (IOException e) {
						Logger.getGlobal().severe("Could not decrypt Encrypted Video");
					}
					
				}
				break;
		}
		return video;
	}
	
	
	@Override
	public void deleteVideo(int videoId, int userId) {
		assertCompletelySetup();
		super.getFileHierarchyManager().deleteFile(getVideo(videoId, userId, "ANONYMIZED"));
		super.getFileHierarchyManager().deleteFile(getVideo(videoId, userId, "UNANONYMIZED"));
		super.getFileHierarchyManager().deleteFile(getMetadata(videoId,userId, userId));
		super.getFileHierarchyManager().deleteFile(getEncKey(videoId, userId));
	}
	

	



	@Override
	public File getVideo(int videoId, int videoOwnerId, int accessorId, String videoType) {
		assertCompletelySetup();
		File video = null;
		switch(videoType) {
			case ANONYMIZED:
				video = super.getFileHierarchyManager().getExistingFile(videoFileName(videoId), videoDirectory(videoOwnerId));
				break;
			case UNANONYMIZED:
				if(super.getAccessControl().canAccessUnanonymized(accessorId)) {
					video = super.getTemporaryFileManager().getOrCreateFile("decVid.mp4");
					File encVid = super.getFileHierarchyManager().getExistingFile(videoFileName(videoId), encVideoDirectory(videoOwnerId));
					File encKey = super.getFileHierarchyManager().getExistingFile(encKeyFileName(videoId), encKeyDirectory(videoOwnerId));
					try {
						video = super.getTemporaryFileManager().getOrCreateFile(UUID.randomUUID().toString());
						super.getVideoDecryptor().decryptVideo(encVid, Files.readAllBytes(encKey.toPath()), video);
					} catch (IOException e) {
						Logger.getGlobal().severe("Could not decrypt Encrypted Video");
					}
					
				}
				break;
		}
		return video;
	}

	@Override
	public File getMetadata(int videoId, int targetUserId, int accessorId) {
		assertCompletelySetup();
		if(super.getAccessControl().canAccessAllVideoInformation(accessorId)) {
			return super.getFileHierarchyManager().getExistingFile(metaDataFileName(videoId), metadataDirectory(targetUserId));
		}
		return null;
	}

	@Override
	public int getUserIdForVideoId(int videoId, int accessorId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	protected int generateUnusedVideoId(int userId) {
		int[] videoIds = getVideoIds(userId);
		if (videoIds.length == 0) {
			return 1;
		}
		return Arrays.stream(videoIds).max().getAsInt() + 1;
	}
}
