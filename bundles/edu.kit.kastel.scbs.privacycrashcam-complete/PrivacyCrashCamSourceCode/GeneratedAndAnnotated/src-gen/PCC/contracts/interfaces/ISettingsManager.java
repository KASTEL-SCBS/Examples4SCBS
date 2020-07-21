package PCC.contracts.interfaces;

import PCC.contracts.datatypes.Settings;
		
public interface ISettingsManager {
			
	void storeSettings(Settings settings); 
	Settings loadSettings(); 

}