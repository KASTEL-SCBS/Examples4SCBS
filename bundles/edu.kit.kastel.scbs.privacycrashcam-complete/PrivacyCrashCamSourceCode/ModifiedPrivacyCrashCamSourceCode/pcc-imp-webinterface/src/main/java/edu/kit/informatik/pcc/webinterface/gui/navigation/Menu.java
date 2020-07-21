package edu.kit.informatik.pcc.webinterface.gui.navigation;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;
import edu.kit.informatik.pcc.webinterface.gui.MyUI;

/**
 * Created by chris on 17.01.2017.
 * The Menu class shows Buttons to navigate on the website.
 *
 * @author chris
 */
public class Menu extends VerticalLayout {

    //attributes
    private MyUI ui;
    private CssLayout menuItemsLayout;
    private MenuItem userItem;

    //constructors
    public Menu(MyUI myUI) {
        super();
        this.ui = myUI;
        menuItemsLayout = new CssLayout();
        MenuBar userMenu = new MenuBar();

        this.setPrimaryStyleName("valo-menu");

        Label menuCaption = new Label("Menu", ContentMode.HTML);
        menuCaption.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(menuCaption);
        logoWrapper.setComponentAlignment(menuCaption, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        this.addComponent(logoWrapper);

        userMenu.addStyleName("user-menu");

        userItem = userMenu.addItem("", null);
        userItem.setIcon(FontAwesome.MALE);

        this.addComponent(userMenu);

        this.addComponent(menuItemsLayout);
        menuItemsLayout.setSizeUndefined();
    }

    //methods

    /**
     * This method takes caption and a view so you can add it to the menu.
     *
     * @param caption caption
     * @param viewID  the view to which the menu button will lead
     */
    public void addMenuItem(String caption, String viewID) {

        Button button = new Button(caption,
                (Button.ClickListener) clickEvent -> ui.getNavigator().navigateTo(viewID));
        button.setPrimaryStyleName("valo-menu-item");
        menuItemsLayout.addComponent(button);
    }

    /**
     * This method adds teh user name to the menu.
     * @param text username
     */
    public void addUserMenu(String text) {
        if (text == null) {
            return;
        }
        userItem.setText(text);
    }

    /**
     * This method adds the option logout to the menu.
     */
    public void addLogout() {
        Button button = new Button("Logout", (Button.ClickListener) clickEvent -> ui.logout());
        button.setPrimaryStyleName("valo-menu-item");
        menuItemsLayout.addComponent(button);
    }
}
