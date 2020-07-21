package PCC.contracts.interfaces;

import java.lang.Iterable;
		
public interface IAsymmetricEncryptor {
			
	Iterable<Integer> encryptKey(Iterable<Integer> key, Iterable<Integer> publicKey); 

}