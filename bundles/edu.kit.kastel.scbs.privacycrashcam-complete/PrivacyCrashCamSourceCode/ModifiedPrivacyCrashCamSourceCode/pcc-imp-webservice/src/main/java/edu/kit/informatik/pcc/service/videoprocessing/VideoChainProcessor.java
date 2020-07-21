package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.util.UUID;

import org.junit.rules.TemporaryFolder;

import edu.kit.informatik.pcc.core.data.IFileManager;

/**
 * A VideoChainProcessor processes files by applying a chain of IVideoProcessor instances onto them.
 * @author Jan Wittler
 */
public class VideoChainProcessor implements IVideoProcessor {
	private IFileManager temporaryFileManager;
	private IVideoProcessor[] videoProcessors;
	
	public VideoChainProcessor(IFileManager temporaryFileManager, IVideoProcessor[] videoProcessors) {
		this.temporaryFileManager = temporaryFileManager;
		this.videoProcessors = videoProcessors;
	}
	
//	public void setTemporaryFileManager(IFileManager temporaryFileManager) {
//		assert this.temporaryFileManager == null;
//		this.temporaryFileManager = temporaryFileManager;
//	}
//	
//	public void setVideoProcessors(IVideoProcessor[] videoProcessors) {
//		assert this.videoProcessors == null;
//		assert videoProcessors.length > 0;
//		this.videoProcessors = videoProcessors;
//	}

	@Override
	public Boolean processVideo(File inputVideo, File outputVideo) {
		assertCompletelySetup();
		File intermediateVideo = inputVideo;
		Boolean success = true;
		for (int i = 0; i < videoProcessors.length - 1; i++) {
			File temporaryFile = temporaryFileManager.getOrCreateFile(UUID.randomUUID().toString());
			success = videoProcessors[i].processVideo(intermediateVideo, temporaryFile);
			if (intermediateVideo != inputVideo) {
				temporaryFileManager.deleteFile(intermediateVideo);
			}
			intermediateVideo = temporaryFile;
			if (!success) {
				break;
			}
		}
		if (success) {
			success = videoProcessors[videoProcessors.length - 1].processVideo(intermediateVideo, outputVideo);
		}
		if (intermediateVideo != inputVideo) {
			temporaryFileManager.deleteFile(intermediateVideo);
		}
		return success;
	}
	
	private void assertCompletelySetup() {
		assert videoProcessors != null;
	}
}
