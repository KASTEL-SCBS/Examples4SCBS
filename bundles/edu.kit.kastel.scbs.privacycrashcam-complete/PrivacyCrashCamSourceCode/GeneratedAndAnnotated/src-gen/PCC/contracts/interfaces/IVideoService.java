package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.File;
		
public interface IVideoService {
			
	Iterable<Integer> getVideoIds(int userId); 
	void postVideo(File encryptedVideo, File encryptedMetadata, Iterable<Integer> encryptedKeyData, int userId); 
	File getVideo(int videoId, int userId); 
	File getMetadata(int videoId, int userId); 
	void deleteVideo(int videoId, int userId); 

}