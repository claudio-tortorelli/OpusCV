package claudiosoft.opusCV.image;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Claudio
 */
public class OpenCVImage extends Image {

    protected Mat rawImage;

    public OpenCVImage(String imagePath) {
        super();
        this.rawImage = Imgcodecs.imread(imagePath);
        setSize(rawImage.size().width, rawImage.size().height);
        setDepth(rawImage.depth());
    }

    @Override
    public Mat getRaw() {
        return rawImage;
    }

    @Override
    public void release() {
        rawImage.release();
    }
}
