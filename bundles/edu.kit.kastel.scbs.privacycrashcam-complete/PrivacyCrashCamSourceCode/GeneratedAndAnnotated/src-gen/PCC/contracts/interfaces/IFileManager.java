package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
		
public interface IFileManager {
			
	File getOrCreateFile(int name); 
	File getExisitingFile(int name); 
	void deleteFile(File file); 

}