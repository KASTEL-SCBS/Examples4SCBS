package PCC.components.DoubleSecuredFileEncryptor;

import PCC.contracts.interfaces.IAsymmetricEncryptor;
import java.lang.Iterable;
import PCC.contracts.datatypes.File;
import PCC.contracts.interfaces.IFileEncryptor;
import PCC.contracts.interfaces.ISymmetricEncryptor;
		
public class DoubleSecuredFileEncryptor implements IFileEncryptor {
	
	private ISymmetricEncryptor iSymmetricEncryptor;
	private IAsymmetricEncryptor iAsymmetricEncryptor;
	
	public DoubleSecuredFileEncryptor(ISymmetricEncryptor iSymmetricEncryptor, IAsymmetricEncryptor iAsymmetricEncryptor) {
		// TODO: implement and verify auto-generated constructor.
	    this.iSymmetricEncryptor = iSymmetricEncryptor;
	    this.iAsymmetricEncryptor = iAsymmetricEncryptor;
	}
	
	public Iterable<Integer> generateKey(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void encryptFile(File inputFile, Iterable<Integer> key, File outputFile){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> encryptKey(Iterable<Integer> key, Iterable<Integer> publicKey){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}