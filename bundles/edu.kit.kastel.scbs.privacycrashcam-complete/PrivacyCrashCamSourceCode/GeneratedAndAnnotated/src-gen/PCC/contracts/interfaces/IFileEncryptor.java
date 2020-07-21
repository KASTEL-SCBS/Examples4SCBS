package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.File;
		
public interface IFileEncryptor {
			
	Iterable<Integer> generateKey(); 
	void encryptFile(File inputFile, Iterable<Integer> key, File outputFile); 
	Iterable<Integer> encryptKey(Iterable<Integer> key, Iterable<Integer> publicKey); 

}