package edu.kit.informatik.pcc.service.authentication;

import java.sql.Connection;

public interface PasswordAuthentication {

	public boolean authenticatePassword(Connection connection, int userId, String inputHash);
}
