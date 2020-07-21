package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import de.steinwedel.messagebox.MessageBox;
import edu.kit.informatik.pcc.webinterface.datamanagement.AccountDataManager;

import java.util.ResourceBundle;

/**
 * Created by chris on 17.01.2017.
 * The View which is showed after every start of the website.
 *
 * @author chris
 */
public class LoginView extends VerticalLayout implements View {

    private final static String viewID = "LoginView";
    private TextField mailField;
    private PasswordField passwordField;

    //Constructor
    public LoginView(MyUI ui) {
        this.setSpacing(true);

        //initialization
        ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");
        mailField = new TextField(messages.getString(viewID + "mailField"));
        passwordField = new PasswordField(messages.getString(viewID + "passwordField"));
        Button loginButton = new Button(messages.getString(viewID + "loginButton"));
        Button registerButton = new Button(messages.getString(viewID + "registerButton"));

        loginButton.addClickListener(
                (ClickListener) event -> {
                    if (AccountDataManager.authenticateAccount(mailField.getValue(), passwordField.getValue(), getSession())) {
                        ui.login();
                    }
                }
        );

        registerButton.addClickListener(
                (ClickListener) event -> {
                    if (AccountDataManager.createAccount(mailField.getValue(), passwordField.getValue(), getSession())) {
                        MessageBox.createInfo()
                                .withMessage(messages.getString(viewID + "registerInfo"))
                                .open();
                    }
                }
        );
        Label header = new Label("Privacy Crash Cam");
        header.setSizeUndefined();
        header.setHeight(10, Unit.PERCENTAGE);

        this.addComponent(header);
        this.setComponentAlignment(header, Alignment.MIDDLE_CENTER);

        Panel loginPanel = new Panel("Login");
        VerticalLayout content = new VerticalLayout();
        content.setSizeUndefined();
        loginPanel.setContent(content);
        loginPanel.setSizeUndefined();

        content.addComponent(mailField);
        content.addComponent(passwordField);
        content.addComponent(loginButton);
        content.addComponent(registerButton);
        content.setSpacing(true);
        content.setMargin(true);

        this.addComponent(loginPanel);
        this.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
