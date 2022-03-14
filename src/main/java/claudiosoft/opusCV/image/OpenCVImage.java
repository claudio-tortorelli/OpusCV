package claudiosoft.opusCV.image;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Claudio
 */
public class OpenCVImage extends Image {

    private Mat rawImage;

    public OpenCVImage(String imagePath) {
        super();
        rawImage = Imgcodecs.imread(imagePath);
        setSize(rawImage.size().width, rawImage.size().height);
        setDepth(rawImage.depth());
    }

    public Mat getRaw() {
        return rawImage;
    }

    //TODO...serve?
    public void setRaw(Mat image) {
        this.rawImage = image;
    }

}
