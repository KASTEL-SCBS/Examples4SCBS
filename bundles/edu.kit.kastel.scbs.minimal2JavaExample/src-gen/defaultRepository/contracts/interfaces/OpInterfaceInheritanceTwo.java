package defaultRepository.contracts.interfaces;

import defaultRepository.contracts.datatypes.CompTypeFull;
import defaultRepository.contracts.datatypes.CompTypeAllPrimitives;
		
public interface OpInterfaceInheritanceTwo extends OpInterfacePrimitiveTypes {
			
	CompTypeAllPrimitives methodInheritanceTwoA(); 
	CompTypeFull methodInheritanceTwoB(); 

}