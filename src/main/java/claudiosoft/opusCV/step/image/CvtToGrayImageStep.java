package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.OpusCVException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Claudio
 */
public class CvtToGrayImageStep extends ImageStep {

    public CvtToGrayImageStep(Mat image, BasicConsoleLogger logger) {
        super(image, logger);
    }

    @Override
    public void prepare() throws OpusCVException {
        super.prepare();
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
        Mat cvtImg = new Mat(image.rows(), image.cols(), CvType.CV_8UC1);
        Imgproc.cvtColor(image, cvtImg, Imgproc.COLOR_BGR2GRAY);
        image = cvtImg;
        logger.debug("image color converted to gray");
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
