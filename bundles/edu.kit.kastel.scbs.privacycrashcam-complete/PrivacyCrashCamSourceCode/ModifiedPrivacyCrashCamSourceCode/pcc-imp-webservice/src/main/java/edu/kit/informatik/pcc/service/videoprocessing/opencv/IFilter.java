package edu.kit.informatik.pcc.service.videoprocessing.opencv;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;

/**
 * Interface for classes that make detections
 * unrecognizable with the OpenCV framework.
 *
 * @author Josh Romanowski
 */
public interface IFilter {

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Takes the input frame and makes all dections
     * on it unrecognizable.
     *
     * @param frame      Input frame.
     * @param detections Detections to anonymize.
     * @return Returns the edited input frame.
     */
    public Mat applyFilter(Mat frame, RectVector detections);
}
