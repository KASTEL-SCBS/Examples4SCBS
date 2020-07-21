package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface ILocalVideoManager {
			
	void storeVideo(File video, File metadata); 
	Iterable<Integer> getLocallyStoredVideoIds(); 
	void deleteContentForVideo(int videoId); 
	File getMetadata(int videoId); 

}