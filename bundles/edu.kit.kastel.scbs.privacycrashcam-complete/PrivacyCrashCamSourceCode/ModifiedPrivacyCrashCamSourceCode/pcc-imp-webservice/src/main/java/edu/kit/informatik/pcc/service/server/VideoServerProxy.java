package edu.kit.informatik.pcc.service.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;

import edu.kit.informatik.pcc.core.data.IFileManager;

@Path("webservice/videos")
public class VideoServerProxy implements PersecutionInformationProvisionWeb, JudgeAccessWeb{
	private static IFileManager temporaryFileManager;
	
	public static void setTemporaryFileManager(IFileManager pTemporaryFileManager, AnonymizationService service) {
		assert temporaryFileManager == null;
		temporaryFileManager = pTemporaryFileManager;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getVideoIds(@HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get Video Ids Request");
		int[] videoIds = AnonymizationService.getGlobal().getVideoIds(authenticationToken);
		if (videoIds == null) {
			return ServerConstants.NOT_AUTHENTICATED;
		}
		JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < videoIds.length; i++) {
            jsonArray.put(videoIds[i]);
        }
        return jsonArray.toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allVideos")
	public String getAllVideoIds(@HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get all Video Ids Request");
		int[] videoIds = AnonymizationService.getGlobal().getAllVideoIds(authenticationToken);
		if (videoIds == null) {
			return ServerConstants.NOT_AUTHENTICATED;
		}
		JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < videoIds.length; i++) {
            jsonArray.put(videoIds[i]);
        }
        return jsonArray.toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getUserIdForVideoId/{id}")
	public String getUserIdForVideoId(@PathParam("id") int videoId, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get UserId for VideoId Request");
		
		int userId = AnonymizationService.getGlobal().getUserIdForVideoId(videoId, authenticationToken);
		if(userId == -1) {
			return ServerConstants.NOT_AUTHENTICATED;
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(userId);
		
		return jsonArray.toString();
	}
	
	@GET
	@Path("{id}")
	public Response getVideo(@PathParam("id") int videoId, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get Video by Id Request");
		File video = AnonymizationService.getGlobal().downloadVideo(videoId, authenticationToken);
		if (video == null) {
			return response404().build();
		}
		
		InputStream inputStream;
        try {
            inputStream = new FileInputStream(video.getPath());
        } catch (FileNotFoundException e) {
            Logger.getGlobal().warning("An error has occurred finding file " + video.getPath());
            return response404().build();
        }
        return response(inputStream).build();
	}

	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("getConfidentialVideo")
	public Response getConfidentialVideo(@FormParam("videoId") int videoId, @FormParam("userId") int userId, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get Video by Id Request");
		File video = AnonymizationService.getGlobal().getConfidentialVideo(videoId, userId, authenticationToken);
		if (video == null) {
			return response404().build();
		}
		
		InputStream inputStream;
        try {
            inputStream = new FileInputStream(video.getPath());
        } catch (FileNotFoundException e) {
            Logger.getGlobal().warning("An error has occurred finding file " + video.getPath());
            return response404().build();
        }
        return response(inputStream).build();
	}

	private Response.ResponseBuilder response(InputStream inputStream){
		return Response.ok().entity(inputStream);
	}

	private Response.ResponseBuilder response404(){
		return Response.status(404);
	}

	@GET
	@Path("metadata/{id}")
	public Response getMetadata(@PathParam("id") int videoId, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get Metadata by Id Request");
		File metadata = AnonymizationService.getGlobal().getMetadata(videoId, authenticationToken);
		if (metadata == null) {
			return response404().build();
		}
		
		InputStream inputStream;
        try {
            inputStream = new FileInputStream(metadata.getPath());
        } catch (FileNotFoundException e) {
            Logger.getGlobal().warning("An error has occurred finding file " + metadata.getPath());
            return response404().build();
        }
        return response(inputStream).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("metadata/getMetaData")
	public Response getMetaData(@FormParam("videoId") int videoId, @FormParam("targetUserId") int targetUserId, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Get Metadata by Id Request");
		File metadata = AnonymizationService.getGlobal().getMetaData(videoId, targetUserId, authenticationToken);
		if (metadata == null) {
			return response404().build();
		}
		
		InputStream inputStream;
        try {
            inputStream = new FileInputStream(metadata.getPath());
        } catch (FileNotFoundException e) {
            Logger.getGlobal().warning("An error has occurred finding file " + metadata.getPath());
            return response404().build();
        }
        return response(inputStream).build();
	}
	
	@DELETE
	@Path("{id}")
	public String deleteVideo(@PathParam("id") int videoId, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
		assertCompletelySetup();
		Logger.getGlobal().info("Delete video by Id Request");
		AnonymizationService.getGlobal().deleteVideo(videoId, authenticationToken);
		return ServerConstants.SUCCESS;
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadVideo(@FormDataParam("video") InputStream encryptedVideoStream, @FormDataParam("metadata") InputStream encryptedMetadataStream, @FormDataParam("key") InputStream encryptedKeyStream, @HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
    	assertCompletelySetup();
		Logger.getGlobal().info("Video upload");
		
		String uuid = UUID.randomUUID().toString();
		File encryptedVideo = temporaryFileManager.getOrCreateFile(uuid + ".video");
		File encryptedMetadata = temporaryFileManager.getOrCreateFile(uuid + ".metadata");
		File encryptedKeyFile = temporaryFileManager.getOrCreateFile(uuid + ".key");
		saveStreamToFile(encryptedVideoStream, encryptedVideo);
		saveStreamToFile(encryptedMetadataStream, encryptedMetadata);
		saveStreamToFile(encryptedKeyStream, encryptedKeyFile);
		String result = ServerConstants.SUCCESS;
		
		
		AnonymizationService.getGlobal().receiveVideo(encryptedVideo, encryptedMetadata, encryptedKeyFile, authenticationToken);
		return result;
    }
	
	private void assertCompletelySetup() {
		assert temporaryFileManager != null;
	}
	
	private void saveStreamToFile(InputStream inputStream, File file) {
		int read;
        byte[] bytes = new byte[1024];
		try (FileOutputStream fos = new FileOutputStream(file)) {
			while ((read = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
			fos.flush();
		} catch (IOException e) {
			Logger.getGlobal().warning("Failed to store file during upload");
			e.printStackTrace();
		}
	}
}
