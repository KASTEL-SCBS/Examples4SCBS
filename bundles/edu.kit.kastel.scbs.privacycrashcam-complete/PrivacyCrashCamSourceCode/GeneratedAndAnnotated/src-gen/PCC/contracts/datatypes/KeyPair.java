package PCC.contracts.datatypes;

import java.lang.Iterable;
import java.util.ArrayList;
		
public class KeyPair {
	
	private Iterable<Integer> privateKey;
	private Iterable<Integer> publicKey;
	
	public KeyPair() {
		// TODO: Implement and verify auto-generated constructor.
		this.privateKey = new ArrayList<Integer>();
		this.publicKey = new ArrayList<Integer>();
	}
	
	public KeyPair(Iterable<Integer> privateKey, Iterable<Integer> publicKey) {
		// TODO: Implement and verify auto-generated constructor.
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	public Iterable<Integer> getPrivateKey() {
	    return privateKey;
	}
	
	public void setPrivateKey(Iterable<Integer> privateKey) {
	    this.privateKey = privateKey;
	}
	
	public Iterable<Integer> getPublicKey() {
	    return publicKey;
	}
	
	public void setPublicKey(Iterable<Integer> publicKey) {
	    this.publicKey = publicKey;
	}
	
}