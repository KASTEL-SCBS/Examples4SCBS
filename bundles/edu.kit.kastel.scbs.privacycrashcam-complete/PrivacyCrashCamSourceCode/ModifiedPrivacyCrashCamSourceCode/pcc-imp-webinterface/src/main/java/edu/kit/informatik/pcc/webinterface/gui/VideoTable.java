package edu.kit.informatik.pcc.webinterface.gui;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;
import de.steinwedel.messagebox.MessageBox;
import edu.kit.informatik.pcc.webinterface.datamanagement.Video;
import edu.kit.informatik.pcc.webinterface.datamanagement.VideoDataManager;


import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * This class creates a table of all videos and adds buttons for functionality.
 *
 * @author Josh Romanowski, Christoph Hörtnagl
 */
public class VideoTable extends Table {

    private static final String tableId = "VideoTable";
    private static ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");
    private LinkedList<Video> videos;

    public VideoTable() {
        super();
        this.setSizeFull();
    }

    /**
     * Creates the table.
     */
    public void update() {
        videos = VideoDataManager.getVideos(getSession());
        this.addContainerProperty(messages.getString(tableId + "Name"), String.class, null);
        this.addContainerProperty(messages.getString(tableId + "Download"), Button.class, null);
        this.addContainerProperty(messages.getString(tableId + "Info"), Button.class, null);
        this.addContainerProperty(messages.getString(tableId + "Delete"), Button.class, null);
        this.removeAllItems();
        prepareEntries();
    }

    /**
     * This method sets the table entries up.
     */
    private void prepareEntries() {
        int i = 2;
        
        if (videos == null) {
        	return;
        }

        for (Video v : videos) {

          Button download = new Button(FontAwesome.DOWNLOAD);
          FileDownloader downloader = new FileDownloader(
                  VideoDataManager.createDownloadFileProxy(v.getId(), v.getName(), getSession()));
          downloader.extend(download);
          
            Button info = new Button(FontAwesome.INFO);
            info.addClickListener(
                    (ClickListener) event -> MessageBox.createInfo()
                            .withMessage(v.getInfo())
                            .open()
            );

            Button delete = new Button(FontAwesome.REMOVE);
            delete.addClickListener(
                    (ClickListener) event -> {
                        VideoDataManager.deleteVideo(v.getId(), getSession());
                        update();
                    }
            );
            this.addItem(new Object[]{v.getName(), download, info, delete}, i);
            i++;
        }
    }

}
