package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.security.Key;

public interface ISymmetricDecryptor extends IKeyConsumer {
	public void decrypt(File inputFile, Key key, File outputFile);
}
