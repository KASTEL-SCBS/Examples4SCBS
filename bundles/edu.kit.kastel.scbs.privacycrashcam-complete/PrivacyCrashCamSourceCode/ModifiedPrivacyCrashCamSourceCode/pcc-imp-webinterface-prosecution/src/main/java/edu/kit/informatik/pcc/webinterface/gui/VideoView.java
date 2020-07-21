package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 * This view shows the video table.
 *
 * @author Josh Romanowski, Christoph Hörtnagl
 */
public class VideoView extends VerticalLayout implements View{

    //attributes
    public static final String viewID = "VideoView";
    private VideoTable videoTable;

    //constructors
    public VideoView() {
        this.setSizeFull();
        videoTable = new VideoTable();
        this.addComponent(videoTable);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        videoTable.update();
    }

}
