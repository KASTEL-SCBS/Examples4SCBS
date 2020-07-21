package PCC.contracts.interfaces;

public interface IUserDB {
			
	void createUser(int email, int password); 
	int getUserIdByMail(int email); 
	int validatePassword(int userId, int password); 
	void deleteAccount(int userId); 

}