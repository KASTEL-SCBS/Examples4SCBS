package PCC.components.VideoNetworkAdapter;

import PCC.contracts.interfaces.VideoReceiving;
import PCC.contracts.interfaces.IClientVideoUpload;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
import PCC.contracts.interfaces.VideoHandling;
		
public class VideoNetworkAdapter implements IClientVideoUpload {
	
	private VideoHandling videoHandling;
	private VideoReceiving videoReceiving;
	
	public VideoNetworkAdapter(VideoHandling videoHandling, VideoReceiving videoReceiving) {
		// TODO: implement and verify auto-generated constructor.
	    this.videoHandling = videoHandling;
	    this.videoReceiving = videoReceiving;
	}
	
	public void uploadVideo(File encryptedVideo, File encryptedMetadata, Iterable<Integer> encryptedKey, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}