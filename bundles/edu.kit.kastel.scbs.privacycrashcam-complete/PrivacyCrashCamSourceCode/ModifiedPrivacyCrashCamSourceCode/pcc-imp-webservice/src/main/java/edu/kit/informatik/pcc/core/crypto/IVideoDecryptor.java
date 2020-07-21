package edu.kit.informatik.pcc.core.crypto;

import java.io.File;

/**
 * An IVideoEncryptor instance provides functionality to decrypt videos and metadata.
 * @author Jan Wittler
 */
public interface IVideoDecryptor {
	/**
	 * Decrypts the provided files using the provided keyData.
	 * @param inputVideo The video to decrypt. Must not be null.
	 * @param inputMetadata The metadata to decrypt. Must not be null.
	 * @param keyData The data of the key used to encrypt the provided files. Must not be null.
	 * @param outputVideo The output file for the decrypted video. Must not be null.
	 * @param outputMetadata The output file for the decrypted metadata. Must not be null.
	 */
	public void decrypt(File inputVideo, File inputMetadata, byte[] keyData, File outputVideo, File outputMetadata);
	
	public void decryptVideo(File inputVideo, byte[] keyData,File outputFile);
}
