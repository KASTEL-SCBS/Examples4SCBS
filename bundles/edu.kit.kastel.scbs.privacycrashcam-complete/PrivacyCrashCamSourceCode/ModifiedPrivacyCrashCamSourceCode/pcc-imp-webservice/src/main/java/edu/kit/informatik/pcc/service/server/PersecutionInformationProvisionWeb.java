package edu.kit.informatik.pcc.service.server;

import javax.ws.rs.core.Response;

public interface PersecutionInformationProvisionWeb {
	public Response getMetaData(int videoId, int targetUserId, String authenticationToken);
	public String getAllVideoIds(String authenticationToken);
	public String getUserIdForVideoId(int videoId, String authenticationToken);
}
