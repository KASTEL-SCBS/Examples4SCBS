package calendarRepository.contracts.interfaces;

import confidentialityRepository.ParametersAndDataPairs;
import calendarRepository.contracts.datatypes.BP;
import java.lang.Iterable;
import calendarRepository.contracts.datatypes.CE;
import calendarRepository.contracts.datatypes.ID;
import confidentialityRepository.InformationFlow;
import calendarRepository.contracts.datatypes.TS;
		
public interface Calendar {
			
	Iterable<BP> getBlockedPeriods(TS from, TS to); 
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.id2metadata}) // TODO: verify annotation
	CE getFullCalendarEntry(ID id); 

}