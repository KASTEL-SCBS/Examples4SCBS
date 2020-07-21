package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.security.Key;

public interface Decryptor {
	public Key decryptKey(byte[] keyData, Key privateKey);
	public void decrypt(File inputFile, Key key, File outputFile);
}
