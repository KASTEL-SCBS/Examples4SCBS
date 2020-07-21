package edu.kit.informatik.pcc.webinterface.serverconnection;

import java.util.Collection;

public interface IUserManagement {
	public Boolean createAccount(String email, String password);
	public String login(String email, String password);
	public Boolean deleteAccount(String authenticationToken);
	
}
