package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.util.Collection;

public interface IVideoService {
	public int[] getVideoIds(int userId);
	public void deleteVideo(int videoId, int userId);
	public File getVideo(int videoId, int videoOwnerId, int accessorId, String videoType);
	boolean postVideo(File encryptedVideo, File encryptedMetadata, File encryptedKey, int userId);
	int[] getAllVideoIds(Collection<Integer> userIds, int accessorId);
	public File getMetadata(int videoId, int targetUserId, int accessorId);
	public int getUserIdForVideoId(int videoId, int accessorId);
}
