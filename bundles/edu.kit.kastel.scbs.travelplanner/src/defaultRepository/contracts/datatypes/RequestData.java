package defaultRepository.contracts.datatypes;

/**
 * The request data contains a flight destination and the date of the departure.
 * 
 * date format: XXXX-YY-ZZ
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class RequestData {

    /**
     * The destination of the flight.
     */
    private final String destination;

    /**
     * The date of the flight.
     */
    private final String date;

    /**
     * Creates new request data with the given destination and date.
     * 
     * @param destination
     *            The destination of the flight.
     * @param date
     *            The date of the departure of the flight.
     */
    public RequestData(String destination, String date) {
        this.destination = destination;
        this.date = date;
    }

    /**
     * Gets the destination of the flight.
     * 
     * @return The destination of the flight.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * The date of the departure.
     * 
     * @return The date of the departure.
     */
    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof RequestData) {
            RequestData other = (RequestData) obj;
            return this.destination.equals(other.destination) && this.date.equals(other.date);
        } else {
            return false;
        }
    }
}