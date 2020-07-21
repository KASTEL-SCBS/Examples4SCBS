package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
		
public interface IVideoEncryptor {
			
	void encrypt(File inputVideo, File inputMetadata, File outputVideo, File outputMetadata); 

}