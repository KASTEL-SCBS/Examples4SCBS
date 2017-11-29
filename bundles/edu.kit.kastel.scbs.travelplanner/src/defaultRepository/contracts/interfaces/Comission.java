package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
		
/**
 * To be able to pay a commission for a given flight.
 * 
 * @version 1.0, 29.11.2017
 */
public interface Comission {
			
    /**
     * Pays the commission for the flight offer with the given id.
     * 
     * @param offerId
     *            The id of the flight offer to pay the commission for.
     * @return True, if the commission was successfully payed.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.payCommision})
	boolean payCommission(int offerId); 

}