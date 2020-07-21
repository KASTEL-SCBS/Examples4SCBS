package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;

public interface IVideoProcessor {
	public Boolean processVideo(File inputVideo, File outputVideo);
}
