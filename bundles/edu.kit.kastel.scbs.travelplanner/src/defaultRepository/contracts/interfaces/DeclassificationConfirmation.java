package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;

/**
 * To confirm the declassification of credit card details.
 * 
 * @version 1.0, 29.11.2017
 */
public interface DeclassificationConfirmation {
	
    /**
     * Confirms the delcassification of the credit card details of the user for the given airline.
     * 
     * @param airline
     *            The airline to declassify the credit card details for.
     * @return True, if the user allows the declassification, else false.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.confirmRelease})
    boolean confirmRelease(String airline); 

}