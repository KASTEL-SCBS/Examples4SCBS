package PCC.components.SessionManager;

import PCC.contracts.interfaces.ISessionManager;
import PCC.contracts.interfaces.IUserManagement;
import PCC.contracts.interfaces.ISimpleValueStorage;
import PCC.contracts.interfaces.ILocalVideoManager;
		
public class SessionManager implements ISessionManager {
	
	private ISimpleValueStorage iSimpleValueStorage;
	private ILocalVideoManager iLocalVideoManager;
	private IUserManagement iUserManagement;
	
	public SessionManager(ISimpleValueStorage iSimpleValueStorage, ILocalVideoManager iLocalVideoManager, IUserManagement iUserManagement) {
		// TODO: implement and verify auto-generated constructor.
	    this.iSimpleValueStorage = iSimpleValueStorage;
	    this.iLocalVideoManager = iLocalVideoManager;
	    this.iUserManagement = iUserManagement;
	}
	
	public int login(int email, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int hasActiveSession(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int getAuthenticationToken(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void logout(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}