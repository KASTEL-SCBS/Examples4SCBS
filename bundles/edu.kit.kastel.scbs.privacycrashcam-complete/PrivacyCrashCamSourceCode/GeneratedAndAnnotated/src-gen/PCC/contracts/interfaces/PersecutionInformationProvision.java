package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface PersecutionInformationProvision {
			
	File getMetaData(int videoId, int targetUserId, int authenticationToken); 
	Iterable<Integer> getAllVideoIds(); 
	int getUserIdForVideoId(int videoId, int authenticationToken); 

}