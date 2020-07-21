package edu.kit.informatik.pcc.webinterface.gui;

import java.util.LinkedList;

import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinSession;

import edu.kit.informatik.pcc.webinterface.datamanagement.AccountDataManager;
import edu.kit.informatik.pcc.webinterface.datamanagement.AccountManagerInterface;
import edu.kit.informatik.pcc.webinterface.datamanagement.Video;
import edu.kit.informatik.pcc.webinterface.datamanagement.VideoDataManager;
import edu.kit.informatik.pcc.webinterface.datamanagement.VideoManagerInterface;
import edu.kit.informatik.pcc.webinterface.serverconnection.ServerCommunicator;
//Facade for PCM Alignment Purposes
public class PersecutionWebinterface implements VideoManagerInterface, AccountManagerInterface {
	private AccountDataManager accountDataManager;
	private VideoDataManager videoDataManager;
	private VaadinSession currentSession;
	
	public PersecutionWebinterface(ServerCommunicator communicator) {
		this.videoDataManager = new VideoDataManager(communicator);
		this.accountDataManager = new AccountDataManager(communicator);
	}
	
	public void setCurrentSession(VaadinSession session) {
		this.currentSession = session;
	}
	
	public VaadinSession getCurrentSession() {
		return currentSession;
	}

	@Override
	public StreamResource downloadVideo(int videoID, String videoName) {
		return videoDataManager.downloadVideo(videoID, videoName, currentSession);
	}

	@Override
	public LinkedList<Video> getVideos() {
		return videoDataManager.getVideos(currentSession);
	}

	@Override
	public boolean createAccount(String mail, String password) {
		return accountDataManager.createAccount(mail, password, currentSession);
	}

	@Override
	public boolean authenticateAccount(String mail, String password) {
		return accountDataManager.authenticateAccount(mail, password, currentSession);
	}

	@Override
	public boolean changeAccount(String password, String mailNew, String passwordNew) {
		return accountDataManager.changeAccount(password, mailNew, passwordNew, currentSession);
	}

	@Override
	public boolean deleteAccount() {
		return accountDataManager.deleteAccount(currentSession);
	}
}
