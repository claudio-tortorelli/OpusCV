package claudiosoft.opusCV.step.video;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.GenericStep;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

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

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
