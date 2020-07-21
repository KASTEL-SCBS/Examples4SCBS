package PCC.contracts.interfaces;

import PCC.contracts.datatypes.Connection;
		
public interface PasswordAuthentication {
			
	int authenticatePassword(Connection connection, int userId, int password); 

}