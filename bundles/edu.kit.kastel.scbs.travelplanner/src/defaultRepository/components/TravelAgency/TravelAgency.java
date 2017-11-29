package defaultRepository.components.TravelAgency;

import defaultRepository.components.Airline.Airline;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;
import defaultRepository.contracts.interfaces.Comission;
import defaultRepository.contracts.interfaces.FlightOffers;

/**
 * The travel agency provides the user with flight offers (of an airline) and gets payed a
 * commission from the airline for doing it.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class TravelAgency implements FlightOffers, Comission {

    // automatically generated:
    // @ cluster AgencyServerCluster;
    // @ \lowIn flightOffers.getFlightOffers.\term(\result),
    // @     this.getFlightOffers.\call(requestData),
    // @     this.payCommission.\call(offerId)
    // @ \lowOut flightOffers.getFlightOffers.\call(requestData),
    // @     this.getFlightOffers.\term(\result),
    // @     this.payCommission.\term(\result)
    // @ \visible \nothing
    // @ \lowState AgencyServer;
    // @
    // @ model \seq AgencyServer

    // automatically generated:
    // @ cluster AirlineServerCluster;
    // @ \lowIn flightOffers.getFlightOffers.\term(\result),
    // @     this.getFlightOffers.\call(requestData),
    // @     this.payCommission.\call(offerId)
    // @ \lowOut flightOffers.getFlightOffers.\call(requestData),
    // @     this.getFlightOffers.\term(\result),
    // @     this.payCommission.\term(\result)
    // @ \visible \nothing
    // @ \lowState AirlineServer;
    // @
    // @ model \seq AirlineServer

    // automatically generated:
    // @ cluster MobilePhoneCluster;
    // @ \lowIn flightOffers.getFlightOffers.\term(\result),
    // @     this.getFlightOffers.\call(requestData)
    // @ \lowOut flightOffers.getFlightOffers.\call(requestData),
    // @     this.getFlightOffers.\term(\result)
    // @ \visible \nothing
    // @ \lowState MobilePhone;
    // @
    // @ model \seq MobilePhone

    /**
     * The singleton instance.
     */
    private static TravelAgency instance;

    /**
     * To get the flight offers from.
     */
    private FlightOffers flightOffers;

    /**
     * Creates a new travel agency.
     */
    private TravelAgency() {
        //
    }

    /**
     * Gets the singleton of this class. Must be initialized before used.
     * 
     * @return The singleton.
     */
    public static TravelAgency getInstance() {
        if (instance == null) {
            instance = new TravelAgency();
        }
        return instance;
    }

    /**
     * Initializes this class, i.e. sets all necessary fields.
     */
    public static void initialize() {
        getInstance();
        instance.flightOffers = Airline.getInstance();
    }

    @Override
    public Iterable<FlightOffer> getFlightOffers(RequestData requestData) {
        return flightOffers.getFlightOffers(requestData);
    }

    @Override
    public boolean payCommission(int offerId) {
        return true; // confirm payment
    }
}