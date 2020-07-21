package edu.kit.informatik.pcc.core.crypto;

import java.security.Key;
import java.security.KeyPair;

public interface IAsymmetricDecryptor extends IKeyConsumer {
	public KeyPair generateAsymmetricKeyPair();
	public Key decryptKey(byte[] keyData, String keyAlgorithm, Key privateKey);
}
