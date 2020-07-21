package edu.kit.informatik.pcc.service.server;

import javax.ws.rs.core.Response;

public interface JudgeAccessWeb {
	public Response getConfidentialVideo(int videoId, int targetUserId, String authenticationToken);
}
