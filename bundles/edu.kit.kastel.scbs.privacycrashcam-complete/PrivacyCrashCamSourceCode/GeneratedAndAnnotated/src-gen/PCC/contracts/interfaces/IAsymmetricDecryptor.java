package PCC.contracts.interfaces;

import java.lang.Iterable;
import PCC.contracts.datatypes.KeyPair;
		
public interface IAsymmetricDecryptor {
			
	KeyPair generateAsymmetricKeyPair(); 
	Iterable<Integer> decryptKey(Iterable<Integer> key, int keyAlgorithm, Iterable<Integer> privateKey); 

}