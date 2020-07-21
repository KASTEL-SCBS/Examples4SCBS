package edu.kit.informatik.pcc.service.videoprocessing;


/**
 * An IAsyncVideoProcessor instance is responsible for processing videos and metadata in an asynchronous way.
 * @author Jan Wittler
 */
public interface IAsyncVideoProcessor {
	/**
	 * Decrypts and processes the provided files in an asynchronous way.
	 * This method should return immediately and delegate the file processing to a background queue.
	 * As it is unsafe that the provided encrypted files will be available after this method returned, it is suggested to copy those to some internal storage for further processing.
	 * @param encryptedVideo The encrypted video to process. Must not be null.
	 * @param encryptedMetadata The encrypted metadata to process. Must not be null.
	 * @param encryptedKeyData The encrypted key data of the key used to encrypt the provided files. Must not be null.
	 * @param outputVideo The output file for the processed video. Must not be null.
	 * @param outputMetadata The output file for the processed metadata. Must not be null.
	 */
	public void processVideo(VideoProcessingContext context);
}
