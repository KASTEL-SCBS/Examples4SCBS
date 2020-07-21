package PCC.components.PersecutionWebinterface;

import PCC.contracts.interfaces.JudgeAccess;
import PCC.contracts.interfaces.PersecutionInformationProvision;
		
public class PersecutionWebinterface {
	
	private PersecutionInformationProvision persecutionInformationProvision;
	private JudgeAccess judgeAccess;
	
	public PersecutionWebinterface(PersecutionInformationProvision persecutionInformationProvision, JudgeAccess judgeAccess) {
		// TODO: implement and verify auto-generated constructor.
	    this.persecutionInformationProvision = persecutionInformationProvision;
	    this.judgeAccess = judgeAccess;
	}
	
}