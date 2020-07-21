package edu.kit.informatik.pcc.service.videoprocessing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.service.data.SQLDB;

public class VideoServiceSQLAccess extends SQLDB {

	public boolean persistVideoInformation(int userId, int videoId){
		Connection connection = connectToDatabase();
		if (connection == null) {
			return false;
		}
		
		try {
			Statement stmt = connection.createStatement();
			String sqlCommand = "insert into \"video\" (id, user_Id) values (" + videoId + ",'" + userId + "');";
			stmt.executeUpdate(sqlCommand);
			stmt.close();
			connection.close();
		} catch (NullPointerException | SQLException e) {
			Logger.getGlobal().warning("Inserting video and meta in database failed!");
			return false;
		}
		
		return true;
	}
	
	public boolean videoIdOccupied(int videoId) {
		Connection connection = connectToDatabase();
		boolean available = false;
		
		
		if (connection == null) {
			return false;
		}
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select \"id\" from \"video\" where \"video\".\"id\" = '" + videoId + "'");
			
			if(rs.next()) {
				available = true;
			}
			
			stmt.close();
			connection.close();
		} catch (NullPointerException | SQLException e) {
			Logger.getGlobal().warning("Inserting video and meta in database failed!");
			return false;
		}
		
		return available;
	}
	
}
