package PCC.components.VideoService;

import PCC.contracts.interfaces.IAsyncVideoProcessor;
import PCC.contracts.interfaces.AccessControl;
import PCC.contracts.interfaces.IVideoService;
import PCC.contracts.interfaces.IFileHierarchyManager;
import java.lang.Iterable;
import PCC.contracts.datatypes.File;
import PCC.contracts.interfaces.IFileManager;
		
public class VideoService implements IVideoService {
	
	private IAsyncVideoProcessor iAsyncVideoProcessor;
	private IFileManager iFileManager;
	private IFileHierarchyManager iFileHierarchyManager;
	private AccessControl accessControl;
	
	public VideoService(IAsyncVideoProcessor iAsyncVideoProcessor, IFileManager iFileManager, IFileHierarchyManager iFileHierarchyManager, AccessControl accessControl) {
		// TODO: implement and verify auto-generated constructor.
	    this.iAsyncVideoProcessor = iAsyncVideoProcessor;
	    this.iFileManager = iFileManager;
	    this.iFileHierarchyManager = iFileHierarchyManager;
	    this.accessControl = accessControl;
	}
	
	public Iterable<Integer> getVideoIds(int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void postVideo(File encryptedVideo, File encryptedMetadata, Iterable<Integer> encryptedKeyData, int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getVideo(int videoId, int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getMetadata(int videoId, int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteVideo(int videoId, int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}