package claudiosoft.opusCV.steps.check;

import claudiosoft.opusCV.BasicConsoleLogger;
import claudiosoft.opusCV.OpenCVNative;
import claudiosoft.opusCV.steps.GenericStep;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public class OpenCVCheckStep extends GenericStep {

    public OpenCVCheckStep(BasicConsoleLogger logger) {
        super(logger);
    }

    @Override
    public void doProcess() throws Exception {
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        logger.debug("test: mat = " + mat.dump());
    }

    @Override
    public void checkPrerequisites() throws Exception {
        if (!OpenCVNative.isLoaded()) {
            throw new Exception("OpenCVNative not loaded");
        }
    }
}
