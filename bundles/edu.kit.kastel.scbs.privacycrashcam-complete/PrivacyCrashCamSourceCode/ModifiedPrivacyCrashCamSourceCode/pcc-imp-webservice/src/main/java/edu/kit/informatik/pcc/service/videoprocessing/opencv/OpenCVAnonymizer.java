package edu.kit.informatik.pcc.service.videoprocessing.opencv;

import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

import edu.kit.informatik.pcc.service.videoprocessing.IVideoProcessor;

import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.io.File;
import java.util.logging.Logger;

/**
 * Implements the AAnonymizer interface.
 * Takes a video file and divides it into frames.
 * Then it analyzes each frames for faces and finally
 * makes it unrecognizable.
 *
 * @author Josh Romanowski
 */
public class OpenCVAnonymizer implements IVideoProcessor {

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Analyzer that takes a frame and analyzes it for faces.
     */
    private IAnalyzer analyzer;
    /**
     * Filter that makes a face recognition unrecognizable.
     */
    private IFilter filter;

    /* #############################################################################################
     *                                  constructors
     * ###########################################################################################*/

    /**
     * Loads the OpenCV library and creates the filters
     */
    public OpenCVAnonymizer() {

        analyzer = new OpenCVAnalyzer();
        filter = new OpenCVBoxfilter();
    }

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    @Override
    public Boolean processVideo(File inputVideo, File outputVideo) {
        if (inputVideo == null || outputVideo == null) {
            Logger.getGlobal().warning("Invalid input/output");
            return false;
        }
        // shut ffmpeg logger
        avutil.av_log_set_level(avutil.AV_LOG_QUIET);

        // Read settings
        IContainer container = IContainer.make();
        container.open(inputVideo.getAbsolutePath(), IContainer.Type.READ, null);
        IStream stream = container.getStream(0);

        if (stream == null) {
            Logger.getGlobal().warning("Uploaded file contained no video stream");
            return false;
        }

        IStreamCoder coder = stream.getStreamCoder();
        // video codec is rotated by 90 degrees!!!
        int width = coder.getHeight();
        int heigth = coder.getWidth();
        double fps = coder.getFrameRate().getValue();
        int bitrate = coder.getBitRate();
        long length = container.getDuration() / 1000000;
        coder.close();
        container.close();

        Logger.getGlobal().info(String.format(
                "Start anonymizing %s. Fps:%d, Size:%d x %d, Dur:%ds",
                inputVideo.getName(), (int) fps, width, heigth, length));

        // initialize grabber and recorder
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputVideo.getAbsolutePath());
        grabber.setImageHeight(heigth);
        grabber.setImageWidth(width);
        grabber.setFrameRate(fps);

        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideo.getAbsoluteFile(),
                width, heigth);
        recorder.setFormat("mp4");
        recorder.setFrameRate(fps);
        recorder.setVideoCodec(8);
        recorder.setVideoBitrate(bitrate);

        OpenCVFrameConverter<Mat> converter = new OpenCVFrameConverter.ToMat();

        //record
        try {
            grabber.start();
            recorder.start();
            Frame frame;
            while ((frame = grabber.grabImage()) != null) {
                // detect faces
                Mat mat = converter.convertToMat(frame);
                // rotate mat by 90 degrees
                opencv_core.transpose(mat, mat);
                opencv_core.flip(mat, mat, 1);
                RectVector detections = analyzer.analyze(mat);
                mat = filter.applyFilter(mat, detections);
                Frame end = converter.convert(mat);
                recorder.record(end);
            }
            grabber.stop();
            recorder.stop();
            grabber.close();
            recorder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Logger.getGlobal().info("Finished anonymization video " + inputVideo.getName());
        return true;
    }
}
