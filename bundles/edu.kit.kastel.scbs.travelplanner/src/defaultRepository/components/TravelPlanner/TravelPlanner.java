package defaultRepository.components.TravelPlanner;

import defaultRepository.components.Airline.Airline;
import defaultRepository.components.CreditCardCenter.CreditCardCenter;
import defaultRepository.components.TravelAgency.TravelAgency;
import defaultRepository.contracts.datatypes.CreditCardDetails;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;
import defaultRepository.contracts.interfaces.Booking;
import defaultRepository.contracts.interfaces.BookingConfirmation;
import defaultRepository.contracts.interfaces.BookingSelection;
import defaultRepository.contracts.interfaces.Declassification;
import defaultRepository.contracts.interfaces.FlightOffers;

/**
 * The travel planner provides the user with flight offers from an airline by getting them from a
 * travel agency.
 * 
 * The user can then select a flight offer to book by directly communicating with the airline. The
 * user has to release and declassify his credit card details in the process in order to be able to
 * book the selected flight.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class TravelPlanner implements BookingSelection, BookingConfirmation {

    //automatically generated:
	//@ cluster AgencyServerCluster;
	//@ \lowIn flightOffers.getFlightOffers.\term(\result),
	//@     this.getFlightOffers.\call(requestData),
	//@     this.bookSelected.\call(flightOffer),
	//@     this.confirmBooking.\call(offerId, airline)
	//@ \lowOut booking.bookFlight.\call(offerId),
	//@     flightOffers.getFlightOffers.\call(requestData),
	//@     this.getFlightOffers.\term(\result),
	//@     this.bookSelected.\term(\result),
	//@     this.confirmBooking.\term(\result)
	//@ \visible \nothing
	//@ \lowState AgencyServer;
	//@ 
	//@ model \seq AgencyServer

	//automatically generated:
	//@ cluster AirlineServerCluster;
	//@ \lowIn booking.bookFlight.\term(\result),
	//@     declassification.releaseCCD.\term(\result),
	//@     flightOffers.getFlightOffers.\term(\result),
	//@     this.getFlightOffers.\call(requestData),
	//@     this.bookSelected.\call(flightOffer),
	//@     this.confirmBooking.\call(offerId, airline)
	//@ \lowOut booking.bookFlight.\call(ccd_decl, offerId),
	//@     flightOffers.getFlightOffers.\call(requestData),
	//@     this.getFlightOffers.\term(\result),
	//@     this.bookSelected.\term(\result),
	//@     this.confirmBooking.\term(\result)
	//@ \visible \nothing
	//@ \lowState AirlineServer;
	//@ 
	//@ model \seq AirlineServer

	//automatically generated:
	//@ cluster MobilePhoneCluster;
	//@ \lowIn booking.bookFlight.\term(\result),
	//@     declassification.releaseCCD.\term(\result),
	//@     flightOffers.getFlightOffers.\term(\result),
	//@     this.getFlightOffers.\call(requestData),
	//@     this.bookSelected.\call(flightOffer),
	//@     this.confirmBooking.\call(offerId, airline)
	//@ \lowOut booking.bookFlight.\call(ccd_decl, offerId),
	//@     declassification.releaseCCD.\call(airline),
	//@     flightOffers.getFlightOffers.\call(requestData),
	//@     this.getFlightOffers.\term(\result),
	//@     this.bookSelected.\term(\result),
	//@     this.confirmBooking.\term(\result)
	//@ \visible \nothing
	//@ \lowState MobilePhone;
	//@ 
	//@ model \seq MobilePhone
	
    /**
     * The singleton instance.
     */
    private static TravelPlanner instance;

    /**
     * To get the flight offers from.
     */
    private FlightOffers flightOffers;

    /**
     * To book the selected flight offer.
     */
    private Booking booking;

    /**
     * To delcassify the credit card details of the user.
     */
    private Declassification declassification;

    /**
     * Creates a new travel planner for getting flight offers and booking them.
     */
    private TravelPlanner() {
        //
    }

    /**
     * The singleton instance.
     */
    public static TravelPlanner getInstance() {
        if (instance == null) {
            instance = new TravelPlanner();
        }
        return instance;
    }

    /**
     * Initializes this class, i.e. sets all necessary fields.
     */
    public static void initialize() {
        getInstance();
        instance.flightOffers = TravelAgency.getInstance();
        instance.booking = Airline.getInstance();
        instance.declassification = CreditCardCenter.getInstance();
    }

    @Override
    public boolean bookSelected(FlightOffer flightOffer) {
        CreditCardDetails ccd_decl = declassification.releaseCCD();
        if (ccd_decl == null) {
            return false;
        } else {
            return booking.bookFlight(flightOffer.getId(), ccd_decl);
        }
    }

    @Override
    public Iterable<FlightOffer> getFlightOffers(RequestData requestData) {
        return flightOffers.getFlightOffers(requestData);
    }

    @Override
    public boolean confirmBooking(int offerId, String airline) {
        return true;
    }
}