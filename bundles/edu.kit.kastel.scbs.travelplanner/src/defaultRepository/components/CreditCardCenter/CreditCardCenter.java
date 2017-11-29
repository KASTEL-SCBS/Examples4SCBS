package defaultRepository.components.CreditCardCenter;

import defaultRepository.contracts.datatypes.CreditCardDetails;
import defaultRepository.contracts.interfaces.Configuration;
import defaultRepository.contracts.interfaces.Declassification;
import defaultRepository.contracts.interfaces.DeclassificationConfirmation;
import defaultRepository.main.User;

/**
 * Credit card center for storing the credit card details of users.
 * 
 * Declassifies credit card details to make them available for others, e.g. for doing a payment.
 * 
 * @author Nils Wilka
 * @version 1.0, 29.11.2017
 */
public class CreditCardCenter implements Declassification, Configuration {

    // automatically generated:
    // @ cluster AirlineServerCluster;
    // @ \lowIn \nothing
    // @ \lowOut this.releaseCCD.\term(\result)
    // @ \visible \nothing
    // @ \lowState AirlineServer;
    // @
    // @ model \seq AirlineServer

    // automatically generated:
    // @ cluster MobilePhoneCluster;
    // @ \lowIn declassificationConfirmation.confirmRelease.\term(\result),
    // @     this.releaseCCD.\call(airline),
    // @     this.setCreditCard.\call(ccd)
    // @ \lowOut declassificationConfirmation.confirmRelease.\call(airline),
    // @     this.releaseCCD.\term(\result)
    // @ \visible \nothing
    // @ \lowState MobilePhone;
    // @
    // @ model \seq MobilePhone

    /**
     * The singleton instance.
     */
    private static CreditCardCenter instance;

    /**
     * To get confirmation for declassifying the credit card details.
     */
    private DeclassificationConfirmation declassificationConfirmation;

    /**
     * Credit card details of the user.
     */
    private CreditCardDetails ccd;

    /**
     * Creates a new credit card center for managing the credit card details of the users.
     * 
     * @param declassificationConfirmation
     *            To get declassification confirmation.
     */
    private CreditCardCenter(DeclassificationConfirmation declassificationConfirmation) {
        this.declassificationConfirmation = declassificationConfirmation;
    }

    /**
     * Gets the singleton of this class.
     * 
     * @return The singleton.
     */
    public static CreditCardCenter getInstance() {
        if (instance == null) {
            instance = new CreditCardCenter(User.getInstance());
        }
        return instance;
    }

    @Override
    public CreditCardDetails releaseCCD(String airline) {
        if (declassificationConfirmation.confirmRelease(airline)) {
            return declassify(ccd);
        }
        return null;
    }

    /**
     * Declassifies the credit card details. The credit card information that was previously only
     * allowed to be accessed by the user via his mobile phone, is allowed to be accessed by the
     * airline after calling this method.
     * 
     * @param ccd
     *            The credit card details to declassify.
     * @return The declassified credit card details of the user.
     */
    private CreditCardDetails declassify(CreditCardDetails ccd) {
        // MobilePhone ==> MobilePhone, Airline
        CreditCardDetails ccd_decl = ccd;
        return ccd_decl;
    }

    @Override
    public void setCreditCard(CreditCardDetails ccd) {
        this.ccd = ccd;
    }
}