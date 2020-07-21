package PCC.components.UserNetworkAdapter;

import PCC.contracts.interfaces.IUserManagement;
		
public class UserNetworkAdapter implements IUserManagement {
	
	private IUserManagement iUserManagement;
	
	public UserNetworkAdapter(IUserManagement iUserManagement) {
		// TODO: implement and verify auto-generated constructor.
	    this.iUserManagement = iUserManagement;
	}
	
	public int createAccount(int email, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteAccount(int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int login(int email, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}