package edu.kit.informatik.pcc.webinterface.datamanagement;

import java.util.LinkedList;

import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinSession;

public interface VideoManagerInterface {
	public StreamResource downloadVideo(int videoID, String videoName);
	public LinkedList<Video> getVideos();
}
