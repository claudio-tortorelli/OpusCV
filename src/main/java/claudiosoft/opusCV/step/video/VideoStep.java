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
        this.video = video;
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (video == null || !video.exists()) {
            throw new OpusCVException("video file not found");
        }
    }

    @Override
    public void prepare() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doProcess() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalize() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
