package defaultRepository.main;

import java.io.IOException;

import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.interfaces.DeclassificationConfirmation;

/**
 * The user selects a flight offer to book by communicating with the travel planner.
 * 
 * Confirms or declines the declassification of his credit card details.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class User implements DeclassificationConfirmation {

    /**
     * The singleton instance.
     */
    private static User instance;

    /**
     * To temporary store flights offers. The flight offers the user accessed and are now available
     * for booking.
     */
    private Iterable<FlightOffer> flightOffers;

    /**
     * Gets the singleton of this class. Must be initialized before used.
     * 
     * @return The singleton.
     */
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
            instance.flightOffers = null;
        }
        return instance;
    }

    @Override
    public boolean confirmRelease(String airline) {
        System.out.println("Release Credit Card Details to " + airline + "? (y/n)");
        boolean answer = false;
        String input = "";
        try {
            input = Main.READER.readLine();
        } catch (IOException e) {
            //
        }
        if (input.equals("y") || input.equals("yes") || input.equals("true")) {
            answer = true;
        }
        return answer;
    }

    /**
     * Gets the last flight offers the user accessed.
     * 
     * @return The last flight offers the user accessed.
     */
    public Iterable<FlightOffer> getFlightOffers() {
        return flightOffers;
    }

    /**
     * Sets the last flight offers the user accessed.
     * 
     * @param flightOffers
     *            The last flight offers the user accessed.
     */
    public void setFlightOffers(Iterable<FlightOffer> flightOffers) {
        this.flightOffers = flightOffers;
    }
}
