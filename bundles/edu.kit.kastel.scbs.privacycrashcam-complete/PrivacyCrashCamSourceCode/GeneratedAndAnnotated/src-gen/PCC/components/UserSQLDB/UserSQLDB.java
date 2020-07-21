package PCC.components.UserSQLDB;

import PCC.contracts.interfaces.IUserDB;
import PCC.contracts.interfaces.PasswordAuthentication;
import PCC.contracts.interfaces.IUserSessionDB;
		
public class UserSQLDB implements IUserDB, IUserSessionDB {
	
	private PasswordAuthentication passwordAuthentication;
	
	public UserSQLDB(PasswordAuthentication passwordAuthentication) {
		// TODO: implement and verify auto-generated constructor.
	    this.passwordAuthentication = passwordAuthentication;
	}
	
	public void createUser(int email, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int getUserIdByMail(int email){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int validatePassword(int userId, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteAccount(int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void storeAuthenticationToken(int authenticationToken, int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteTokensForUserId(int userId){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}