package edu.kit.informatik.pcc.service.server;

import java.io.File;

public interface JudgeAccess {
	File getConfidentialVideo(int videoId, int targetUserId, String authenticationToken);
}
