package edu.kit.informatik.pcc.service.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.service.authentication.intAuth.IPasswordAuthentication;

public class PasswordAuthenticator implements PasswordAuthentication{
	
	private IPasswordAuthentication authentication;
	
	public PasswordAuthenticator() {
		this.authentication = authentication;
	}
	
	@Override
	public boolean authenticatePassword(Connection connection, int userId, String inputHash) {
        String passwordHash = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select \"password_salt\",\"password\" from \"user\" where \"user\".\"id\"=" +
                    userId + ";");
            if (rs.next()) {
                passwordHash = rs.getString("password");
            }
            rs.close();
            stmt.close();
            connection.close();
        } 
        catch (NullPointerException | SQLException e) {
            Logger.getGlobal().severe("Validating password with database failed");
            return false;
        }
        
        if (passwordHash == null) {
        	return false;
        }
        
        //return inputHash.equals(passwordHash);
        return authentication.authenticatePassword(userId, inputHash);
	}
}
