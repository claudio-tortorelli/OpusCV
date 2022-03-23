package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public abstract class ImageStep extends BaseStep {

    protected Mat image;

    public ImageStep(String imageFilePath) {

    }

    public ImageStep(Mat image) {
        super();
        this.image = image;
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        super.checkPrerequisites();
        if (image == null) {
            throw new OpusCVException(ErrorCode.IMG_INVALID_IMAGE);
        }
    }

    @Override
    public void prepare() throws OpusCVException {
        super.prepare();
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
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

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
