package PCC.components.SettingsManager;

import PCC.contracts.datatypes.Settings;
import PCC.contracts.interfaces.ISimpleValueStorage;
import PCC.contracts.interfaces.ISettingsManager;
		
public class SettingsManager implements ISettingsManager {
	
	private ISimpleValueStorage iSimpleValueStorage;
	
	public SettingsManager(ISimpleValueStorage iSimpleValueStorage) {
		// TODO: implement and verify auto-generated constructor.
	    this.iSimpleValueStorage = iSimpleValueStorage;
	}
	
	public void storeSettings(Settings settings){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Settings loadSettings(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}