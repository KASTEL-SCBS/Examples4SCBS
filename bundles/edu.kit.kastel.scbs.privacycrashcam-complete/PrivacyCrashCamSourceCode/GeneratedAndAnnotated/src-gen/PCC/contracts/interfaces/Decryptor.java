package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.File;
		
public interface Decryptor {
			
	Iterable<Integer> decryptKey(Iterable<Integer> key, Iterable<Integer> privateKey); 
	void decrypt(File inputFile, Iterable<Integer> key, File outputFile); 

}