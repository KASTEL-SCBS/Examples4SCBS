package defaultRepository.contracts.interfaces;

import defaultRepository.contracts.datatypes.CompTypeCompTypes;
import java.lang.Iterable;
import defaultRepository.contracts.datatypes.CompTypeInheritance;
import defaultRepository.contracts.datatypes.CompTypeEmpty;
		
public interface OpInterfaceFull extends OpInterfaceEmpty, OpInterfaceInheritanceOne {
			
	void methodEmpty(); 
	int methodPrimitiveReturn(Iterable<Iterable<CompTypeEmpty>> paramCollType, CompTypeCompTypes paramCompType); 
	Iterable<Iterable<Iterable<CompTypeEmpty>>> methodCollTypeReturn(byte paraByte); 
	CompTypeInheritance methodCompTypeReturn(); 

}