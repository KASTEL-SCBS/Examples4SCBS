package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.File;
		
public interface IVideoDetailsProvider {
			
	Iterable<Integer> getEncryptedKey(int videoId); 
	File getEncryptedVideo(int videoId); 
	File getEncryptedMetadata(int videoId); 

}