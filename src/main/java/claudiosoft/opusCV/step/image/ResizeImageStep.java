package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.OpusCVException;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Claudio
 */
public class ResizeImageStep extends ImageStep {

    private Size dims;
    private final int MAX_PIX = 20000;
    private double ratio = 1.0;

    public ResizeImageStep(double width, double height, Mat image) {
        super(image);
        //force dims between 1 and MAX_PIX
        this.dims = new Size(Double.min(Double.max(1.0, width), MAX_PIX), Double.min(Double.max(1, height), MAX_PIX));
    }

    public ResizeImageStep(double ratio, Mat image) {
        super(image);
        this.ratio = ratio;
        this.dims = null;
    }

    @Override
    public void prepare() throws OpusCVException {
        super.prepare();
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
        if (dims != null) {
            // use custom dimensions
            Imgproc.resize(image, image, dims);
        } else {
            Size srcSize = image.size();
            Size dstSize = new Size(srcSize.width * ratio, srcSize.height * ratio);
            Imgproc.resize(image, image, dstSize);
        }
        logger.debug("image resized to '" + image.size().width + " x " + image.size().height);
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        super.checkPrerequisites();
    }

    @Override
    public void finalize() throws OpusCVException {
        super.finalize();
    }

}
