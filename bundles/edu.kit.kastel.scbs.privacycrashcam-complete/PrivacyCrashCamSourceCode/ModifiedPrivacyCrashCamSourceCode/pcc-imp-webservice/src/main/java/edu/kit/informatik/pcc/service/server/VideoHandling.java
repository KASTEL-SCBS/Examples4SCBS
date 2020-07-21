package edu.kit.informatik.pcc.service.server;

import java.io.File;

public interface VideoHandling {
	public int[] getVideoIds(String authenticationToken);
	public File getMetadata(int videoId, String authenticationToken);
	public void deleteVideo(int videoId, String authenticationToken);
	public File downloadVideo(int videoId, String authenticationToken);
}
