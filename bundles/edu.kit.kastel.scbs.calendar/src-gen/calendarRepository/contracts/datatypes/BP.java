package calendarRepository.contracts.datatypes;

import calendarRepository.contracts.datatypes.ID;
import calendarRepository.contracts.datatypes.TS;
		
public class BP {
	
	public ID entryID;
	public TS start;
	public TS end;
	
	public BP() {
		// TODO: Implement and verify auto-generated constructor.
		this.entryID = new ID();
		this.start = new TS();
		this.end = new TS();
	}
	
	public BP(ID entryID, TS start, TS end) {
		// TODO: Implement and verify auto-generated constructor.
		this.entryID = entryID;
		this.start = start;
		this.end = end;
	}
	
}