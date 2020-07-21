package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;

import edu.kit.informatik.pcc.core.crypto.IVideoDecryptor;
import edu.kit.informatik.pcc.core.data.IFileHierarchyManager;
import edu.kit.informatik.pcc.core.data.IFileManager;
import edu.kit.informatik.pcc.service.accesscontrol.AccessControl;

public abstract class VideoService implements IVideoService {
	protected static final String videosDirectory = "videos";
	protected static final String metadataDirectory = "metadata";
	protected static final String keyDirectory = "encKey";
	protected static final String encVideoDirectory = "encVideo";
	protected static final String ANONYMIZED = "ANONYMIZED";
	protected static final String UNANONYMIZED = "UNANONYMIZED";
	
	
	private IFileHierarchyManager fileHierarchyManager;
	private IFileManager temporaryFileManager;
	private IAsyncVideoProcessor asyncVideoProcessor;
	private IVideoDecryptor videoDecryptor;
	protected AccessControl accessControl;
	
	public VideoService(IFileHierarchyManager fileHierarchyManager, IFileManager temporaryFileManager, IAsyncVideoProcessor asyncVideoProcessor, AccessControl rbac, IVideoDecryptor videoDecryptor) {
		this.fileHierarchyManager = fileHierarchyManager;
		this.temporaryFileManager = temporaryFileManager;
		this.asyncVideoProcessor = asyncVideoProcessor;
		this.videoDecryptor = videoDecryptor;
		this.accessControl = rbac;
	}
	
	protected IFileHierarchyManager getFileHierarchyManager() {
		return fileHierarchyManager;
	}
	
	protected IFileManager getTemporaryFileManager() {
		return temporaryFileManager;
	}
	
	protected IAsyncVideoProcessor getAsyncVideoProcessor() {
		return asyncVideoProcessor;
	}
	
	protected IVideoDecryptor getVideoDecryptor() {
		return videoDecryptor;
	}
	
	protected AccessControl getAccessControl() {
		return accessControl;
	}
	
	protected void assertCompletelySetup() {
		assert fileHierarchyManager != null;
		assert temporaryFileManager != null;
		assert asyncVideoProcessor != null;
	}
	
	protected File getEncKey(int videoId, int userId) {
		return fileHierarchyManager.getExistingFile(encKeyFileName(videoId), encKeyDirectory(userId));
	}
	
	public static String videoFileName(int videoId) {
		return videoId + ".mp4";
	}
	
	public static String metaDataFileName(int videoId) {
		return videoId + ".json";
	}
	
	public static String encKeyFileName(int videoId) {
		return videoId + ".key";
	}
	public static String videoDirectory(int userId) {
		return userId + File.separator + videosDirectory;
	}
	
	public static String metadataDirectory(int userId) {
		return userId + File.separator + metadataDirectory;
	}
	
	public static String encVideoDirectory(int userId) {
		return userId + File.separator + encVideoDirectory;
	}
	
	public static String encKeyDirectory(int userId) {
		return userId + File.separator + keyDirectory;
	}


	protected abstract int generateUnusedVideoId(int userId);
}
