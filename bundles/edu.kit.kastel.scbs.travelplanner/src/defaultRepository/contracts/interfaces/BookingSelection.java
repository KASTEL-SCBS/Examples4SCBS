package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;
		
/**
 * For making a flight offer selection and getting flights offers.
 * 
 * @version 1.0, 29.11.2017
 */
public interface BookingSelection {
			
    /**
     * Books the given flight offer.
     * 
     * @param flightOffer
     *            The flight offer to book.
     * @return True, if the flight offer was successfully booked.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.bookSelectedParam, ParametersAndDataPairs.bookSelectedConfirmation})
    boolean bookSelected(FlightOffer flightOffer);

    /**
     * Gets flights offers matching the given request data.
     * 
     * @param requestData
     *            The data the flight offers should have.
     * @return The flights offers with the requested data.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.getFlightOffersTP})
	Iterable<FlightOffer> getFlightOffers(RequestData requestData); 

}