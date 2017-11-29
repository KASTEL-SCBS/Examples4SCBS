package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
import defaultRepository.contracts.datatypes.CreditCardDetails;
		
/**
 * Provides the functionality to book flights.
 * 
 * @version 1.0, 29.11.2017
 */
public interface Booking {
			
    /**
     * Book a flight with the given id using the given declassified credit card details.
     * 
     * @param offerId
     *            The id of the flight offer to book.
     * @param ccd_decl
     *            The declassified credit card details to use for the purchase.
     * @return True, if the booking was successful, else false.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.bookFlightParam0, ParametersAndDataPairs.bookFlightParam1, ParametersAndDataPairs.bookFlightConfirmation})
	boolean bookFlight(int offerId, CreditCardDetails ccd_decl); 

}