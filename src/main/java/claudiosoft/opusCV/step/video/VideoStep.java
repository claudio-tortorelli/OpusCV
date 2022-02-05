package claudiosoft.opusCV.step.video;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.GenericStep;
import java.io.File;

/**
 *
 * @author Claudio
 */
public class VideoStep extends GenericStep {

    protected File video;

    public VideoStep(File video, BasicConsoleLogger logger) {
        super(logger);
        this.video = video;
    }

    @Override
    public void doProcess() throws OpusCVException {

    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (video == null || !video.exists()) {
            throw new OpusCVException("video file not found");
        }
    }

}
