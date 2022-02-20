package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.OpusCVException;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public class ThresholdImageStep extends ImageStep {

    private boolean adaptive;

    public ThresholdImageStep(boolean adaptive, Mat image) {
        super(image);
        //force dims between 1 and MAX_PIX
        this.adaptive = adaptive;
    }

    @Override
    public void prepare() throws OpusCVException {
        super.prepare();
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();

        logger.debug("");
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
