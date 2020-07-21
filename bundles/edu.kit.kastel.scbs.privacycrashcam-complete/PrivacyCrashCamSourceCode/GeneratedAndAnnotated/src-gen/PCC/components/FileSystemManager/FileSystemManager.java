package PCC.components.FileSystemManager;

import PCC.contracts.interfaces.IFileHierarchyManager;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
import PCC.contracts.interfaces.IFileManager;
		
public class FileSystemManager implements IFileHierarchyManager, IFileManager {
	
	public FileSystemManager() {
		// TODO: implement and verify auto-generated constructor.
	}
	
	public File getOrCreateFile(int name, int directoryPath){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getExistingFile(int name, int directoryPath){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void createDirectory(int directoryPath){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<File> getAllFilesInDirectory(int directoryPath){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteFile(File file){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getOrCreateFile(int name){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getExisitingFile(int name){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteFile(File file){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}