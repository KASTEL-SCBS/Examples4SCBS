package defaultRepository.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import defaultRepository.components.Airline.Airline;
import defaultRepository.components.CreditCardCenter.CreditCardCenter;
import defaultRepository.components.TravelAgency.TravelAgency;
import defaultRepository.components.TravelPlanner.TravelPlanner;
import defaultRepository.contracts.datatypes.CreditCardDetails;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;

/**
 * Main class for initialization of components, setting examples values of the system and
 * continuously reading user input.
 * 
 * The travel planner system allows the user to look at the flight offers of an airline, which he
 * receives from a travel agency. Once a desired flight offer is selected, he can book it directly
 * at the airline and pay by releasing his credit card details to the airline. He therefore has to
 * call the credit card center, which manages his credit card, to release and declassify the credit
 * card details. He has to explicitly confirm the declassification in order for this to work. Once
 * the flight is booked, the airline pays a commission to the travel agency for conveying the flight
 * offer.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class Main {

    public final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static boolean quit = false;

    /**
     * Continuously read user input, create a corresponding command and execute it.
     * 
     * @param args
     *            unused.
     */
    public static void main(String[] args) {
        // initialize and set example values
        TravelPlanner.initialize();
        Airline.initialize();
        TravelAgency.initialize();
        setExampleValues();
        // read user input, create command and execute it
        String input;
        Command cmd;
        do {
            input = null;
            cmd = null;
            try {
                input = READER.readLine();
            } catch (IOException e) {
                input = "";
            }
            if (input.equals("quit")) {
                quit = true;
            } else {
                for (Command command : Command.values()) {
                    if (input.startsWith(command.getName())) {
                        cmd = command;
                        break;
                    }
                }
                if (cmd == null) {
                    // display help message, if an invalid command was given
                    input = Command.HELP.getName();
                    cmd = Command.HELP;
                }
                cmd.execute(input.substring(cmd.getName().length()).trim());
            }
        } while (!quit);
    }

    /**
     * Sets example flight offers of the airline and the credit card details of the user.
     */
    private static void setExampleValues() {
        List<FlightOffer> initialFlightOffers = new ArrayList<FlightOffer>();
        initialFlightOffers
                .add(new FlightOffer(Airline.getInstance().getName(), new RequestData("Augsburg", "2017-11-28")));
        initialFlightOffers
                .add(new FlightOffer(Airline.getInstance().getName(), new RequestData("Augsburg", "2017-12-03")));
        initialFlightOffers
                .add(new FlightOffer(Airline.getInstance().getName(), new RequestData("Augsburg", "2017-12-25")));
        initialFlightOffers
                .add(new FlightOffer(Airline.getInstance().getName(), new RequestData("Munich", "2017-12-15")));
        initialFlightOffers
                .add(new FlightOffer(Airline.getInstance().getName(), new RequestData("Munich", "2017-11-25")));
        initialFlightOffers
                .add(new FlightOffer(Airline.getInstance().getName(), new RequestData("Munich", "2017-12-30")));
        Airline.getInstance().setAvailableFlights(initialFlightOffers);

        CreditCardCenter.getInstance().setCreditCard(new CreditCardDetails("ccd1", "1234567890", "2018-12-31"));
    }
}
