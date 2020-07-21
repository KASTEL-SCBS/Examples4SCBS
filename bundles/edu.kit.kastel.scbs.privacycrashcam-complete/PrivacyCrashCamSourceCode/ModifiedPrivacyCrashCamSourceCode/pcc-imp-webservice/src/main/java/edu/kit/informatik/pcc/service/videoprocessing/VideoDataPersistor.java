package edu.kit.informatik.pcc.service.videoprocessing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import edu.kit.informatik.pcc.core.data.IFileHierarchyManager;
import edu.kit.informatik.pcc.core.data.IFileManager;
import edu.kit.informatik.pcc.core.data.Metadata;

public class VideoDataPersistor implements VideoPersistor{
	private IFileManager temporaryFileManager;
	private IFileHierarchyManager videoFileManager;
	private VideoServiceSQLAccess sqlAccess;
	
	public VideoDataPersistor(IFileManager temporaryFileManager, IFileHierarchyManager videoFileManager, VideoServiceSQLAccess access) {
		this.temporaryFileManager = temporaryFileManager;
		this.videoFileManager = videoFileManager;
		this.sqlAccess = access;
	}
	
	protected IFileManager getTemporaryFileManger() {
		return temporaryFileManager;
	}
	
	protected IFileHierarchyManager getIFileHierarchyManager() {
		return videoFileManager;
	}
	
	 protected void copyFilesAndReplace(File from, File to) throws IOException{
	    	Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
	 }
	 
	 protected VideoServiceSQLAccess getSQLAccess() {
		 return sqlAccess;
	 }
	 
	 private void saveMetaData(VideoProcessingContext context) throws IOException{
		   //Put editing time into metadata
	        Metadata meta;
			
				 meta = new Metadata(new String(Files.readAllBytes(Paths.get(context.getmetaData().getAbsolutePath()))));
				 meta.setEditingDate(System.currentTimeMillis());
			     PrintWriter pw = new PrintWriter(context.getmetaData());
			        pw.println(meta.getAsJSON());
			        pw.flush();
			        pw.close();

			        //Save metadata to final destination.
			        File metadata = videoFileManager.getOrCreateFile(VideoServiceLocalRetrieve.metaDataFileName(context.getTargetVideoId()), VideoServiceLocalRetrieve.metadataDirectory(context.getUserId()));
			     
					copyFilesAndReplace(context.getmetaData(), metadata);
	 }
	 
	 private void saveAnonymizedVideo(VideoProcessingContext context) throws IOException{
	    	File videoFile = videoFileManager.getOrCreateFile(VideoServiceLocalRetrieve.videoFileName(context.getTargetVideoId()), VideoServiceLocalRetrieve.videoDirectory(context.getUserId()));
	    	copyFilesAndReplace(context.getAnonymizedVideo(), videoFile);
	 }
	 
	 private void deleteFile(File file) {
		 temporaryFileManager.deleteFile(file);
	 }
	 
	 private File createRandomTemporaryFile() {
		 return temporaryFileManager.getOrCreateFile(UUID.randomUUID().toString());
	 }
	 
	 void clearProcessingContext(VideoProcessingContext context) {
		 deleteFile(context.getEncryptedVideo());
		 deleteFile(context.getEncryptedMetadata());
		 deleteFile(context.getEncryptedKey());
		 deleteFile(context.getmetaData());
		 deleteFile(context.getAnonymizedVideo());
		 deleteFile(context.getDecryptedVideo());
		 context.clearVideoContext();
	 }
	 
	 public boolean persistVideoInformation(VideoProcessingContext context) {
		 try {
			saveAnonymizedVideo(context);
			saveMetaData(context);
			saveAdditionalInformation(context);
			if(sqlAccess != null) {
				sqlAccess.persistVideoInformation(context.getUserId(), context.getTargetVideoId());
			}
			
		} catch (IOException e) {
			return false;
		}
		 
		 return true;
	 }
	 
	protected void saveAdditionalInformation(VideoProcessingContext context) throws IOException{
		//Do nothing but use as template hook;
	}
}
