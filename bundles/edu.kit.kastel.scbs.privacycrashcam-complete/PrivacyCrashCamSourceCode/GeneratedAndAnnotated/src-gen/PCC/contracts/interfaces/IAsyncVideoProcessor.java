package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface IAsyncVideoProcessor {
			
	void processVideo(File encryptedVideo, File encryptedMetadata, Iterable<Integer> encryptedKeyData, File outputVideo, File outputMetadata); 

}