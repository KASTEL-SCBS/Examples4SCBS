package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
import java.lang.Iterable;
		
public interface ISymmetricDecryptor {
			
	File decryptFile(File inputFile, Iterable<Integer> key); 

}