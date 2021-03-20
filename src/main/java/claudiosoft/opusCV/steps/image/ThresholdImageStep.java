package claudiosoft.opusCV.steps.image;

import claudiosoft.opusCV.BasicConsoleLogger;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public class ThresholdImageStep extends ImageStep {

    private boolean adaptive;

    public ThresholdImageStep(boolean adaptive, Mat image, BasicConsoleLogger logger) {
        super(image, logger);
        //force dims between 1 and MAX_PIX
        this.adaptive = adaptive;
    }

    @Override
    public void prepare() throws Exception {
        super.prepare();
    }

    @Override
    public void doProcess() throws Exception {
        super.doProcess();

        logger.debug("");
    }

    @Override
    public void checkPrerequisites() throws Exception {
        super.checkPrerequisites();
    }

    @Override
    public void finalize() throws Exception {
        super.finalize();
    }

}
