package defaultRepository.contracts.datatypes;

/**
 * The credit card details of a user storing the information necessary to book flights.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class CreditCardDetails {

    /**
     * Name of the credit card owner.
     */
    private String name;

    /**
     * The credit card number.
     */
    private String number;

    /**
     * The expiration date of the credit card.
     */
    private String expiration;

    /**
     * Creates a new crdit card details with an empty list of flight offers.
     * 
     * @param name
     *            The name of the credit card owner.
     * @param number
     *            The number of the credit card.
     * @param expiration
     *            The expiration date of the credit card.
     */
    public CreditCardDetails(String name, String number, String expiration) {
        this.name = name;
        this.number = number;
        this.expiration = expiration;
    }

    /**
     * Gets the name of the credit card owner.
     * 
     * @return The name of the credit card owner.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of the credit card.
     * 
     * @return The number of the credit card.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Gets the expiration date of the credit card.
     * 
     * @return The expiration date of the credit card.
     */
    public String getExpiration() {
        return expiration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Owner: ").append(getName()).append("\n");
        sb.append("No. ").append(number).append("\n");
        sb.append("Expiration date: ").append(expiration).append("\n");
        return sb.toString();
    }
}