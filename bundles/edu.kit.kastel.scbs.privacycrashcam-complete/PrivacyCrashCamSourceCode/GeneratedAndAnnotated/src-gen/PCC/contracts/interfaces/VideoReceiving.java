package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
		
public interface VideoReceiving {
			
	void receiveVideo(File encryptedVideo, File encryptedMetadata, File encryptedKey, int authenticationToken); 

}