package PCC.components.UserService;

import PCC.contracts.interfaces.IUserDB;
import PCC.contracts.interfaces.IUserManagement;
import PCC.contracts.interfaces.IUserIdProvider;
import PCC.contracts.interfaces.IUserSessionDB;
import PCC.contracts.interfaces.SQLSanitizer;
		
public class UserService implements IUserIdProvider, IUserManagement {
	
	private IUserDB iUserDB;
	private IUserSessionDB iUserSessionDB;
	private SQLSanitizer sQLSanitizer;
	
	public UserService(IUserDB iUserDB, IUserSessionDB iUserSessionDB, SQLSanitizer sQLSanitizer) {
		// TODO: implement and verify auto-generated constructor.
	    this.iUserDB = iUserDB;
	    this.iUserSessionDB = iUserSessionDB;
	    this.sQLSanitizer = sQLSanitizer;
	}
	
	public int getUserId(int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
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