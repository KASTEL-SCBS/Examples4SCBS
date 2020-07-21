package edu.kit.informatik.pcc.service.authentication;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.service.data.SQLDB;
import edu.kit.informatik.pcc.service.hashing.CryptographicHasher;
import edu.kit.informatik.pcc.service.hashing.CryptographicHashing;

public class UserSQLDB extends SQLDB implements IUserDB, IUserSessionDB {

	
	private PasswordAuthentication authenticator;
	private CryptographicHashing hashing;
	
	public UserSQLDB() {
		this.authenticator = new PasswordAuthenticator();
	}
	
	@Override
	public void createUser(String email, String password) {
		//mail already in use
		if (getUserIdByMail(email) != IUserIdProvider.invalidId) {
			return;
		}
		byte[] salt = createSalt();
		String hashedPassword = hashing.hash(password, salt);
		String saltString = Base64.getEncoder().encodeToString(salt);
		
		Connection connection = connectToDatabase();
		if (connection == null) {
			return;
		}
		try {
			Statement stmt = connection.createStatement();
			String sql = "insert into \"user\" (mail,password,password_salt) values ('" + 
					email + "','" + hashedPassword + "','" + saltString + "' );";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		}
		catch (SQLException e) {
			Logger.getGlobal().warning("Creating account in database failed: " + e.getLocalizedMessage());
		}
	}
	
	private byte[] getSaltForUser(int userId) {
		Connection connection = connectToDatabase();
		String salt = null;
		try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select \"password_salt\",\"password\" from \"user\" where \"user\".\"id\"=" +
                    userId + ";");
            if (rs.next()) {
                salt = rs.getString("password_salt");
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (NullPointerException | SQLException e) {
            Logger.getGlobal().severe("Salt for ID not found");
            return null;
        }
		
		if(salt == null)
			return null;
		
		return Base64.getDecoder().decode(salt);
		
	}

	@Override
	public int getUserIdByMail(String email) {
		int userId = IUserIdProvider.invalidId;
		Connection connection = connectToDatabase();
		if (connection == null || email == null) {
			return userId;
		}
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select \"id\" from \"user\" where \"user\".\"mail\"='" +
                    email + "'");
            if (rs.next()) {
            	userId = rs.getInt("id");
            }
            stmt.close();
            connection.close();
        } 
        catch (SQLException | NullPointerException e) {
            Logger.getGlobal().warning("Retrieving account id from database failed");
            return IUserIdProvider.invalidId;
        }
        return userId;
	}

	@Override
	public Boolean validatePassword(int userId, String password) {
		Connection connection = connectToDatabase();
		if (connection == null) {
			return false;
		}
		
		byte[] salt = getSaltForUser(userId);
		String hash = hashing.hash(password, salt);
		
       return authenticator.authenticatePassword(connection, userId, hash);
	}

	@Override
	public void deleteAccount(int userId) {
		Connection connection = connectToDatabase();
		if (connection == null) {
			return;
		}
        try {
            Statement stmt = connection.createStatement();
            String sql = "delete from \"user\" where \"user\".\"id\"='" + userId + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } 
        catch (NullPointerException | SQLException e) {
            Logger.getGlobal().warning("Error while deleting account in database");
        }
	}
	
	@Override
	public int getUserId(String authenticationToken) {
		int userId = IUserIdProvider.invalidId;
		Connection connection = connectToDatabase();
		if (connection == null) {
			return userId;
		}
		try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select \"id\" from \"sessions\" where \"sessions\".\"token\"='" +
                    authenticationToken + "'");
            if (rs.next()) {
                userId = rs.getInt("id");
            }
            stmt.close();
            connection.close();
        } 
        catch (SQLException | NullPointerException e) {
            Logger.getGlobal().warning("Retrieving account id from database failed");
            return IUserIdProvider.invalidId;
        }
        return userId;
	}

	@Override
	public void storeAuthenticationToken(String authenticationToken, int userId) {
		Connection connection = connectToDatabase();
		if (connection == null) {
			return;
		}
		try {
			Statement stmt = connection.createStatement();
			String sql = "insert into \"sessions\" (id,token) values ('" + 
			userId + "','" + authenticationToken + "' );";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		}
		catch (SQLException e) {
			Logger.getGlobal().warning("Storing token to database failed");
		}
	}

	@Override
	public void deleteTokensForUserId(int userId) {
		Connection connection = connectToDatabase();
		if (connection == null) {
			return;
		}
        try {
            Statement stmt = connection.createStatement();
            String sql = "delete from \"sessions\" where \"sessions\".\"id\"='" + userId + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } 
        catch (NullPointerException | SQLException e) {
            Logger.getGlobal().warning("Error while deleting tokens in database");
        }
	}
    
    private byte[] createSalt () {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } 
        catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            Logger.getGlobal().warning("An error occurred getting a secure random instance!");
            return null;
        }
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        return salt;
    }
    


	@Override
	public Collection<Integer> getUserIds(int userId) {
		Connection connection = connectToDatabase();
		Set<Integer> userIds = new HashSet<Integer>();
		if (connection == null) {
			return null;
		}
		try {
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery("select \"id\" from \"user\"");
			while(resultSet.next()) {
				userIds.add(new Integer(resultSet.getInt("id")));
			}
			stmt.close();
			connection.close();
		}
		catch (SQLException e) {
			Logger.getGlobal().warning("Storing token to database failed");
		}
		
		return userIds;
	}





}
