package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class JavaCryptoAES implements ISymmetricDecryptor{
	private static final String cipherTransformation = "AES/ECB/PKCS5Padding";
	private static final String keyAlgorithm = "AES";
	
	@Override
	public String keyAlgorithm() {
		return keyAlgorithm;
	}
	

	@Override
	public void decrypt(File inputFile, Key key, File outputFile) {
        Cipher cipher;
        try {
        	cipher = Cipher.getInstance(cipherTransformation);
        	cipher.init(Cipher.DECRYPT_MODE, key);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            Logger.getGlobal().warning("Creating cipher failed");
            return;
        }
        
        try {
        	cryptFile(inputFile, cipher, outputFile);
        }
        catch (IOException e) {
        	Logger.getGlobal().warning("Error while decrypting file " + inputFile.getName());
        }
	}
	
	private void cryptFile(File inputFile, Cipher cipher, File outputFile) throws IOException {
		try (
    		FileInputStream inStream = new FileInputStream(inputFile);
        	FileOutputStream outStream = new FileOutputStream(outputFile);
        	CipherOutputStream cipherStream = new CipherOutputStream(outStream, cipher);
		) {
        	int bytesRead;
        	byte[] buffer = new byte[1024];
        	while ((bytesRead = inStream.read(buffer)) != -1) {
        		cipherStream.write(buffer, 0, bytesRead);
        		cipherStream.flush();
        	}
        	outStream.flush();
        }
	}
}
