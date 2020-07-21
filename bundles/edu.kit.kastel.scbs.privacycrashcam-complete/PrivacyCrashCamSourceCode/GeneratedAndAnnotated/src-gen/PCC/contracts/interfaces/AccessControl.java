package PCC.contracts.interfaces;

public interface AccessControl {
			
	int hasPermission(int userId, int permissionName); 
	int canAccessUnanonymized(int userId); 

}