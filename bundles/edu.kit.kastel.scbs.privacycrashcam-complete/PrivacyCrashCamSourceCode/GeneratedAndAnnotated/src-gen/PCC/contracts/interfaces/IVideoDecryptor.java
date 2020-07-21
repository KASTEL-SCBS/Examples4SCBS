package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface IVideoDecryptor {
			
	void decrypt(File inputVideo, File inputMetadata, Iterable<Integer> keyData, File outputVideo, File outputMetadata); 

}