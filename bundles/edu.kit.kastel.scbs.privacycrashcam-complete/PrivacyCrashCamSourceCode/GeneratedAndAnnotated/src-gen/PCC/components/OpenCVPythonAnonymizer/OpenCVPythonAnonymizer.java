package PCC.components.OpenCVPythonAnonymizer;

import PCC.contracts.interfaces.IVideoProcessor;
import PCC.contracts.datatypes.File;
import PCC.contracts.interfaces.IFileManager;
		
public class OpenCVPythonAnonymizer implements IVideoProcessor {
	
	private IVideoProcessor iVideoProcessor;
	private IFileManager iFileManager;
	
	public OpenCVPythonAnonymizer(IVideoProcessor iVideoProcessor, IFileManager iFileManager) {
		// TODO: implement and verify auto-generated constructor.
	    this.iVideoProcessor = iVideoProcessor;
	    this.iFileManager = iFileManager;
	}
	
	public int processVideo(File inputVideo, File outputVideo){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}