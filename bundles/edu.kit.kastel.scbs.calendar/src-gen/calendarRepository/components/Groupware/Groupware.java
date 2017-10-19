package calendarRepository.components.Groupware;

import calendarRepository.contracts.datatypes.BP;
import java.lang.Iterable;
import calendarRepository.contracts.datatypes.CE;
import calendarRepository.contracts.datatypes.ID;
import calendarRepository.contracts.interfaces.Calendar;
import calendarRepository.contracts.datatypes.TS;
		
public class Groupware implements Calendar {
	
	public Groupware() {
		// TODO: implement and verify auto-generated constructor.
	}
	
	public Iterable<BP> getBlockedPeriods(TS from, TS to){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
	public CE getFullCalendarEntry(ID id){
		// TODO: implement and verify auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}
	
}