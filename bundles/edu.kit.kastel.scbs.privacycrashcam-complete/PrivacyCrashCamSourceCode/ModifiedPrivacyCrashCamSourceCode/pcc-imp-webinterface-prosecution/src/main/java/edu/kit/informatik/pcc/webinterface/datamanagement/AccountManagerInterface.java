package edu.kit.informatik.pcc.webinterface.datamanagement;

import com.vaadin.server.VaadinSession;

public interface AccountManagerInterface {
	public boolean createAccount(String mail, String password);
	public boolean authenticateAccount(String mail, String password);
	public boolean changeAccount(String password, String mailNew,String passwordNew);
	public boolean deleteAccount();
}
