package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.image.Image;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.StepCategory;

/**
 *
 * @author Claudio
 */
public abstract class ImageStep extends BaseStep {

    protected Image image;
    protected String imagePath;

    public ImageStep(ObjectTypeName objName, StepCategory category, CVProvider provider) {
        super(objName, category, provider);
        this.imagePath = "";
        this.image = null;
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (image == null) {
            throw new OpusCVException(ErrorCode.IMG_INVALID_IMAGE);
        }
    }

    @Override
    public void prepare() throws OpusCVException {

    }

    @Override
    public void doProcess() throws OpusCVException {

    }

    @Override
    public void finalize() throws OpusCVException {
        image.release();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
