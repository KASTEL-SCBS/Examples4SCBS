package edu.kit.informatik.pcc.service.server;

import java.io.File;
import java.util.Collection;

import edu.kit.informatik.pcc.core.crypto.IPublicKeyProvider;
import edu.kit.informatik.pcc.service.authentication.IUserIdProvider;
import edu.kit.informatik.pcc.service.authentication.IUserManagement;
import edu.kit.informatik.pcc.service.videoprocessing.IVideoService;

public class AnonymizationService implements IUserManagement, VideoHandling, VideoReceiving, PersecutionInformationProvision, JudgeAccess {
	private IUserManagement userManagement;
	private IUserIdProvider userIdProvider;
	private IVideoService videoService;
	private IPublicKeyProvider publicKeyProvider;
	
	private static AnonymizationService globalInstance;
	
	public AnonymizationService(IUserManagement userManagement, IUserIdProvider userIdProvider, IVideoService videoService, IPublicKeyProvider publicKeyProvider) {
		this.userManagement = userManagement;
		this.userIdProvider = userIdProvider;
		this.videoService = videoService;
		this.publicKeyProvider = publicKeyProvider;
	}
	
	public IPublicKeyProvider getPublicKeyProvider() {
		assertCompletelySetup();
		return publicKeyProvider;
	}
	
	public static AnonymizationService getGlobal() {
		assert globalInstance != null;
		return globalInstance;
	}
	
	public static void setGlobal(AnonymizationService webService) {
		assert globalInstance == null;
		globalInstance = webService;
	}

	@Override
	public Boolean createAccount(String email, String password) {
		assertCompletelySetup();
		return userManagement.createAccount(email, password);
	}

	@Override
	public String login(String email, String password) {
		assertCompletelySetup();
		return userManagement.login(email, password);
	}

	@Override
	public Boolean deleteAccount(String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return false;
		}
		int[] videoIds = getVideoIds(authenticationToken);
		for (int videoId: videoIds) {
			videoService.deleteVideo(videoId, userId);
		}
		return userManagement.deleteAccount(authenticationToken);
	}

	@Override
	public int[] getVideoIds(String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return null;
		}
		return videoService.getVideoIds(userId);
	}
	
	@Override
	public int[] getAllVideoIds(String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return null;
		}
		Collection<Integer> userIds = userIdProvider.getUserIds(userId);
		
		return videoService.getAllVideoIds(userIds, userId);
	}

	@Override
	public boolean receiveVideo(File encryptedVideo, File encryptedMetadata, File encryptedKey,
			String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return false;
		}
		return videoService.postVideo(encryptedVideo, encryptedMetadata, encryptedKey, userId);

	}

	@Override
	public File downloadVideo(int videoId, String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return null;
		}
		return videoService.getVideo(videoId, userId, userId, "ANONYMIZED");
	}

	@Override
	public File getMetadata(int videoId, String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return null;
		}
		return videoService.getMetadata(videoId, userId, userId);
	}

	@Override
	public void deleteVideo(int videoId, String authenticationToken) {
		assertCompletelySetup();
		int userId = userIdProvider.getUserId(authenticationToken);
		if (userId == IUserIdProvider.invalidId) {
			return;
		}
		videoService.deleteVideo(videoId, userId);
	}
	
	private void assertCompletelySetup() {
		assert userManagement != null;
		assert userIdProvider != null;
		assert videoService != null;
		assert publicKeyProvider != null;
	}

	@Override
	public File getConfidentialVideo(int videoId, int targetUserId, String authenticationToken) {
		int accessorId = userIdProvider.getUserId(authenticationToken);
		if (accessorId == IUserIdProvider.invalidId) {
			return null;
		}
		return videoService.getVideo(videoId, targetUserId, accessorId, "UNANONYMIZED");
	}

	@Override
	public File getMetaData(int videoId, int targetUserId, String authenticationToken) {
		assertCompletelySetup();
		int accessorId = userIdProvider.getUserId(authenticationToken);
		if (accessorId == IUserIdProvider.invalidId) {
			return null;
		}
		return videoService.getMetadata(videoId, targetUserId, accessorId);
	}

	@Override
	public int getUserIdForVideoId(int videoId, String authenticationToken) {
		int accessorId = userIdProvider.getUserId(authenticationToken);
		if (accessorId == IUserIdProvider.invalidId) {
			return -1;
		}
		return videoService.getUserIdForVideoId(videoId, accessorId);
	}
	
}
