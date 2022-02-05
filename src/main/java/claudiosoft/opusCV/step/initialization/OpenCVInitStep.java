package claudiosoft.opusCV.step.initialization;

import claudiosoft.opusCV.OpenCVNative;
import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.GenericStep;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public class OpenCVInitStep extends GenericStep {

    public OpenCVInitStep(BasicConsoleLogger logger) {
        super(logger);
    }

    @Override
    public void doProcess() throws OpusCVException {
        try {
            Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
            logger.debug("opencv test: mat = " + mat.dump());
        } catch (Exception ex) {
            throw new OpusCVException(ErrorCode.INIT_OPENCV_ERROR, ex);
        }
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (!OpenCVNative.isLoaded()) {
            throw new OpusCVException(ErrorCode.INIT_OPENCV_NOT_LOADED);
        }
    }
}
