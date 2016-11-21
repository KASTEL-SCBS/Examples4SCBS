package defaultRepository.contracts.datatypes;

import java.lang.Iterable;
import java.util.ArrayList;
		
public class CompTypeCollTypesPrimitive {
	
	private Iterable<Object> collTypeEmpty;
	private Iterable<Integer> collTypeInt;
	private Iterable<String> collTypteString;
	private Iterable<Double> collTypeDouble;
	private Iterable<Long> collTypeLong;
	private Iterable<Character> collTypeChar;
	private Iterable<Boolean> collTypeBoolean;
	private Iterable<Byte> collTypeByte;
	
	public CompTypeCollTypesPrimitive() {
		// TODO: Implement and verify auto-generated constructor.
		this.collTypeEmpty = new ArrayList<Object>();
		this.collTypeInt = new ArrayList<Integer>();
		this.collTypteString = new ArrayList<String>();
		this.collTypeDouble = new ArrayList<Double>();
		this.collTypeLong = new ArrayList<Long>();
		this.collTypeChar = new ArrayList<Character>();
		this.collTypeBoolean = new ArrayList<Boolean>();
		this.collTypeByte = new ArrayList<Byte>();
	}
	public CompTypeCollTypesPrimitive(Iterable<Object> collTypeEmpty, Iterable<Integer> collTypeInt, Iterable<String> collTypteString, Iterable<Double> collTypeDouble, Iterable<Long> collTypeLong, Iterable<Character> collTypeChar, Iterable<Boolean> collTypeBoolean, Iterable<Byte> collTypeByte) {
		this.collTypeEmpty = collTypeEmpty;
		this.collTypeInt = collTypeInt;
		this.collTypteString = collTypteString;
		this.collTypeDouble = collTypeDouble;
		this.collTypeLong = collTypeLong;
		this.collTypeChar = collTypeChar;
		this.collTypeBoolean = collTypeBoolean;
		this.collTypeByte = collTypeByte;
	}
	
	public Iterable<Object> getCollTypeEmpty() {
	    return collTypeEmpty;
	}
	
	public void setCollTypeEmpty(Iterable<Object> collTypeEmpty) {
	    this.collTypeEmpty = collTypeEmpty;
	}
	
	public Iterable<Integer> getCollTypeInt() {
	    return collTypeInt;
	}
	
	public void setCollTypeInt(Iterable<Integer> collTypeInt) {
	    this.collTypeInt = collTypeInt;
	}
	
	public Iterable<String> getCollTypteString() {
	    return collTypteString;
	}
	
	public void setCollTypteString(Iterable<String> collTypteString) {
	    this.collTypteString = collTypteString;
	}
	
	public Iterable<Double> getCollTypeDouble() {
	    return collTypeDouble;
	}
	
	public void setCollTypeDouble(Iterable<Double> collTypeDouble) {
	    this.collTypeDouble = collTypeDouble;
	}
	
	public Iterable<Long> getCollTypeLong() {
	    return collTypeLong;
	}
	
	public void setCollTypeLong(Iterable<Long> collTypeLong) {
	    this.collTypeLong = collTypeLong;
	}
	
	public Iterable<Character> getCollTypeChar() {
	    return collTypeChar;
	}
	
	public void setCollTypeChar(Iterable<Character> collTypeChar) {
	    this.collTypeChar = collTypeChar;
	}
	
	public Iterable<Boolean> getCollTypeBoolean() {
	    return collTypeBoolean;
	}
	
	public void setCollTypeBoolean(Iterable<Boolean> collTypeBoolean) {
	    this.collTypeBoolean = collTypeBoolean;
	}
	
	public Iterable<Byte> getCollTypeByte() {
	    return collTypeByte;
	}
	
	public void setCollTypeByte(Iterable<Byte> collTypeByte) {
	    this.collTypeByte = collTypeByte;
	}
	
}