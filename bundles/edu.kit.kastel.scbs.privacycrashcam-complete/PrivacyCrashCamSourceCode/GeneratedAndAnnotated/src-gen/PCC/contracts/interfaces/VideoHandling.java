package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.File;
		
public interface VideoHandling {
			
	Iterable<Integer> getVideoIds(int authenticationToken); 
	File downloadVideo(int videoId, int authenticationToken); 
	File getMetadata(int videoId, int authenticationToken); 
	void deleteVideo(int videoId, int authenticationToken); 

}