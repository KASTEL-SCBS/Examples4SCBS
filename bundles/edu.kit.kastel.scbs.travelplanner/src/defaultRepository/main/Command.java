package defaultRepository.main;

import defaultRepository.components.TravelAgency.TravelAgency;
import defaultRepository.components.TravelPlanner.TravelPlanner;
import defaultRepository.contracts.datatypes.FlightOffer;
import defaultRepository.contracts.datatypes.RequestData;

/**
 * A command is created by the user and calls a function of the travel planner.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public enum Command {

    /**
     * Checks the available flight offers and books the flight with the equal id. The command "get
     * flight offers" needs to be called first. Only checks the flight offers that were returned on
     * the last call.
     */
    BOOK_SELECTED("book flight", "id") {
        @Override
        public void execute(String args) {
            FlightOffer selected = null;
            // search for the flight offer with the given id
            if (args.matches("[0-9][0-9]*")) {
                int id = Integer.parseInt(args);
                for (FlightOffer flightOffer : User.getInstance().getFlightOffers()) {
                    if (flightOffer.equalsId(id)) {
                        selected = flightOffer;
                        break;
                    }
                }
            }
            if (selected == null) {
                // the user did not select a valid flight offer.
                Command.HELP.execute("");
            } else {
                boolean result = TravelPlanner.getInstance().bookSelected(selected);
                // display the success / failure of the booking.
                StringBuilder output = new StringBuilder("booking of flight offer no. ");
                output.append(selected.getId()).append(" ");
                output.append(result ? "succeeded." : "failed.");
                System.out.println(output);
            }
        }
    },

    /**
     * Gets the flights offers with the given destination and date from the travel agency.
     */
    GET_FLIGHT_OFFERS("get flight offers", "destination> <date") {
        @Override
        public void execute(String args) {
            String[] input = args.split(" ");
            if (input.length != 2) {
                // insufficient arguments
                Command.HELP.execute("");
            } else {
                RequestData requestData = new RequestData(input[0], input[1]);
                Iterable<FlightOffer> flightOffers = TravelAgency.getInstance().getFlightOffers(requestData);
                // Set the bookable flight offers
                User.getInstance().setFlightOffers(flightOffers);
                System.out.println("Flight offers for destination " + requestData.getDestination() + ":");
                StringBuilder sb = new StringBuilder();
                for (FlightOffer flightOffer : flightOffers) {
                    sb.append(flightOffer.toString());
                }
                if (sb.toString().isEmpty()) {
                    sb = new StringBuilder("none");
                }
                System.out.println(sb.toString());
            }
        }
    },

    /**
     * Displays a help message, i.e. the available commands.
     */
    HELP("help", "") {
        @Override
        public void execute(String args) {
            StringBuilder sb = new StringBuilder();
            sb.append("Show this message by typing \"help\"").append("\n");
            sb.append("Available commands:").append("\n");
            sb.append("quit").append("\n");
            for (Command command : Command.values()) {
                sb.append(command).append("\n");
            }
            System.out.print(sb.toString());
        }
    };

    /**
     * The call name of the command.
     */
    private String name;

    /**
     * The description of the arguments for the command.
     */
    private String argsPattern;

    /**
     * Creates a new command with the given name and argument pattern.
     * 
     * @param name
     *            The call name of the command.
     * @param argsPattern
     *            The argument description.
     */
    private Command(String name, String argsPattern) {
        this.name = name;
        this.argsPattern = argsPattern;
    }

    /**
     * Executes the command with the given arguments.
     * 
     * @param args
     *            The arguments for the execution of the command.
     */
    public abstract void execute(String args);

    /**
     * Gets the name of the command.
     * 
     * @return The call name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the command arguments description.
     * 
     * @return The command arguments description.
     */
    public String getArgsPattern() {
        return argsPattern;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        if (!argsPattern.isEmpty()) {
            sb.append(" <").append(getArgsPattern()).append(">");
        }
        return sb.toString();
    }
}
