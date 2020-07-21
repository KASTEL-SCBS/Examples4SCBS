package PCC.contracts.interfaces;

public interface ISessionManager {
			
	int login(int email, int password); 
	int hasActiveSession(); 
	int getAuthenticationToken(); 
	void logout(); 

}