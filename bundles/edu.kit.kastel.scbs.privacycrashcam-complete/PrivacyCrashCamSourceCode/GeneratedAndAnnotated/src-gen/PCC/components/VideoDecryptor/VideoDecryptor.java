package PCC.components.VideoDecryptor;

import PCC.contracts.interfaces.Decryptor;
import PCC.contracts.interfaces.IPublicKeyProvider;
import PCC.contracts.interfaces.IVideoDecryptor;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
import PCC.contracts.interfaces.IKeyStorage;
import PCC.contracts.interfaces.IAsymmetricDecryptor;
		
public class VideoDecryptor implements IVideoDecryptor, IPublicKeyProvider {
	
	private IKeyStorage iKeyStorage;
	private Decryptor decryptor;
	private IAsymmetricDecryptor iAsymmetricDecryptor;
	
	public VideoDecryptor(IKeyStorage iKeyStorage, Decryptor decryptor, IAsymmetricDecryptor iAsymmetricDecryptor) {
		// TODO: implement and verify auto-generated constructor.
	    this.iKeyStorage = iKeyStorage;
	    this.decryptor = decryptor;
	    this.iAsymmetricDecryptor = iAsymmetricDecryptor;
	}
	
	public void decrypt(File inputVideo, File inputMetadata, Iterable<Integer> keyData, File outputVideo, File outputMetadata){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> getPublicKey(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}