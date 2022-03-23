package claudiosoft.opusCV.step.video;

import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import java.io.File;

/**
 *
 * @author Claudio
 */
public class VideoStep extends BaseStep {

    protected File video;

    public VideoStep(File video) {
        super();
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

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
