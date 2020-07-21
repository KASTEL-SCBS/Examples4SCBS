package PCC.contracts.interfaces;

import java.lang.Iterable;
		
public interface CryptographicHashing {
			
	int hash(int input, Iterable<Integer> salt); 

}