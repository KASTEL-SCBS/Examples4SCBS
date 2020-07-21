package edu.kit.informatik.pcc.service.data;

import java.io.File;

/**
 * Config class that holds all directories used.
 *
 * @author Josh Romanowski and David Laubenstein
 *         Created by David Laubenstein on 01/18/2017
 */
public class LocationConfig {

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Project directory
     */
    public static final String PROJECT_DIR = System.getProperty("user.dir") + File.separator + "server";
    /**
     * Directory for log files.
     */
    public static final String LOG_DIR = PROJECT_DIR + File.separator + "log";
    public static final String KEY_DIR = PROJECT_DIR + File.separator + "keys";
    public static final String DATA_DIR = PROJECT_DIR + File.separator + "data";
    public static final String TEMP_DIR = PROJECT_DIR + File.separator + "tmp";
    
	private static final String videosDirectory = "videos";
	private static final String metadataDirectory = "metadata";
	private static final String keyDirectory = "encKey";
	private static final String encVideoDirectory = "encVideo";
    
}
