package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
		
public interface JudgeAccess {
			
	File downloadConfidentialVideo(int videoId, int userId, int authenticationToken); 

}