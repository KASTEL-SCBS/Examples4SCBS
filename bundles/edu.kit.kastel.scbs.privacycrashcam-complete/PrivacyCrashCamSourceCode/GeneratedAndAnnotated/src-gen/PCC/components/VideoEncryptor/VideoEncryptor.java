package PCC.components.VideoEncryptor;

import PCC.contracts.interfaces.IPublicKeyProvider;
import PCC.contracts.interfaces.IVideoEncryptor;
import PCC.contracts.datatypes.File;
import PCC.contracts.interfaces.IFileEncryptor;
import PCC.contracts.interfaces.ISymmetricEncryptor;
		
public class VideoEncryptor implements IVideoEncryptor {
	
	private IFileEncryptor iFileEncryptor;
	private IPublicKeyProvider iPublicKeyProvider;
	private ISymmetricEncryptor iSymmetricEncryptor;
	
	public VideoEncryptor(IFileEncryptor iFileEncryptor, IPublicKeyProvider iPublicKeyProvider, ISymmetricEncryptor iSymmetricEncryptor) {
		// TODO: implement and verify auto-generated constructor.
	    this.iFileEncryptor = iFileEncryptor;
	    this.iPublicKeyProvider = iPublicKeyProvider;
	    this.iSymmetricEncryptor = iSymmetricEncryptor;
	}
	
	public void encrypt(File inputVideo, File inputMetadata, File outputVideo, File outputMetadata){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}