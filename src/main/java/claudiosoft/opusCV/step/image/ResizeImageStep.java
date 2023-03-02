package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.image.Image;
import claudiosoft.opusCV.image.ImageProvider;
import claudiosoft.opusCV.image.OpenCVImage;
import claudiosoft.opusCV.step.StepCategory;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Claudio
 */
public class ResizeImageStep extends ImageStep {

    private final Size destSize;
    private final int MAX_PIX;

    public ResizeImageStep(Image targetImage, double targetWidth, double targeHeight) throws OpusCVException {
        this(targetImage.getPath(), targetWidth, targeHeight, Configuration.get().getDefaultCVProvider());
    }

    public ResizeImageStep(Image targetImage, double targetWidth, double targeHeight, CVProvider provider) throws OpusCVException {
        this(targetImage.getPath(), targetWidth, targeHeight, provider);
    }

    public ResizeImageStep(String imagePath, double targetWidth, double targeHeight) throws OpusCVException {
        this(imagePath, targetWidth, targeHeight, Configuration.get().getDefaultCVProvider());
    }

    public ResizeImageStep(String imagePath, double targetWidth, double targeHeight, CVProvider provider) throws OpusCVException {
        super(ObjectTypeName.RESIZE_IMAGE_STEP, StepCategory.IMAGE, provider);
        this.MAX_PIX = 20000;
        image = ImageProvider.get().image(imagePath, provider);
        // force dims between 1 and MAX_PIX
        destSize = new Size(Double.min(Double.max(1.0, targetWidth), MAX_PIX), Double.min(Double.max(1, targeHeight), MAX_PIX));
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        super.checkPrerequisites();
    }

    @Override
    public void prepare() throws OpusCVException {
        super.prepare();
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
        if (!getProvider().equals(CVProvider.OPENCV) || !(image instanceof OpenCVImage)) {
            throw new OpusCVException(ErrorCode.UNSUPPORTED_PROVIDER);
        }
        OpenCVImage cvImage = (OpenCVImage) image;
        Imgproc.resize(cvImage.getRaw(), cvImage.getRaw(), destSize);
        logger.debug("image resized to " + destSize.width + " x " + destSize.height + " pix");
    }

    @Override
    public void finalize() throws OpusCVException {
        super.finalize();
    }

    public double getRatio() {
        return destSize.width / (double) destSize.height;
    }

}
