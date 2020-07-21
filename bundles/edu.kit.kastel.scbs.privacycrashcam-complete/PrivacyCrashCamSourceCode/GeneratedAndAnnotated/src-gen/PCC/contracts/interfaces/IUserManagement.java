package PCC.contracts.interfaces;

public interface IUserManagement {
			
	int createAccount(int email, int password); 
	void deleteAccount(int authenticationToken); 
	int login(int email, int password); 

}