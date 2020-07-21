package edu.kit.informatik.pcc.webinterface.datamanagement;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The attributes of an account combined in one class for simpler usability.
 *
 * @author Josh Romanowski, Christoph Hörtnagl
 */
public class Account {

    // JSON keys
    private static final String JSON_KEY_MAIL = "mail";
    private static final String JSON_KEY_PASSWORD = "password";

    //attributes

    /**
     * The users E-mail address
     */
    private String mail;
    /**
     * The users password
     */
    private String password;

    //constructors

    /**
     * Creates a new account with the given parameters.
     *
     * @param mail     E-mail address of the user
     * @param password Password of the user
     */
    public Account(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    /**
     * Creates a new account from a json string.
     * @param json Json string containing account data
     */
    public Account(String json) {
        try {
            JSONObject account = new JSONObject(json);
            this.mail = account.getString(JSON_KEY_MAIL);
            this.password = account.getString(JSON_KEY_PASSWORD);
        } catch (JSONException e) {
            throw new IllegalArgumentException();
        }
    }

    //getter/setter
    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    /**
     * A getter that gives you the Account attributes in a json object.
     *
     * @return the json object
     */
    public String getAsJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_KEY_MAIL, this.mail);
            json.put(JSON_KEY_PASSWORD, this.password);
        } catch (JSONException e) {
            return "";
        }
        return json.toString();
    }
}
