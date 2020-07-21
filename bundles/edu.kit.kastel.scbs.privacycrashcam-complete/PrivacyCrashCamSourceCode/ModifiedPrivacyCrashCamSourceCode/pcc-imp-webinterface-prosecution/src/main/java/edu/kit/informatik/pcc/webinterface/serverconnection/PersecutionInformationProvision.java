package edu.kit.informatik.pcc.webinterface.serverconnection;

import java.io.InputStream;

import javax.ws.rs.core.Response;

public interface PersecutionInformationProvision {
	public String getMetaData(int videoId, int targetUserId, String authenticationToken);
	public String getAllVideoIds(String authenticationToken);
	public int getUserIdForVideoId(int videoId, String authenticationToken);
}
