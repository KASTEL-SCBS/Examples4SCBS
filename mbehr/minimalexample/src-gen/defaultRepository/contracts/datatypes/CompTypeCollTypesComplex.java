package defaultRepository.contracts.datatypes;

import java.lang.Iterable;
import java.util.ArrayList;
import defaultRepository.contracts.datatypes.CompTypeEmpty;
		
public class CompTypeCollTypesComplex {
	
	private Iterable<Iterable<Object>> collTypeCollTypeEmpty;
	private Iterable<CompTypeEmpty> collTypeCompType;
	private Iterable<Iterable<CompTypeEmpty>> collTypeCollTypeCompType;
	private Iterable<Iterable<Iterable<CompTypeEmpty>>> collTypeCollTypeCollTypeCompType;
	
	public CompTypeCollTypesComplex() {
		// TODO: Implement and verify auto-generated constructor.
		this.collTypeCollTypeEmpty = new ArrayList<Iterable<Object>>();
		this.collTypeCompType = new ArrayList<CompTypeEmpty>();
		this.collTypeCollTypeCompType = new ArrayList<Iterable<CompTypeEmpty>>();
		this.collTypeCollTypeCollTypeCompType = new ArrayList<Iterable<Iterable<CompTypeEmpty>>>();
	}
	public CompTypeCollTypesComplex(Iterable<Iterable<Object>> collTypeCollTypeEmpty, Iterable<CompTypeEmpty> collTypeCompType, Iterable<Iterable<CompTypeEmpty>> collTypeCollTypeCompType, Iterable<Iterable<Iterable<CompTypeEmpty>>> collTypeCollTypeCollTypeCompType) {
		this.collTypeCollTypeEmpty = collTypeCollTypeEmpty;
		this.collTypeCompType = collTypeCompType;
		this.collTypeCollTypeCompType = collTypeCollTypeCompType;
		this.collTypeCollTypeCollTypeCompType = collTypeCollTypeCollTypeCompType;
	}
	
	public Iterable<Iterable<Object>> getCollTypeCollTypeEmpty() {
	    return collTypeCollTypeEmpty;
	}
	
	public void setCollTypeCollTypeEmpty(Iterable<Iterable<Object>> collTypeCollTypeEmpty) {
	    this.collTypeCollTypeEmpty = collTypeCollTypeEmpty;
	}
	
	public Iterable<CompTypeEmpty> getCollTypeCompType() {
	    return collTypeCompType;
	}
	
	public void setCollTypeCompType(Iterable<CompTypeEmpty> collTypeCompType) {
	    this.collTypeCompType = collTypeCompType;
	}
	
	public Iterable<Iterable<CompTypeEmpty>> getCollTypeCollTypeCompType() {
	    return collTypeCollTypeCompType;
	}
	
	public void setCollTypeCollTypeCompType(Iterable<Iterable<CompTypeEmpty>> collTypeCollTypeCompType) {
	    this.collTypeCollTypeCompType = collTypeCollTypeCompType;
	}
	
	public Iterable<Iterable<Iterable<CompTypeEmpty>>> getCollTypeCollTypeCollTypeCompType() {
	    return collTypeCollTypeCollTypeCompType;
	}
	
	public void setCollTypeCollTypeCollTypeCompType(Iterable<Iterable<Iterable<CompTypeEmpty>>> collTypeCollTypeCollTypeCompType) {
	    this.collTypeCollTypeCollTypeCompType = collTypeCollTypeCollTypeCompType;
	}
	
}