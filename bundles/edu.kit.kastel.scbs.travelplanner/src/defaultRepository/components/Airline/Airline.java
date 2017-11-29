package defaultRepository.components.Airline;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import defaultRepository.components.TravelAgency.TravelAgency;
import defaultRepository.components.TravelPlanner.TravelPlanner;
import defaultRepository.contracts.datatypes.CreditCardDetails;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;
import defaultRepository.contracts.interfaces.AirlineAdministration;
import defaultRepository.contracts.interfaces.Booking;
import defaultRepository.contracts.interfaces.BookingConfirmation;
import defaultRepository.contracts.interfaces.Comission;
import defaultRepository.contracts.interfaces.FlightOffers;

/**
 * Represents an airline to offer flights and process the booking of flights.
 * 
 * The user books flights of an airline via a selected flight offer and his credit card details.
 * 
 * Pays the travel agency a commission for conveying the offers to the customer.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class Airline implements FlightOffers, Booking, AirlineAdministration {

    //automatically generated:
	//@ cluster AgencyServerCluster;
	//@ \lowIn comission.payCommission.\term(\result),
	//@     bookingConfirmation.confirmBooking.\term(\result),
	//@     this.getFlightOffers.\call(requestData),
	//@     this.bookFlight.\call(offerId),
	//@     this.setAvailableFlights.\call(flights)
	//@ \lowOut comission.payCommission.\call(offerId),
	//@     bookingConfirmation.confirmBooking.\call(offerId, airline),
	//@     this.getFlightOffers.\term(\result)
	//@ \visible \nothing
	//@ \lowState AgencyServer;
	//@ 
	//@ model \seq AgencyServer

	//automatically generated:
	//@ cluster AirlineServerCluster;
	//@ \lowIn comission.payCommission.\term(\result),
	//@     bookingConfirmation.confirmBooking.\term(\result),
	//@     this.getFlightOffers.\call(requestData),
	//@     this.bookFlight.\call(ccd_decl, offerId),
	//@     this.setAvailableFlights.\call(flights)
	//@ \lowOut comission.payCommission.\call(offerId),
	//@     bookingConfirmation.confirmBooking.\call(offerId, airline),
	//@     this.getFlightOffers.\term(\result),
	//@     this.bookFlight.\term(\result)
	//@ \visible \nothing
	//@ \lowState AirlineServer;
	//@ 
	//@ model \seq AirlineServer

	//automatically generated:
	//@ cluster MobilePhoneCluster;
	//@ \lowIn bookingConfirmation.confirmBooking.\term(\result),
	//@     this.getFlightOffers.\call(requestData),
	//@     this.bookFlight.\call(ccd_decl, offerId),
	//@     this.setAvailableFlights.\call(flights)
	//@ \lowOut bookingConfirmation.confirmBooking.\call(offerId, airline),
	//@     this.getFlightOffers.\term(\result),
	//@     this.bookFlight.\term(\result)
	//@ \visible \nothing
	//@ \lowState MobilePhone;
	//@ 
	//@ model \seq MobilePhone
	
    /**
     * The singleton instance.
     */
    private static Airline instance;

    /**
     * To pay the commission to.
     */
    private Comission comission;

    /**
     * To ask for confirmation for booking a flight.
     */
    private BookingConfirmation bookingConfirmation;

    /**
     * The available flight offers of this airline.
     */
    private Iterable<FlightOffer> flightOffers;

    /**
     * The name of this airline.
     */
    private String name;

    /**
     * Creates a new airline with an empty list of flight offers.
     */
    private Airline() {
        this.flightOffers = new ArrayList<FlightOffer>();
    }

    /**
     * Gets the singleton of this class. Must be initialized before used.
     * 
     * @return The singleton.
     */
    public static Airline getInstance() {
        if (instance == null) {
            instance = new Airline();
            instance.name = "airline";
        }
        return instance;
    }

    /**
     * Initializes this class, i.e. sets all necessary fields.
     */
    public static void initialize() {
        getInstance();
        instance.comission = TravelAgency.getInstance();
        instance.bookingConfirmation = TravelPlanner.getInstance();
    }

    @Override
    public Iterable<FlightOffer> getFlightOffers(RequestData requestData) {
        List<FlightOffer> filteredFlightOffers = new LinkedList<>();
        for (FlightOffer flightOffer : flightOffers) {
            if (flightOffer.equalsData(requestData)) {
                filteredFlightOffers.add(flightOffer);
            }
        }
        return filteredFlightOffers;
    }

    @Override
    public boolean bookFlight(int offerId, CreditCardDetails ccd_decl) {
        boolean booked = proccessBooking(offerId, ccd_decl);
        if (booked) {
            boolean confirmed = bookingConfirmation.confirmBooking(offerId, name);
            if (confirmed) {
                boolean payed = this.comission.payCommission(offerId);
                if (payed) {
                    return true;
                }
            }
            revertBooking(offerId);
        }
        return false;
    }

    /**
     * Books the flight referenced by the given id by using the given declassified credit card details.
     * 
     * @param offerId The offer id for the flight to book.
     * @param ccd_decl The declassified credit card details to use for the purchase.
     * @return true if the processing was successful.
     */
    private boolean proccessBooking(int offerId, CreditCardDetails ccd_decl) {
        // process booking with offerId and ccd_decl
        return true;
    }

    /**
     * Reverts the booking of the flight offer with the given id.
     * 
     * @param offerId The id of the flight offer.
     */
    private void revertBooking(int offerId) {
        // revert booking with offerId
    }

    @Override
    public void setAvailableFlights(Iterable<FlightOffer> flights) {
        this.flightOffers = flights;
    }

    /**
     * Gets the name of the airline.
     * 
     * @return The name of the airline.
     */
    public String getName() {
        return name;
    }
}