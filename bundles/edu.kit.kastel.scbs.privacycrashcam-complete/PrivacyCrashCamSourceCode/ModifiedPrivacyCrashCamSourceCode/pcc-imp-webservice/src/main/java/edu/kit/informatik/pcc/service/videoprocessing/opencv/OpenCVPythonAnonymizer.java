package edu.kit.informatik.pcc.service.videoprocessing.opencv;

import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

import edu.kit.informatik.pcc.core.data.IFileManager;
import edu.kit.informatik.pcc.service.data.LocationConfig;
import edu.kit.informatik.pcc.service.videoprocessing.IVideoProcessor;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Implements the AAnonymizer interface.
 * Takes a video file and divides it into pictures.
 * Than it takes a python-script to anonymize each picture.
 * After anonymization the pictures get merged into a mp4-File again.
 *
 * @author Josh Romanowski
 */
public class OpenCVPythonAnonymizer implements IVideoProcessor {
    private static final String PYTHON_DIR = LocationConfig.PROJECT_DIR + File.separator + "Python";
    private static final String ANONYM_SUFFIX = "_out_inverse_result_inverted";
    
    private IFileManager temporaryFileManager;
    
    public void setTemporaryFileManager(IFileManager temporaryFileManager) {
    	assert this.temporaryFileManager == null;
    	this.temporaryFileManager = temporaryFileManager;
    }

    /* #############################################################################################
     *                                  attributes
     * ###########################################################################################*/

    /**
     * Converter used to convert the video into pictures and vise-versa
     */
    private VideoPictureConverter converter;

    /* #############################################################################################
     *                                  constructors
     * ###########################################################################################*/

    /**
     * Creates a new anonymizer.
     */
    public OpenCVPythonAnonymizer() {
        this.converter = new VideoPictureConverter();
    }

    /* #############################################################################################
     *                                  methods
     * ###########################################################################################*/

    @Override
    public Boolean processVideo(File inputVideo, File outputVideo) {
        if (inputVideo == null || outputVideo == null)
            return false;

        // make temporary editing dir
        File editingDir = temporaryFileManager.getOrCreateFile(Long.toString(System.currentTimeMillis()));
        editingDir.delete();
        if (!editingDir.mkdir())
            return false;

        // clean up in ANY case (!)
        Boolean result = anonymizeVideo(inputVideo, outputVideo, editingDir);
        cleanUp(editingDir);
        return result;
    }

    /* #############################################################################################
     *                                  helper methods
     * ###########################################################################################*/

    /**
     * Anonymizes a video using a python-script.
     *
     * @param input      Input video file.
     * @param output     Output video file.
     * @param editingDir Directory used to temporarily save data.
     * @return Returns whether editing was successful or not.
     */
    private boolean anonymizeVideo(File input, File output, File editingDir) {
        IContainer container = IContainer.make();
        container.open(input.getAbsolutePath(), IContainer.Type.READ, null);
        IStream stream = container.getStream(0);

        if (stream == null) {
            Logger.getGlobal().warning("Uploaded file contained no video stream");
            return false;
        }

        // read settings
        IStreamCoder coder = stream.getStreamCoder();
        int width = coder.getHeight();
        int height = coder.getWidth();
        double fps = coder.getFrameRate().getValue();
        long length = container.getDuration() / 1000000;

        coder.setWidth(width);
        coder.setHeight(height);
        coder.close();
        container.close();

        Logger.getGlobal().info(String.format("Start splitting %s", input.getName()));

        // make temporary dir for pictures
        File picDir = new File(editingDir + File.separator + "input");
        if (!picDir.mkdir())
            return false;

        // split up video
        converter.splitUp(input, picDir, fps);

        Logger.getGlobal().info(String.format(
                "Start anonymizing %s. Fps:%d, Size:%d x %d, Dur:%ds",
                input.getName(), (int) fps, width, height, length));

        //anonymize
        try {
            Process p = Runtime.getRuntime().exec(
                    "python processing_chain.py -i " + picDir.getAbsolutePath() + " -f 10", null,
                    new File(PYTHON_DIR));

            p.waitFor();
            int status = p.exitValue();

            if (status != 0) {
                Logger.getGlobal().warning("Executing python script failed");
                return false;
            }
        } catch (IOException | InterruptedException e) {
            Logger.getGlobal().warning("Executing python script failed: " + e.getLocalizedMessage());
            return false;
        }

        Logger.getGlobal().info(String.format("Start merging %s", input.getName()));

        // merge video again
        File anonymPicDir = new File(picDir + ANONYM_SUFFIX);
        converter.merge(anonymPicDir, fps, width, height, output);

        return true;
    }

    /**
     * Deletes a files in a directory and the directory itself.
     *
     * @param editingDir Directory to be deleted.
     * @return Returns if deleting was successful or not.
     */
    private boolean cleanUp(File editingDir) {
        // delete all files
        try {
            FileUtils.cleanDirectory(editingDir);
            return editingDir.delete();
        } catch (IOException e) {
            return false;
        }
    }
}
