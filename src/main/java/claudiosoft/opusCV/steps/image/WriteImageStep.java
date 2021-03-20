package claudiosoft.opusCV.steps.image;

import claudiosoft.opusCV.BasicConsoleLogger;
import java.io.File;
import java.io.IOException;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Claudio
 */
public class WriteImageStep extends ImageStep {

    protected File outFile;
    protected boolean overwrite;

    public WriteImageStep(File outFile, Mat image, BasicConsoleLogger logger) {
        super(image, logger);
        this.outFile = outFile;
        this.overwrite = true;
    }

    @Override
    public void doProcess() throws Exception {
        super.doProcess();
        if (outFile.exists() && !overwrite) {
            throw new IOException("output file already exists: " + outFile.getAbsolutePath());
        }
        outFile.delete();
        Imgcodecs.imwrite(outFile.getAbsolutePath(), image);
        logger.debug("image saved to " + outFile.getAbsolutePath());
    }

    @Override
    public void checkPrerequisites() throws Exception {
        super.checkPrerequisites();
        if (outFile == null) {
            throw new IOException("output file not defined");
        }
    }

    public File getOutFile() {
        return outFile;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

}
