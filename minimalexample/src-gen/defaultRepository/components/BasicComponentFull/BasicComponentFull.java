package defaultRepository.components.BasicComponentFull;

import defaultRepository.contracts.interfaces.OpInterfaceEmpty;
import java.lang.Iterable;
import defaultRepository.contracts.interfaces.OpInterfaceInheritanceOne;
import defaultRepository.contracts.datatypes.CompTypeInheritance;
import defaultRepository.contracts.datatypes.CompTypeCollTypesComplex;
import defaultRepository.contracts.datatypes.CompTypeCollTypesPrimitive;
import defaultRepository.contracts.interfaces.OpInterfaceInheritanceMult;
import defaultRepository.contracts.datatypes.CompTypeAllPrimitives;
import defaultRepository.contracts.datatypes.CompTypeEmpty;
		
public class BasicComponentFull implements OpInterfaceInheritanceMult {
	
	private OpInterfaceEmpty opInterfaceEmpty;
	private OpInterfaceInheritanceOne opInterfaceInheritanceOne;
	
	public BasicComponentFull(OpInterfaceEmpty opInterfaceEmpty, OpInterfaceInheritanceOne opInterfaceInheritanceOne) {
		// TODO: implement and verify auto-generated constructor.
	    this.opInterfaceEmpty = opInterfaceEmpty;
	    this.opInterfaceInheritanceOne = opInterfaceInheritanceOne;
	}
	
	public void methodInheritanceOne(int paraInt){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public CompTypeAllPrimitives methodInheritanceOneA(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<String> methodInheritanceOneB(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void methodVoid(CompTypeCollTypesComplex paraCompType, Iterable<Boolean> paraCollType){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public CompTypeCollTypesPrimitive methodCompType(Iterable<Iterable<CompTypeEmpty>> paraCollType){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public Iterable<Integer> methodCollType(CompTypeInheritance paraCompType, Iterable<CompTypeEmpty> paraCollType){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public void methodEmpty(){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public boolean methodBoolean(boolean paraBoolean){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public char methodChar(char paraChar){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public byte methodByte(byte paraByte){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public int methodInt(int paraInt){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public long methodLong(long paraLong){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public double methodDouble(double paraDouble){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public String methodString(String paraString){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public char methodMultipleParameters(boolean paraBooleanOne, boolean paraBooleanTwo, int paraInt, String paraString){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}