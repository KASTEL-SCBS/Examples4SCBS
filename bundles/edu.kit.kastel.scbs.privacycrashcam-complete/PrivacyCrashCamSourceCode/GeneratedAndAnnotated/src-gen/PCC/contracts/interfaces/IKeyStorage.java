package PCC.contracts.interfaces;

import java.lang.Iterable;
		
public interface IKeyStorage {
			
	void storeKey(int id, Iterable<Integer> value); 
	Iterable<Integer> loadKey(int id); 

}