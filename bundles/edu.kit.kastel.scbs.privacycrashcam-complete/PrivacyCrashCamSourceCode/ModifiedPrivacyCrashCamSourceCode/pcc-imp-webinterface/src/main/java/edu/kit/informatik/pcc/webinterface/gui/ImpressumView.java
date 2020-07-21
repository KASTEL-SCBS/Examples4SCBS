package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

/**
 * Created by chris on 17.01.2017.
 *
 * This View shows the Impressum.
 *
 * @author chris
 */
public class ImpressumView extends VerticalLayout implements View {

    public static final String viewID = "ImpressumView";
    private ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");

    public ImpressumView() {
        this.setSpacing(true);
        this.setMargin(true);
        Label label = new Label(messages.getString(viewID + "Text"), ContentMode.PREFORMATTED);
        label.setIcon(FontAwesome.COPYRIGHT);
        this.addComponent(label);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
