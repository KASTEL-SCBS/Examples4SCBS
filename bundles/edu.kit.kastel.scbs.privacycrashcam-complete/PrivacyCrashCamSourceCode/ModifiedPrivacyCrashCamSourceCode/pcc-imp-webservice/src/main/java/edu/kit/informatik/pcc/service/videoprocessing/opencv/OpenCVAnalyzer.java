package edu.kit.informatik.pcc.service.videoprocessing.opencv;

import edu.kit.informatik.pcc.service.data.LocationConfig;
import edu.kit.informatik.pcc.service.server.Main;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

/**
 * Class that analyzes a frame with the OpenCV
 * framework and identifies all frontal faces.
 *
 * @author Josh Romanowski
 */
public class OpenCVAnalyzer implements IAnalyzer {

	private static final String OPENCV_DIR = LocationConfig.PROJECT_DIR + File.separator + "opencv";
    private static final String CASCADE_LOCATION = OPENCV_DIR + File.separator + "cascade.xml";

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Classifier used to detect faces.
     */
    private CascadeClassifier classifier;

    /* #############################################################################################
     *                                  constructors
     * ###########################################################################################*/

    /**
     * Loads the classifier.
     */
    public OpenCVAnalyzer() {

        if (!new File(CASCADE_LOCATION).exists()) {
            copyCascade();
        }

        classifier = new CascadeClassifier(CASCADE_LOCATION);
        if (classifier.empty()) {
            Logger.getGlobal().severe("Classifier couldn't be loaded");
            Main.stopServer();
        }
    }


    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    @Override
    public RectVector analyze(Mat frame) {
        RectVector detections = new RectVector();
        classifier.detectMultiScale(frame, detections);
        return detections;
    }

    private void copyCascade() {
    	File dir = new File(OPENCV_DIR);
    	if (!dir.exists()) {
    		dir.mkdirs();
    	}
        InputStream is = getClass().getResourceAsStream("/haarcascade_frontalface_alt.xml");
        try {
            Files.copy(is, new File(CASCADE_LOCATION).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
