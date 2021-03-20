package claudiosoft.opusCV.steps.video;

import claudiosoft.opusCV.BasicConsoleLogger;
import claudiosoft.opusCV.steps.GenericStep;
import java.io.File;
import java.io.FileNotFoundException;

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
    public void doProcess() throws Exception {

    }

    @Override
    public void checkPrerequisites() throws Exception {
        if (video == null || !video.exists()) {
            throw new FileNotFoundException("video file not found");
        }
    }

}
