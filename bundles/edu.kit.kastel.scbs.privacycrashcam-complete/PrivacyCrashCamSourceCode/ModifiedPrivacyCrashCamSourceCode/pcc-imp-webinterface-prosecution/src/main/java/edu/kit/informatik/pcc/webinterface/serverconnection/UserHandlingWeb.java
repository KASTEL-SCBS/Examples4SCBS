package edu.kit.informatik.pcc.webinterface.serverconnection;


import javax.ws.rs.core.Response;

public interface UserHandlingWeb {
	public String createAccount(String email, String password);
	public Response login(String email, String password);
	public String deleteAccount(String authenticationToken);
}
