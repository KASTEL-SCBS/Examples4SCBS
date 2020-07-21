package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface IFileHierarchyManager {
			
	File getOrCreateFile(int name, int directoryPath); 
	File getExistingFile(int name, int directoryPath); 
	void createDirectory(int directoryPath); 
	Iterable<File> getAllFilesInDirectory(int directoryPath); 
	void deleteFile(File file); 

}