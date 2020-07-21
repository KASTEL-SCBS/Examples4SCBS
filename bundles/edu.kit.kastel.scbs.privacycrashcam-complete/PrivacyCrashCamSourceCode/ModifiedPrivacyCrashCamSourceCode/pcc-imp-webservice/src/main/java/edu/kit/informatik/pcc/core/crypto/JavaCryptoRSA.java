package edu.kit.informatik.pcc.core.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.KeyPair;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class JavaCryptoRSA implements IAsymmetricDecryptor {
	private static final String cipherTransformation = "RSA/ECB/PKCS1Padding";
	private static final String keyAlgorithm = "RSA";
	
	@Override
	public String keyAlgorithm() {
		return keyAlgorithm;
	}

	@Override
	public KeyPair generateAsymmetricKeyPair() {
		try {
			final KeyPairGenerator generator = KeyPairGenerator.getInstance(keyAlgorithm);
			generator.initialize(2048);
			return generator.generateKeyPair();
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	

	@Override
	public Key decryptKey(byte[] keyData, String keyAlgorithm, Key privateKey) {
		try {
            final Cipher cipher = Cipher.getInstance(cipherTransformation);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decodedKeyData = cipher.doFinal(keyData);
            return new SecretKeySpec(decodedKeyData, keyAlgorithm);
        } 
		catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            Logger.getGlobal().warning("Decrypting key failed");
            return null;
        }
	}

}
