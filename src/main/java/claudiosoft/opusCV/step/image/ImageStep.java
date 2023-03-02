package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.image.Image;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.StepCategory;

/**
 *
 * @author Claudio
 */
public abstract class ImageStep extends BaseStep {

    protected Image image = null;

    public ImageStep(ObjectTypeName objName, StepCategory category) throws OpusCVException {
        this(objName, category, Configuration.get().getDefaultCVProvider());
    }

    public ImageStep(ObjectTypeName objName, StepCategory category, CVProvider provider) throws OpusCVException {
        super(objName, category, provider);
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {

    }

    @Override
    public void prepare() throws OpusCVException {

    }

    @Override
    public void doProcess() throws OpusCVException {

    }

    @Override
    public void finalize() throws OpusCVException {

    }

    public Image getResultImage() {
        return image;
    }

}
