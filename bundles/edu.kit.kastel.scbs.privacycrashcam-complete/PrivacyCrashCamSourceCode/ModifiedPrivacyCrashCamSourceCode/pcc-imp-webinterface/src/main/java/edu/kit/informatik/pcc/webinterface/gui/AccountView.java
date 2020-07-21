package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import edu.kit.informatik.pcc.webinterface.datamanagement.Account;
import edu.kit.informatik.pcc.webinterface.datamanagement.AccountDataManager;

import java.util.ResourceBundle;

/**
 * Created by chris on 17.01.2017.
 * This View shows account data and options to change
 *
 * @author chris
 */
public class AccountView extends VerticalLayout implements View{

    //attributes
    public final static String viewID = "AccountView";
    private TextField mailChangeField;
    private PasswordField passwordChangeField;
    private PasswordField passwordField;

    //constructors
    public AccountView(MyUI ui) {
        //initialization
        this.setSpacing(true);
        this.setMargin(true);
        ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");

        Account account = (Account) ui.getSession().getAttribute("account");

        String mail = account.getMail();
        Label mailLabel = new Label(mail);
        //change account not supported anymore
        /*mailChangeField = new TextField(messages.getString(viewID + "mailChangeField"));
        passwordChangeField = new PasswordField(messages.getString(viewID + "passwordChangeField"));
        passwordField = new PasswordField(messages.getString(viewID + "passwordField"));
        Button changeButton = new Button(messages.getString(viewID + "changeButton"));
        */
        Button deleteButton = new Button(messages.getString(viewID + "deleteButton"));

        /*changeButton.addClickListener(
                (Button.ClickListener) event -> {
                    if (AccountDataManager.changeAccount(
                            passwordField.getValue(), mailChangeField.getValue(),
                            passwordChangeField.getValue(), getSession())) {
                        ui.initializeGraphicalComponents();
                    }
                }
        );*/

        deleteButton.addClickListener(
                (Button.ClickListener) event -> {
                    if (AccountDataManager.deleteAccount(getSession())) {
                        ui.initializeGraphicalComponents();
                    }
                }
        );

        this.addComponent(mailLabel);
        /*this.addComponent(mailChangeField);
        this.addComponent(passwordChangeField);
        this.addComponent(passwordField);
        this.addComponent(changeButton);*/
        this.addComponent(deleteButton);
    }

    //methods
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
