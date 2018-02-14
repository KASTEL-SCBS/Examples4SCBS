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
     * Releases and declassifies the credit card details of the user.
     * 
     * @return The released and declassified credit card details.
     */
    CreditCardDetails releaseCCD(); 
	
    /**
     * Declassifies given credit card deatils for the airline.
     * 
     * @param ccDetails The credit card details to declassify.
     * @return The released and declassified credit card details.
     */
    @InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.declassifyCCDParam, ParametersAndDataPairs.declassifiedCCD})
    CreditCardDetails declassifyCCDForAirline(CreditCardDetails ccDetails);
}