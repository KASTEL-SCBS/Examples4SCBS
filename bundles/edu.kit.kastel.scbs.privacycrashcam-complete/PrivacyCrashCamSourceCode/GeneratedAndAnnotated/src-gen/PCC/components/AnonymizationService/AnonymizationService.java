package PCC.components.AnonymizationService;

import PCC.contracts.interfaces.JudgeAccess;
import PCC.contracts.interfaces.IUserManagement;
import PCC.contracts.interfaces.IPublicKeyProvider;
import PCC.contracts.interfaces.IUserIdProvider;
import PCC.contracts.interfaces.PersecutionInformationProvision;
import PCC.contracts.interfaces.IVideoService;
import PCC.contracts.interfaces.VideoReceiving;
import PCC.contracts.datatypes.File;
import java.lang.Iterable;
import PCC.contracts.interfaces.CryptographicHashing;
import PCC.contracts.interfaces.VideoHandling;
		
public class AnonymizationService implements VideoReceiving, VideoHandling, PersecutionInformationProvision, JudgeAccess, IUserManagement {
	
	private IUserManagement iUserManagement;
	private IVideoService iVideoService;
	private IPublicKeyProvider iPublicKeyProvider;
	private IUserIdProvider iUserIdProvider;
	private CryptographicHashing cryptographicHashing;
	
	public AnonymizationService(IUserManagement iUserManagement, IVideoService iVideoService, IPublicKeyProvider iPublicKeyProvider, IUserIdProvider iUserIdProvider, CryptographicHashing cryptographicHashing) {
		// TODO: implement and verify auto-generated constructor.
	    this.iUserManagement = iUserManagement;
	    this.iVideoService = iVideoService;
	    this.iPublicKeyProvider = iPublicKeyProvider;
	    this.iUserIdProvider = iUserIdProvider;
	    this.cryptographicHashing = cryptographicHashing;
	}
	
	@EntryPoint(tag="6#_CsAvNW9SEeqa34g-QYI2IQ#_CsFAo29SEeqa34g-QYI2IQ") 
	@EntryPoint(tag="6#_CsAvNW9SEeqa34g-QYI2IQ#_CsGOwG9SEeqa34g-QYI2IQ") 
	@Source(tags={"6#_CsAvNW9SEeqa34g-QYI2IQ#_CsFAo29SEeqa34g-QYI2IQ", "6#_CsAvNW9SEeqa34g-QYI2IQ#_CsGOwG9SEeqa34g-QYI2IQ"})
	public void receiveVideo(File encryptedVideo, File encryptedMetadata, File encryptedKey, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> getVideoIds(int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	@EntryPoint(tag="5#_CsAvM29SEeqa34g-QYI2IQ#_CsGOwG9SEeqa34g-QYI2IQ") 
	@Source(tags="5#_CsAvM29SEeqa34g-QYI2IQ#_CsGOwG9SEeqa34g-QYI2IQ")
	public File downloadVideo(int videoId, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getMetadata(int videoId, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteVideo(int videoId, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public File getMetaData(int videoId, int targetUserId, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> getAllVideoIds(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int getUserIdForVideoId(int videoId, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	@EntryPoint(tag="4#_CsAIIW9SEeqa34g-QYI2IQ#_CsFAp29SEeqa34g-QYI2IQ") 
	@Source(tags="4#_CsAIIW9SEeqa34g-QYI2IQ#_CsFAp29SEeqa34g-QYI2IQ")
	public File downloadConfidentialVideo(int videoId, int userId, int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int createAccount(int email, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void deleteAccount(int authenticationToken){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	@EntryPoint(tag="3#_z2jQ8IiNEemd3YbtesF-Rw#_CsEZlW9SEeqa34g-QYI2IQ") 
	@EntryPoint(tag="10#_z2jQ8IiNEemd3YbtesF-Rw#_CsEZlW9SEeqa34g-QYI2IQ") 
	@Source(tags={"3#_z2jQ8IiNEemd3YbtesF-Rw#_CsEZlW9SEeqa34g-QYI2IQ", "10#_z2jQ8IiNEemd3YbtesF-Rw#_CsEZlW9SEeqa34g-QYI2IQ"})
	public int login(int email, int password){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}