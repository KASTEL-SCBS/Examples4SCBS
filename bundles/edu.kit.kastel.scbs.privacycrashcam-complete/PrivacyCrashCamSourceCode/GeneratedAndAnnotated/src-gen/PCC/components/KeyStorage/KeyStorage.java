package PCC.components.KeyStorage;

import java.lang.Iterable;
import PCC.contracts.interfaces.IKeyStorage;
import PCC.contracts.interfaces.IFileManager;
		
public class KeyStorage implements IKeyStorage {
	
	private IFileManager iFileManager;
	
	public KeyStorage(IFileManager iFileManager) {
		// TODO: implement and verify auto-generated constructor.
	    this.iFileManager = iFileManager;
	}
	
	public void storeKey(int id, Iterable<Integer> value){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> loadKey(int id){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}