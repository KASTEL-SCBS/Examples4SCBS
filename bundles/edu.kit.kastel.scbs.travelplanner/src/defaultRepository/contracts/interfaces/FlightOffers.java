package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;

/**
 * To provide flight offers matching the given request data.
 * 
 * @version 1.0, 29.11.2017
 */
public interface FlightOffers {
			
    /**
     * Gets the flight offers matching the requested data.
     * 
     * @param requestData
     *            The data the returned flight offers should have.
     * @return The flight offers.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.getFlightOffersTA})
	Iterable<FlightOffer> getFlightOffers(RequestData requestData); 

}