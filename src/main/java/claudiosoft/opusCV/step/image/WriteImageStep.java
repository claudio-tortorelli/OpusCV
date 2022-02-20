package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import java.io.File;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Claudio
 */
public class WriteImageStep extends ImageStep {

    protected File outFile;
    protected boolean overwrite;

    public WriteImageStep(File outFile, Mat image) {
        super(image);
        this.outFile = outFile;
        this.overwrite = true;
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
        if (outFile.exists() && !overwrite) {
            logger.error(String.format("this image can be overridden: %s", outFile.getAbsolutePath()));
            throw new OpusCVException(ErrorCode.IMG_ALREADY_EXISTS);
        }
        outFile.delete();
        Imgcodecs.imwrite(outFile.getAbsolutePath(), image);
        logger.debug("image saved to " + outFile.getAbsolutePath());
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        super.checkPrerequisites();
        if (outFile == null) {
            throw new OpusCVException(ErrorCode.IMG_OUTPUT_NOT_DEFINED);
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
