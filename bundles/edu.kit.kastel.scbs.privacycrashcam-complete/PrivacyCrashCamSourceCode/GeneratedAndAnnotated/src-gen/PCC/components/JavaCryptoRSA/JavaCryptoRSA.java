package PCC.components.JavaCryptoRSA;

import PCC.contracts.interfaces.IAsymmetricEncryptor;
import PCC.contracts.interfaces.IKeyConsumer;
import java.lang.Iterable;
import PCC.contracts.interfaces.IAsymmetricDecryptor;
import PCC.contracts.datatypes.KeyPair;
		
public class JavaCryptoRSA implements IAsymmetricDecryptor, IAsymmetricEncryptor, IKeyConsumer {
	
	public JavaCryptoRSA() {
		// TODO: implement and verify auto-generated constructor.
	}
	
	public KeyPair generateAsymmetricKeyPair(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> decryptKey(Iterable<Integer> key, int keyAlgorithm, Iterable<Integer> privateKey){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> encryptKey(Iterable<Integer> key, Iterable<Integer> publicKey){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int keyAlgorithm(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}