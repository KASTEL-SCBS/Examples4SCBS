package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.io.IOException;

import edu.kit.informatik.pcc.core.data.IFileHierarchyManager;
import edu.kit.informatik.pcc.core.data.IFileManager;

public class EncryptedEnhancedPersistor extends VideoDataPersistor{

	public EncryptedEnhancedPersistor(IFileManager temporaryFileManager, IFileHierarchyManager videoFileManager, VideoServiceSQLAccess access) {
		super(temporaryFileManager, videoFileManager, access);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void saveAdditionalInformation(VideoProcessingContext context) throws IOException{
		File encVideoToPersist = super.getIFileHierarchyManager().getOrCreateFile(VideoServiceLocalRetrieve.videoFileName(context.getTargetVideoId()), VideoServiceLocalRetrieve.encVideoDirectory(context.getUserId()));
		super.copyFilesAndReplace(context.getEncryptedVideo(), encVideoToPersist);
		File encKeyToPersist = super.getIFileHierarchyManager().getOrCreateFile(VideoServiceLocalRetrieve.encKeyFileName(context.getTargetVideoId()), VideoServiceLocalRetrieve.encKeyDirectory(context.getUserId()));
		super.copyFilesAndReplace(context.getEncryptedKey(), encKeyToPersist);
	}

}
