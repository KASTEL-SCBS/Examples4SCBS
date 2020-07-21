package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class VideoProcessingContext {

	private File encryptedVideo;
	private File encryptedMetadata;
	private File encryptedKey;
	private File metaData;
	private File anonymizedVideo;
	private int targetVideoId;
	private int userId;
	private File decryptedVideo;
	
	public VideoProcessingContext(File encryptedVideo, File encryptedMetadata, File encryptedKey, File metaData, File anonymizedVideo, File decryptedVideo, int targetVideoId, int userId) {
		this.encryptedVideo = encryptedVideo;
		this.encryptedMetadata = encryptedMetadata;
		this.encryptedKey = encryptedKey;
		this.metaData = metaData;
		this.decryptedVideo = decryptedVideo;
		this.anonymizedVideo = anonymizedVideo;
		this.targetVideoId = targetVideoId;
		this.userId = userId;
	}
	
	public File getEncryptedVideo() {
		return encryptedVideo;
	}
	
	public File getEncryptedMetadata() {
		return encryptedMetadata;
	}
	
	public File getEncryptedKey() {
		return encryptedKey;
	}
	
	public File getmetaData() {
		return metaData;
	}
	
	public File getAnonymizedVideo() {
		return anonymizedVideo;
	}
	
	public int getTargetVideoId() {
		return targetVideoId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public File getDecryptedVideo() {
		return decryptedVideo;
	}
	
	public byte[] getEncryptedKeyAsBytes() throws IOException {
		return Files.readAllBytes(encryptedKey.toPath());
	}
	
	public void clearVideoContext() {
		this.encryptedVideo = null;
		this.encryptedKey = null;
		this.encryptedMetadata = null;
		this.metaData = null;
		this.anonymizedVideo = null;
		this.decryptedVideo = null;
		this.userId = Integer.MIN_VALUE;
		this.targetVideoId = Integer.MIN_VALUE;
	}
}
