package edu.kit.informatik.pcc.service.authentication;

import java.util.Collection;

public interface IUserIdProvider {
	public final static int invalidId = -1;
	
	public int getUserId(String authenticationToken);
	public Collection<Integer> getUserIds(int userId);
}
