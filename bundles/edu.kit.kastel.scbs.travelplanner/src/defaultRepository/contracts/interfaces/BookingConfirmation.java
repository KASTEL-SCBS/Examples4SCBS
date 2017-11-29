package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;

/**
 * For confirmation of a flight booking.
 * 
 * @version 1.0, 29.11.2017
 */
public interface BookingConfirmation {
	
    /**
     * Confirms the booking of the flight offer (of the given airline) with the given offer id.
     * 
     * @param offerId
     *            The id of the flight offer to book.
     * @param airline
     *            The airline the flight offer belongs to.
     * @return True, if the booking is confirmed, else false.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.confirmBooking})
    boolean confirmBooking(int offerId, String airline); 

}