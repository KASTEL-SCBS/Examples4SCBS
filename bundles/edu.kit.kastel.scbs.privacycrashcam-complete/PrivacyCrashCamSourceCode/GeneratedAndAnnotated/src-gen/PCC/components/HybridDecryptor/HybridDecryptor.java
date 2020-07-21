package PCC.components.HybridDecryptor;

import PCC.contracts.interfaces.ISymmetricDecryptor;
import PCC.contracts.interfaces.Decryptor;
import PCC.contracts.interfaces.IKeyConsumer;
import java.lang.Iterable;
import PCC.contracts.datatypes.File;
import PCC.contracts.interfaces.IAsymmetricDecryptor;
		
public class HybridDecryptor implements Decryptor {
	
	private ISymmetricDecryptor iSymmetricDecryptor;
	private IAsymmetricDecryptor iAsymmetricDecryptor;
	private IKeyConsumer iKeyConsumer;
	
	public HybridDecryptor(ISymmetricDecryptor iSymmetricDecryptor, IAsymmetricDecryptor iAsymmetricDecryptor, IKeyConsumer iKeyConsumer) {
		// TODO: implement and verify auto-generated constructor.
	    this.iSymmetricDecryptor = iSymmetricDecryptor;
	    this.iAsymmetricDecryptor = iAsymmetricDecryptor;
	    this.iKeyConsumer = iKeyConsumer;
	}
	
	public Iterable<Integer> decryptKey(Iterable<Integer> key, Iterable<Integer> privateKey){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	@Sink(tags={"0#_WqXt0HpEEequULS08eP9cA#_CsFAo29SEeqa34g-QYI2IQ", "2#_WqXt2XpEEequULS08eP9cA#_CsFAo29SEeqa34g-QYI2IQ", "6#_CsAvNW9SEeqa34g-QYI2IQ#_CsFAo29SEeqa34g-QYI2IQ", "7#_Wqg30XpEEequULS08eP9cA#_CsFAo29SEeqa34g-QYI2IQ"})
	public void decrypt(File inputFile, Iterable<Integer> key, File outputFile){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}