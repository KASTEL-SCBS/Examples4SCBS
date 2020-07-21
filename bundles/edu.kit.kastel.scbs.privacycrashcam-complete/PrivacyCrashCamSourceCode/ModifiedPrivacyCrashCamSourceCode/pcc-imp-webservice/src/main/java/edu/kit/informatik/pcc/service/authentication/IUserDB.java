package edu.kit.informatik.pcc.service.authentication;

import java.util.Collection;

/**
 * An IUserDB instance manages a user database.
 * A user is uniquely identified by either his userId or his email.
 * @author Jan Wittler
 */
public interface IUserDB {
	/**
	 * Creates a new user account in the database.
	 * If the provided mail address is already associated with some user, this method does nothing.
	 * @param email The user's email. Must be not null and a valid mail address.
	 * @param password The user's password. Must be not null and not empty.
	 */
	public void createUser(String email, String password);
	/**
	 * Returns the id of the user associated with the provided email.
	 * If no user is associated with the provided email <code>IUserIdProvider.invalidId</code> is returned.
	 * @param email The user's email.
	 * @return The user's id or <code>IUserIdProvider.invalidId</code> if no user is associated with the provided email.
	 */
	public int getUserIdByMail(String email);
	public Boolean validatePassword(int userId, String password);
	public void deleteAccount(int userId);
	public Collection<Integer> getUserIds(int userId);
}
