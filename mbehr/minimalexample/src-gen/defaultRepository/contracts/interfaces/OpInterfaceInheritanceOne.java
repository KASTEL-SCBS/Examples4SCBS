package defaultRepository.contracts.interfaces;

import java.lang.Iterable;
import defaultRepository.contracts.datatypes.CompTypeAllPrimitives;
		
public interface OpInterfaceInheritanceOne extends OpInterfacePrimitiveTypes {
			
	CompTypeAllPrimitives methodInheritanceOneA(); 
	Iterable<String> methodInheritanceOneB(); 

}