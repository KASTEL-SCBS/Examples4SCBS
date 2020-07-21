package PCC.components.LocalVideoManager;

import PCC.contracts.interfaces.IVideoEncryptor;
import PCC.contracts.interfaces.ILocalVideoManager;
import PCC.contracts.interfaces.IFileHierarchyManager;
import PCC.contracts.interfaces.IVideoDetailsProvider;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public class LocalVideoManager implements ILocalVideoManager, IVideoDetailsProvider {
	
	private IFileHierarchyManager iFileHierarchyManager;
	private IVideoEncryptor iVideoEncryptor;
	
	public LocalVideoManager(IFileHierarchyManager iFileHierarchyManager, IVideoEncryptor iVideoEncryptor) {
		// TODO: implement and verify auto-generated constructor.
	    this.iFileHierarchyManager = iFileHierarchyManager;
	    this.iVideoEncryptor = iVideoEncryptor;
	}
	
	public void storeVideo(File video, File metadata){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> getLocallyStoredVideoIds(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteContentForVideo(int videoId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getMetadata(int videoId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> getEncryptedKey(int videoId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getEncryptedVideo(int videoId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getEncryptedMetadata(int videoId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}