package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.GenericStep;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public class ImageStep extends GenericStep {

    protected Mat image;

    public ImageStep(Mat image, BasicConsoleLogger logger) {
        super(logger);
        this.image = image;
    }

    @Override
    public void doProcess() throws OpusCVException {

    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (image == null) {
            throw new OpusCVException(ErrorCode.IMG_INVALID_IMAGE);
        }
    }

    @Override
    public void finalize() throws OpusCVException {
        super.finalize();
        image.release();
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

}
