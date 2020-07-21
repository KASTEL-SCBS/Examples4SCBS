package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * Created by chris on 17.01.2017.
 *
 * This class shows the privacy information.
 *
 * @author chris
 */
public class PrivacyView extends HorizontalLayout implements View {

    public static final String viewID = "PrivacyView";
    private ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");

    public PrivacyView() {
        this.setSpacing(true);
        this.setMargin(true);
        this.setSizeFull();
        Panel panel = new Panel(messages.getString(viewID + "Privacy"));
        panel.setWidth(50, Sizeable.Unit.PERCENTAGE);
        Label htmlLabel = new Label(loadHtmlLabel(), ContentMode.HTML);
        panel.setContent(htmlLabel);
        panel.setSizeFull();
        this.addComponent(panel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    private String loadHtmlLabel() {
        InputStream is = getClass().getResourceAsStream("/privacy.txt");
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
            return "";
        }
    }
}
