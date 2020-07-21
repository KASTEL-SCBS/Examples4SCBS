package edu.kit.informatik.pcc.service.server;

import java.io.File;

public interface PersecutionInformationProvision {
	public File getMetaData(int videoId, int targetUserId, String authenticationToken);
	public int[] getAllVideoIds(String authenticationToken);
	public int getUserIdForVideoId(int videoId, String authenticationToken);
}
