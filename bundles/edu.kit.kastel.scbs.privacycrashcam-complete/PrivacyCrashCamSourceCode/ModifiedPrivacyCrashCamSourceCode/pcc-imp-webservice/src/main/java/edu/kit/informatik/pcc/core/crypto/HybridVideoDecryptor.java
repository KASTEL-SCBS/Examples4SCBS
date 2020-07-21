package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.security.Key;

import edu.kit.informatik.pcc.core.data.IFileManager;

public class HybridVideoDecryptor implements IVideoDecryptor, IPublicKeyProvider {
	private VideoDecryptor videoDecryptor;
	private KeyStorage keyStorage;
	
	public HybridVideoDecryptor(IFileManager fileManager) {
		JavaCryptoRSA javaCryptoRSA = new JavaCryptoRSA();
		JavaCryptoAES javaCryptoAES = new JavaCryptoAES();
		HybridDecryptor hybridDecryptor = new HybridDecryptor(javaCryptoRSA, javaCryptoAES);
	
		KeyStorage keyStorage = new KeyStorage(fileManager);
		VideoDecryptor videoDecryptor = new VideoDecryptor(keyStorage, hybridDecryptor, javaCryptoRSA);

		this.videoDecryptor = videoDecryptor;
		this.keyStorage = keyStorage;
	}
	
	@Override
	public Key getPublicKey() {
		return videoDecryptor.getPublicKey();
	}

	@Override
	public void decrypt(File inputVideo, File inputMetadata, byte[] keyData, File outputVideo, File outputMetadata) {
		videoDecryptor.decrypt(inputVideo, inputMetadata, keyData, outputVideo, outputMetadata);
	}

	@Override
	public void decryptVideo(File inputVideo, byte[] keyData, File outputFile) {
		videoDecryptor.decryptVideo(inputVideo, keyData, outputFile);
	}
}
