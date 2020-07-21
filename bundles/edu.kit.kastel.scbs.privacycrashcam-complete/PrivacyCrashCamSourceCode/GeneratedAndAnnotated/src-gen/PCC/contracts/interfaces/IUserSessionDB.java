package PCC.contracts.interfaces;

public interface IUserSessionDB {
			
	void storeAuthenticationToken(int authenticationToken, int userId); 
	void deleteTokensForUserId(int userId); 

}