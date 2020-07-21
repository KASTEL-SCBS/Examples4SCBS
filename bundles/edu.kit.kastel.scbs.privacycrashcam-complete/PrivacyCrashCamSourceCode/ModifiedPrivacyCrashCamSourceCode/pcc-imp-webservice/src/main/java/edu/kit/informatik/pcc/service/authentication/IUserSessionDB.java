package edu.kit.informatik.pcc.service.authentication;

public interface IUserSessionDB extends IUserIdProvider {
	public void storeAuthenticationToken(String authenticationToken, int userId);
	public void deleteTokensForUserId(int userId);
}
