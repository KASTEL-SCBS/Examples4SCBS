package PCC.components.OpenCVAnonymizer;

import PCC.contracts.interfaces.IVideoProcessor;
import PCC.contracts.datatypes.File;
		
public class OpenCVAnonymizer implements IVideoProcessor {
	
	private IVideoProcessor iVideoProcessor;
	
	public OpenCVAnonymizer(IVideoProcessor iVideoProcessor) {
		// TODO: implement and verify auto-generated constructor.
	    this.iVideoProcessor = iVideoProcessor;
	}
	
	public int processVideo(File inputVideo, File outputVideo){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}