package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.security.Key;
import java.security.KeyPair;

public class VideoDecryptor implements IVideoDecryptor, IPublicKeyProvider {
	public static final String publicKeyId = "PublicKey.key";
	public static final String privateKeyId ="PrivateKey.key";
	
	private IKeyStorage keyStorage;
	private Decryptor fileDecryptor;
	private IAsymmetricDecryptor asymmetricDecryptor;
	
	public VideoDecryptor(IKeyStorage keyStorage, Decryptor fileDecryptor, IAsymmetricDecryptor asymmetricDecryptor) {
		this.keyStorage = keyStorage;
		this.fileDecryptor = fileDecryptor;
		this.asymmetricDecryptor = asymmetricDecryptor;
	}
	
	/**
	 * Returns the instance's public key.
	 * If there is none yet, a new public / private key pair is generated and its public key is returned.
	 */
	@Override
	public Key getPublicKey() {
		assertCompletelySetup();
		return getKey(publicKeyId);
	}
	
	private Key getPrivateKey() {
		return getKey(privateKeyId);
	}
	
	private Key getKey(String id) {
		Key key = keyStorage.loadKey(id);
		if (key != null) {
			return key;
		}
		setupAsymmetricKeyPair();
		return keyStorage.loadKey(id);
	}

	@Override
	public void decrypt(File inputVideo, File inputMetadata, byte[] keyData, File outputVideo, File outputMetadata) {
		assertCompletelySetup();
		Key privateKey = getPrivateKey();
		Key key = fileDecryptor.decryptKey(keyData, privateKey);
		fileDecryptor.decrypt(inputVideo, key, outputVideo);
		fileDecryptor.decrypt(inputMetadata, key, outputMetadata);
	}
	
	private void setupAsymmetricKeyPair() {
		KeyPair keyPair = asymmetricDecryptor.generateAsymmetricKeyPair();
		keyStorage.storeKey(publicKeyId, keyPair.getPublic());
		keyStorage.storeKey(privateKeyId, keyPair.getPrivate());
	}
	
	private void assertCompletelySetup() {
		assert keyStorage != null;
		assert fileDecryptor != null;
		assert asymmetricDecryptor != null;
	}

	@Override
	public void decryptVideo(File inputVideo, byte[] keyData, File outputVideo) {
		Key privateKey = getPrivateKey();
		Key key = fileDecryptor.decryptKey(keyData, privateKey);
		fileDecryptor.decrypt(inputVideo, key, outputVideo);
	}

}
