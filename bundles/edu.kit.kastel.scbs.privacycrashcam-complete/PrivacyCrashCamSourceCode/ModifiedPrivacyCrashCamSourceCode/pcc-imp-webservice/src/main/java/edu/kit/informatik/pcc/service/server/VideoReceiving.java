package edu.kit.informatik.pcc.service.server;

import java.io.File;

public interface VideoReceiving {
	public boolean receiveVideo(File encryptedVideo, File encryptedMetadata, File encryptedKey, String authenticationToken);
}
