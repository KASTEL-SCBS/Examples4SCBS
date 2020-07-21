package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface IClientVideoUpload {
			
	void uploadVideo(File encryptedVideo, File encryptedMetadata, Iterable<Integer> encryptedKey, int authenticationToken); 

}