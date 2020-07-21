package edu.kit.informatik.pcc.service.data;

import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.service.server.Main;

public abstract class SQLDB {
	   public static final String PORT = "5432";
	   public static final String HOST = "localhost";
	   public static final String DB_NAME = "PrivacyCrashCam";
	   public static final String USER = "demonstrator";
	   public static final String PASSWORD = "pccdata";
	   
		
		/**
	     * This method is used to open a connection to the database with the class attributes.
	     * If the connection fails, the server is shut down.
	     * <p>
	     * IMPORTANT: A Connection will be opened, but not closed. After calling this method,
	     * you have to close the returned connection.
	     * </p>
	     */
	    protected Connection connectToDatabase() {
	        try {
				Properties props = new Properties();
				props.setProperty("user", USER);
				props.setProperty("password", PASSWORD);
				return new org.postgresql.Driver().connect("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME, props);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            Logger.getGlobal().severe("No connection to database!");
	            Main.stopServer();
	            return null;
	        }
	    }
}
