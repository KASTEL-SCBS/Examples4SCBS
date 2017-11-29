package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
import defaultRepository.contracts.datatypes.CreditCardDetails;
		
/**
 * To release and therefore declassify the credit card details of the user for the given airline.
 * 
 * @version 1.0, 29.11.2017
 */
public interface Declassification {
			
    /**
     * Releases and declassifies the credit card details of the user for the given airline.
     * 
     * @param airline
     *            The airline to release the credit card details for.
     * @return The released and declassified credit card details.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.releaseCCDParam, ParametersAndDataPairs.declassifiedCCD})
    CreditCardDetails releaseCCD(String airline); 
	
}