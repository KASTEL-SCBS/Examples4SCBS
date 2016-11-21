package defaultRepository.contracts.interfaces;

import java.lang.Iterable;
		
public interface OpInterfaceDiamond extends OpInterfaceInheritanceTwo, OpInterfaceInheritanceOne {
			
	Iterable<Object> methodDiamond(); 

}