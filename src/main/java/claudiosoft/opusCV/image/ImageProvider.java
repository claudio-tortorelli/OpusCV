package claudiosoft.opusCV.image;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import java.util.HashMap;

/**
 *
 * @author Claudio
 */
public class ImageProvider {

    private static ImageProvider imageProvider;

    private static HashMap<String, Image> imgMap; //TODO, change to LRU Map

    public Image image(String imagePath) throws OpusCVException {
        return image(imagePath, Configuration.get().getDefaultCVProvider());
    }

    public Image image(String imagePath, CVProvider provider) throws OpusCVException {
        if (imgMap.containsKey(imagePath)) {
            return imgMap.get(imagePath);
        }
        Image retImage = null;
        if (provider.equals(CVProvider.OPENCV)) {
            retImage = new OpenCVImage(imagePath);
        }
        if (retImage == null) {
            throw new OpusCVException(ErrorCode.UNSUPPORTED_PROVIDER);
        }
        synchronized (this) {
            imgMap.put(imagePath, retImage);
        }
        return retImage;
    }

    public synchronized void clearAll() {
        for (Image img : imgMap.values()) {
            img.release();
        }
        imgMap.clear();
    }

    public static ImageProvider get() {
        if (imageProvider != null) {
            return imageProvider;
        }
        imageProvider = new ImageProvider();
        return imageProvider;
    }

    private ImageProvider() {
        imgMap = new HashMap<>();
    }
}
