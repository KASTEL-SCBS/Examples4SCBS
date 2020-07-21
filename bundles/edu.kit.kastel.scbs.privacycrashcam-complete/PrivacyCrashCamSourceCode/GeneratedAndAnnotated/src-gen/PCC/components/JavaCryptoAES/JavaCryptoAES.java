package PCC.components.JavaCryptoAES;

import PCC.contracts.interfaces.ISymmetricDecryptor;
import PCC.contracts.interfaces.IKeyConsumer;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
import PCC.contracts.interfaces.ISymmetricEncryptor;
		
public class JavaCryptoAES implements ISymmetricDecryptor, ISymmetricEncryptor, IKeyConsumer {
	
	public JavaCryptoAES() {
		// TODO: implement and verify auto-generated constructor.
	}
	
	public File decryptFile(File inputFile, Iterable<Integer> key){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> generateSymmetricKey(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File encryptFile(File inputFile, Iterable<Integer> key){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int keyAlgorithm(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}