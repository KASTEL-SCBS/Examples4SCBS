package edu.kit.informatik.pcc.service.videoprocessing.opencv;


import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 * Interfaces for classes that analyzes single frames
 * for personal information with the OpenCV functionality.
 *
 * @author Josh Romanowski
 */
public interface IAnalyzer {

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    /**
     * Takes a single frames and analyzes it for personal
     * data.
     *
     * @param frame Input frame.
     * @return Returns all face detections.
     */
    public RectVector analyze(Mat frame);
}
