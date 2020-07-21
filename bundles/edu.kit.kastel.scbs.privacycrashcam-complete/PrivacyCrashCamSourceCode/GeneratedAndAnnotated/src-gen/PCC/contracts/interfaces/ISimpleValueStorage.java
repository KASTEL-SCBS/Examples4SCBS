package PCC.contracts.interfaces;

public interface ISimpleValueStorage {
			
	void putString(int key, int value); 
	int getString(int key); 

}