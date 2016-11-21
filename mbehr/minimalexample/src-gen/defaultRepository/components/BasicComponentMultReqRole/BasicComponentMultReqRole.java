package defaultRepository.components.BasicComponentMultReqRole;

import defaultRepository.contracts.interfaces.OpInterfaceFull;
import defaultRepository.contracts.interfaces.OpInterfaceInheritanceOne;
import defaultRepository.contracts.interfaces.OpInterfacePrimitiveTypes;
		
public class BasicComponentMultReqRole {
	
	private OpInterfacePrimitiveTypes opInterfacePrimitiveTypes;
	private OpInterfaceInheritanceOne opInterfaceInheritanceOne;
	private OpInterfaceFull opInterfaceFull;
	
	public BasicComponentMultReqRole(OpInterfacePrimitiveTypes opInterfacePrimitiveTypes, OpInterfaceInheritanceOne opInterfaceInheritanceOne, OpInterfaceFull opInterfaceFull) {
		// TODO: implement and verify auto-generated constructor.
	    this.opInterfacePrimitiveTypes = opInterfacePrimitiveTypes;
	    this.opInterfaceInheritanceOne = opInterfaceInheritanceOne;
	    this.opInterfaceFull = opInterfaceFull;
	}
	
}