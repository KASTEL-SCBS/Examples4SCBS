package defaultRepository.contracts.interfaces;

import confidentialityRepository.InformationFlow;
import confidentialityRepository.ParametersAndDataPairs;
import defaultRepository.contracts.datatypes.FlightOffer;
		
/**
 * Management of airline functionality.
 *
 * @version 1.0, 29.11.2017
 */
public interface AirlineAdministration {
			
    /**
     * Sets the available flights offers of the implementing class.
     * 
     * @param flights
     *            The flight offers to be available.
     */
	@InformationFlow(parametersAndDataPairs = {ParametersAndDataPairs.setFlightOffers})
	void setAvailableFlights(Iterable<FlightOffer> flights); 

}