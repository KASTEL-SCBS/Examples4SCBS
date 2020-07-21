package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.security.Key;

public class HybridDecryptor implements Decryptor {
	private IAsymmetricDecryptor asymmetricDecryptor;
	private ISymmetricDecryptor symmetricDecryptor;
	
	public HybridDecryptor(IAsymmetricDecryptor asymmetricDecryptor, ISymmetricDecryptor symmetricDecryptor) {
		this.asymmetricDecryptor = asymmetricDecryptor;
		this.symmetricDecryptor = symmetricDecryptor;
	}

	@Override
	public Key decryptKey(byte[] keyData, Key privateKey) {
		assertCompletelySetup();
		return asymmetricDecryptor.decryptKey(keyData, symmetricDecryptor.keyAlgorithm(), privateKey);
	}

	@Override
	public void decrypt(File inputFile, Key key, File outputFile) {
		assertCompletelySetup();
		symmetricDecryptor.decrypt(inputFile, key, outputFile);
	}
	
	private void assertCompletelySetup() {
		assert asymmetricDecryptor != null;
		assert symmetricDecryptor != null;
	}
}
