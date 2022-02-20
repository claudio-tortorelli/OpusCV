package claudiosoft.opusCV.step.video;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.image.ImageStep;
import java.io.File;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_COUNT;
import static org.opencv.videoio.Videoio.CAP_PROP_POS_FRAMES;

/**
 *
 * @author Claudio
 */
public class VideoGrabberStep extends VideoStep {

    protected int startFrame;
    protected int endFrame;
    protected int stepFrame;

    public VideoGrabberStep(File video) {
        super(video);
        startFrame = 0;
        endFrame = Integer.MAX_VALUE;
        stepFrame = 1;
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
        logger.info("processing " + video.getAbsolutePath());

        VideoCapture cap = new VideoCapture(video.getAbsolutePath());
        if (!cap.isOpened()) {
            throw new OpusCVException(ErrorCode.VID_CANNOT_OPEN);
        }
        int totFrame = (int) cap.get(CAP_PROP_FRAME_COUNT);
        if (endFrame == Integer.MAX_VALUE) {
            endFrame = totFrame;
        }
        int finalFrame = Integer.min(totFrame, endFrame);
        logger.info("size " + video.length() / (1024 * 1024) + " MB - " + totFrame + " frames - from " + startFrame + " to " + finalFrame);

        Mat frame = new Mat();
        int processedFrames = 0;
        try {
            for (int frame_count = startFrame; frame_count < finalFrame; frame_count++) {
                if (frame_count % stepFrame != 0) {
                    continue;
                }
                cap.set(CAP_PROP_POS_FRAMES, frame_count);
                if (!cap.read(frame)) {
                    logger.error("failed to extract frame " + frame_count);
                    continue;
                }
                logger.debug("processing frame " + frame_count);

                Mat workImage = null;
                for (BaseStep subStep : subSteps) {
                    if (!ImageStep.class.isAssignableFrom(subStep.getClass())) {
                        continue; //skip
                    }
                    ImageStep imgStep = ((ImageStep) subStep);
                    if (workImage == null) {
                        imgStep.setImage(frame);
                    } else {
                        imgStep.setImage(workImage);
                    }
                    imgStep.checkPrerequisites();
                    imgStep.doProcess();
                    workImage = imgStep.getImage(); // update work image
                }
                processedFrames++;
            }
        } finally {
            logger.info(processedFrames + " frames processed");
        }
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (startFrame >= endFrame) {
            throw new OpusCVException(ErrorCode.VID_INVALID_FRAME_RANGE);
        }
        if (stepFrame < 1) {
            throw new OpusCVException(ErrorCode.VID_INVALID_FRAME_STEP);
        }
        super.checkPrerequisites();
    }

    public int getStartFrame() {
        return startFrame;
    }

    public void setStartFrame(int startFrame) {
        this.startFrame = startFrame;
        logger.info("Start frame set to " + startFrame);
    }

    public int getEndFrame() {
        return endFrame;
    }

    public void setEndFrame(int endFrame) {
        this.endFrame = endFrame;
        logger.info("End frame set to " + endFrame);
    }

    public int getStepFrame() {
        return stepFrame;
    }

    public void setStepFrame(int stepFrame) {
        this.stepFrame = Integer.max(1, stepFrame);
        logger.info("Step frame set to " + stepFrame);
    }

}
