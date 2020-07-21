package edu.kit.informatik.pcc.webinterface.datamanagement;

/**
 * Created by chris on 17.01.2017.
 * The attributes of a video combined in one class for simpler useabilty.
 *
 * @author chris
 */
public class Video {

    public static final String EXTENSION = ".mp4";

    //attributes

    /**
     * Name of the video
     */
    private String name;
    /**
     * Database id of the video
     */
    private int id;
    /**
     * Video metadata
     */
    private String info;

    //constructors

    /**
     * Creates a new video object with the given name and id.
     *
     * @param name Name of the video
     * @param id
     */
    public Video(String name, int id) {
        this(name, id, "");
    }

    public Video(String name, int id, String info) {
        this.name = name;
        this.id = id;
        this.info = info;
    }

    //getter/setter
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
