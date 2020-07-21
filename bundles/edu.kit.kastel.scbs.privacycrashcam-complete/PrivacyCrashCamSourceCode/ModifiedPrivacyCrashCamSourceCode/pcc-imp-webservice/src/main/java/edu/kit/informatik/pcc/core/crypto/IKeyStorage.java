package edu.kit.informatik.pcc.core.crypto;

import java.security.Key;

public interface IKeyStorage {
	public void storeKey(String id, Key key);
	public Key loadKey(String id);
}
