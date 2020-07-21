package PCC.components.VideoProcessingManager;

import PCC.contracts.interfaces.IAsyncVideoProcessor;
import PCC.contracts.interfaces.IVideoProcessor;
import PCC.contracts.interfaces.IVideoDecryptor;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
import PCC.contracts.interfaces.IFileManager;
		
public class VideoProcessingManager implements IAsyncVideoProcessor {
	
	private IFileManager iFileManager;
	private IVideoDecryptor iVideoDecryptor;
	private IVideoProcessor iVideoProcessor;
	
	public VideoProcessingManager(IFileManager iFileManager, IVideoDecryptor iVideoDecryptor, IVideoProcessor iVideoProcessor) {
		// TODO: implement and verify auto-generated constructor.
	    this.iFileManager = iFileManager;
	    this.iVideoDecryptor = iVideoDecryptor;
	    this.iVideoProcessor = iVideoProcessor;
	}
	
	public void processVideo(File encryptedVideo, File encryptedMetadata, Iterable<Integer> encryptedKeyData, File outputVideo, File outputMetadata){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}