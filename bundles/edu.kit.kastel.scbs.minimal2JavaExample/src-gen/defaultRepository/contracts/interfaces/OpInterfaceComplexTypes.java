package defaultRepository.contracts.interfaces;

import java.lang.Iterable;
import defaultRepository.contracts.datatypes.CompTypeInheritance;
import defaultRepository.contracts.datatypes.CompTypeCollTypesComplex;
import defaultRepository.contracts.datatypes.CompTypeCollTypesPrimitive;
import defaultRepository.contracts.datatypes.CompTypeEmpty;
		
public interface OpInterfaceComplexTypes {
			
	void methodVoid(CompTypeCollTypesComplex paraCompType, Iterable<Boolean> paraCollType); 
	CompTypeCollTypesPrimitive methodCompType(Iterable<Iterable<CompTypeEmpty>> paraCollType); 
	Iterable<Integer> methodCollType(CompTypeInheritance paraCompType, Iterable<CompTypeEmpty> paraCollType); 

}