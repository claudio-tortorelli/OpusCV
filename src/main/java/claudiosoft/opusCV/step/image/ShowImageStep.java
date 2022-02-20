package claudiosoft.opusCV.step.image;

import claudiosoft.opusCV.common.OpusCVException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Claudio
 */
public class ShowImageStep extends ImageStep {

    private String windowName;
    private int frameRate;
    private WinType winType;
    private String text;

    public enum WinType {
        NORMAL,
        AUTOSIZE
    }

    public ShowImageStep(String windowName, Mat image) {
        this(windowName, WinType.NORMAL, 24, image);
    }

    public ShowImageStep(String windowName, WinType winType, Mat image) {
        this(windowName, winType, 24, image);
    }

    public ShowImageStep(String windowName, WinType winType, int frameRate, Mat image) {
        super(image);
        this.windowName = windowName;
        this.winType = winType;
        this.frameRate = Integer.max(0, frameRate);
    }

    @Override
    public void prepare() throws OpusCVException {
        super.prepare();
        HighGui.namedWindow(this.windowName, this.winType.ordinal());
        logger.debug("'" + windowName + "' window created");
    }

    @Override
    public void doProcess() throws OpusCVException {
        super.doProcess();
        HighGui.imshow(this.windowName, image);
        if (!text.isEmpty()) {
            Imgproc.putText(image, text, new Point(10, 15), Core.FONT_HERSHEY_DUPLEX, 0.5, new Scalar(0, 255, 0));
        }
        HighGui.waitKey(this.frameRate);
        logger.debug("image updated to '" + this.windowName + "' window");
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        super.checkPrerequisites();
    }

    @Override
    public void finalize() throws OpusCVException {
        super.finalize();
        HighGui.destroyWindow(windowName);
        logger.debug("'" + windowName + "' window destroyed");
    }

    public String getWindowName() {
        return windowName;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public WinType getWinType() {
        return winType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
