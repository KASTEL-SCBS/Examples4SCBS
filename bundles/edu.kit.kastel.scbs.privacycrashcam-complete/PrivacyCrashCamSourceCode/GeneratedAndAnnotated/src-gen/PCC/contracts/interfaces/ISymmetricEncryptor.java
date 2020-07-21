package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.File;
		
public interface ISymmetricEncryptor {
			
	Iterable<Integer> generateSymmetricKey(); 
	File encryptFile(File inputFile, Iterable<Integer> key); 

}