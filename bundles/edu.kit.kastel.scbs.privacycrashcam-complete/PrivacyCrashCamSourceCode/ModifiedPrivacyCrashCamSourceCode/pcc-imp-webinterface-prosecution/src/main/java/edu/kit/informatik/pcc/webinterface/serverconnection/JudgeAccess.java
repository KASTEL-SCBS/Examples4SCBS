package edu.kit.informatik.pcc.webinterface.serverconnection;

import java.io.InputStream;

import javax.ws.rs.core.Response;

public interface JudgeAccess {
	InputStream getConfidentialVideo(int videoId, int targetUserId, String authenticationToken);
}
