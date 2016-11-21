package defaultRepository.contracts.datatypes;

import defaultRepository.contracts.datatypes.CompTypeCollTypesPrimitive;
import defaultRepository.contracts.datatypes.CompTypeAllPrimitives;
import defaultRepository.contracts.datatypes.CompTypeEmpty;
		
public class CompTypeCompTypes {
	
	private CompTypeEmpty compTypeEmpty;
	private CompTypeAllPrimitives compTypeAllPrimitives;
	private CompTypeCollTypesPrimitive compTypeCollTypesPrimitive;
	
	public CompTypeCompTypes() {
		// TODO: Implement and verify auto-generated constructor.
		this.compTypeEmpty = new CompTypeEmpty();
		this.compTypeAllPrimitives = new CompTypeAllPrimitives();
		this.compTypeCollTypesPrimitive = new CompTypeCollTypesPrimitive();
	}
	public CompTypeCompTypes(CompTypeEmpty compTypeEmpty, CompTypeAllPrimitives compTypeAllPrimitives, CompTypeCollTypesPrimitive compTypeCollTypesPrimitive) {
		this.compTypeEmpty = compTypeEmpty;
		this.compTypeAllPrimitives = compTypeAllPrimitives;
		this.compTypeCollTypesPrimitive = compTypeCollTypesPrimitive;
	}
	
	public CompTypeEmpty getCompTypeEmpty() {
	    return compTypeEmpty;
	}
	
	public void setCompTypeEmpty(CompTypeEmpty compTypeEmpty) {
	    this.compTypeEmpty = compTypeEmpty;
	}
	
	public CompTypeAllPrimitives getCompTypeAllPrimitives() {
	    return compTypeAllPrimitives;
	}
	
	public void setCompTypeAllPrimitives(CompTypeAllPrimitives compTypeAllPrimitives) {
	    this.compTypeAllPrimitives = compTypeAllPrimitives;
	}
	
	public CompTypeCollTypesPrimitive getCompTypeCollTypesPrimitive() {
	    return compTypeCollTypesPrimitive;
	}
	
	public void setCompTypeCollTypesPrimitive(CompTypeCollTypesPrimitive compTypeCollTypesPrimitive) {
	    this.compTypeCollTypesPrimitive = compTypeCollTypesPrimitive;
	}
	
}