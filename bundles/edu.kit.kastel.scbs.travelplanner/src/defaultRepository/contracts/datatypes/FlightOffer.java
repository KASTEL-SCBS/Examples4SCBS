package defaultRepository.contracts.datatypes;

/**
 * A flight offer is offered by an airline and can be booked by the user.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class FlightOffer {

    /**
     * The global id of the flight offer.
     */
    private static int global_id = 0;

    /**
     * The identification number of the flight.
     */
    private int id;

    /**
     * The airline this flight belongs to.
     */
    private String airline;

    /**
     * The destination and date of the flight offer.
     */
    private RequestData data;

    /**
     * Creates a new flight offer with the given airline it belongs to and the destination and date.
     * 
     * @param airline
     *            The airline that provides the flight.
     * @param data
     *            The destination and date of the flight.
     */
    public FlightOffer(String airline, RequestData data) {
        this.id = global_id++;
        this.airline = airline;
        this.data = data;
    }

    /**
     * Gets the id of this offer.
     * 
     * @return The id of this offer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the destination of the flight.
     * 
     * @return The destination of the flight.
     */
    public String getDestination() {
        return data.getDestination();
    }

    /**
     * Gets the date of the departure of the flight.
     * 
     * @return The date of the departure of the flight.
     */
    public String getDate() {
        return data.getDate();
    }

    /**
     * Gets the providing airline.
     * 
     * @return The airline this offer belongs to.
     */
    public String getAirline() {
        return airline;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof FlightOffer) {
            FlightOffer other = (FlightOffer) obj;
            return this.id == other.id && this.data.equals(other.data);
        } else {
            return false;
        }
    }

    /**
     * Checks if the destination and date of this flight offer are equal to the given request data.
     * 
     * @param data
     *            The data to check equality with the request data of this offer.
     * @return True if the request data of this offer is equal to the given request data.
     */
    public boolean equalsData(RequestData data) {
        return this.data.equals(data);
    }

    /**
     * Checks if the id of this flight offer is equal to the given one.
     * 
     * @param id
     *            The id to check equality with the id of this offer.
     * @return True if the id of this offer is equal to the given id.
     */
    public boolean equalsId(int id) {
        return this.id == id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(airline).append(" flight no. ").append(id).append("\n");
        sb.append("Departure: ").append(getDate()).append("\n");
        return sb.toString();
    }
}