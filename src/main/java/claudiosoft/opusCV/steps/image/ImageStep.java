package claudiosoft.opusCV.steps.image;

import claudiosoft.opusCV.BasicConsoleLogger;
import claudiosoft.opusCV.steps.GenericStep;
import java.io.IOException;
import org.opencv.core.Mat;

/**
 *
 * @author Claudio
 */
public class ImageStep extends GenericStep {

    protected Mat image;

    public ImageStep(Mat image, BasicConsoleLogger logger) {
        super(logger);
        this.image = image;
    }

    @Override
    public void doProcess() throws Exception {

    }

    @Override
    public void checkPrerequisites() throws Exception {
        if (image == null) {
            throw new IOException("invalid image");
        }
    }

    @Override
    public void finalize() throws Exception {
        super.finalize();
        image.release();
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

}
