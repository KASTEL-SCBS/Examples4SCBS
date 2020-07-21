package PCC.contracts.interfaces;

import PCC.contracts.datatypes.File;
		
public interface IVideoProcessor {
			
	int processVideo(File inputVideo, File outputVideo); 

}