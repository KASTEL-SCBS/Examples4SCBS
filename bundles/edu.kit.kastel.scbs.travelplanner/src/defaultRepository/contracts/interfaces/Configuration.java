package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
import defaultRepository.contracts.datatypes.CreditCardDetails;
		
/**
 * To configure the credit card details of the user, i.e. setting them.
 * 
 * @version 1.0, 29.11.2017
 */
public interface Configuration {
			
    /**
     * Sets the credit card details of the user.
     * 
     * @param ccd
     *            The credit card details of the user.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.setCCD})
	void setCreditCard(CreditCardDetails ccd); 

}