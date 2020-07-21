package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import edu.kit.informatik.pcc.webinterface.datamanagement.Account;
import edu.kit.informatik.pcc.webinterface.gui.navigation.Menu;

import javax.servlet.annotation.WebServlet;
import java.util.ResourceBundle;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
public class MyUI extends UI {

    public static final String SESSION_KEY_ACCOUNT = "account";
    public static final String SESSION_KEY_VIDOES = "vidoes";
    public static final String SESSION_TOKEN = "token";

    private static ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");
    //attributes
    private HorizontalLayout background;
    private VerticalLayout menuArea;
    private VerticalLayout contentArea;

    /**
     * This method is called whenever somebody openes the UI, we start initialization here.
     *
     * @param vaadinRequest the request
     */
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.setSizeFull();
        initializeGraphicalComponents();
    }


    //methods

    /**
     * Here we initialize all graphical components which are used for the start of the site.
     */
    public void initializeGraphicalComponents() {

        background = new HorizontalLayout();
        menuArea = new VerticalLayout();
        contentArea = new VerticalLayout();

        LoginView login = new LoginView(this);

        background.addComponent(login);
        background.setSizeFull();
        setContent(background);
    }

    /**
     * Sets the site up after login. Creates Menu and Navigator and the loads the VideoView.
     */
    public void login() {
        //set User after login and add menu to the view
        //set up the menu
        background = new HorizontalLayout();
        background.setHeight(100, Unit.PERCENTAGE);
        setContent(background);
        contentArea.setSizeFull();

        Navigator navigator = new Navigator(this, contentArea);
        navigator.addView(AccountView.viewID, new AccountView(this));
        navigator.addView(VideoView.viewID, new VideoView());
        navigator.addView(PrivacyView.viewID, new PrivacyView());
        navigator.addView(ImpressumView.viewID, new ImpressumView());
        navigator.setErrorView(new VideoView());
        this.setNavigator(navigator);

        Menu menu = new Menu(this);
        menu.addMenuItem(messages.getString(AccountView.viewID), AccountView.viewID);
        menu.addMenuItem(messages.getString(VideoView.viewID), VideoView.viewID);
        menu.addMenuItem(messages.getString(PrivacyView.viewID), PrivacyView.viewID);
        menu.addMenuItem(messages.getString(ImpressumView.viewID), ImpressumView.viewID);
        menu.addLogout();

        menuArea.addComponent(menu);
        //menuArea.setWidth(200, Unit.PIXELS);
        menuArea.setHeight(100, Unit.PERCENTAGE);

        Account account = (Account) getSession().getAttribute(SESSION_KEY_ACCOUNT);

        menu.addUserMenu(account.getMail());
        menu.setSizeUndefined();

        background.addComponent(menuArea);
        background.addComponent(contentArea);

        navigator.navigateTo(VideoView.viewID);
    }

    /**
     * Loads the site new.
     */
    public void logout() {
        initializeGraphicalComponents();
    }

    /**
     * The Servlet in which the site runs.
     */
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
