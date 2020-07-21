package edu.kit.informatik.pcc.service.videoprocessing.opencv;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_imgproc;

import static org.bytedeco.javacpp.opencv_imgproc.CV_FILLED;


/**
 * Uses an OpenCV box filter to anonymize face detections.
 *
 * @author Josh Romanowski
 */
public class OpenCVBoxfilter implements IFilter {

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    @Override
    public Mat applyFilter(Mat frame, RectVector detections) {
        for (int i = 0; i < detections.size(); i++) {
            Rect rect = detections.get(i);
            opencv_imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 0),
                    CV_FILLED, 0, 0);
        }
        return frame;
    }
}
