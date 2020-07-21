package PCC.components.VideoUploadWrapper;

import PCC.contracts.interfaces.ISessionManager;
import PCC.contracts.interfaces.IClientVideoUpload;
import PCC.contracts.interfaces.IVideoDetailsProvider;
import PCC.contracts.interfaces.IVideoUploadWrapper;
		
public class VideoUploadWrapper implements IVideoUploadWrapper {
	
	private IClientVideoUpload iClientVideoUpload;
	private ISessionManager iSessionManager;
	private IVideoDetailsProvider iVideoDetailsProvider;
	
	public VideoUploadWrapper(IClientVideoUpload iClientVideoUpload, ISessionManager iSessionManager, IVideoDetailsProvider iVideoDetailsProvider) {
		// TODO: implement and verify auto-generated constructor.
	    this.iClientVideoUpload = iClientVideoUpload;
	    this.iSessionManager = iSessionManager;
	    this.iVideoDetailsProvider = iVideoDetailsProvider;
	}
	
	public void uploadVideo(int videoId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}