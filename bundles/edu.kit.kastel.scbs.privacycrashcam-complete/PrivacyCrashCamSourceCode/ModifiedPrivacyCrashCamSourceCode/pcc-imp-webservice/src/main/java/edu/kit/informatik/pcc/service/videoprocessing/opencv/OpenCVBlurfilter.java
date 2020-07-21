package edu.kit.informatik.pcc.service.videoprocessing.opencv;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_imgproc;

/**
 * Uses an OpenCV box filter to anonymize face detections.
 *
 * @author Josh Romanowski
 */
public class OpenCVBlurfilter implements IFilter {

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    @Override
    public Mat applyFilter(Mat frame, RectVector detections) {
        for (int i = 0; i < detections.size(); i++) {
            Rect rect = detections.get(i);
            opencv_imgproc.blur(frame, frame, rect.size());
        }
        return frame;
    }
}
