package claudiosoft.opusCV.image;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.CVProvider;

/**
 *
 * @author Claudio
 */
public class ImageProvider {

    public static Image get(String imagePath) throws OpusCVException {
        return get(imagePath, CVProvider.OPENCV);
    }

    public static Image get(String imagePath, CVProvider provider) throws OpusCVException {
        if (provider.equals(CVProvider.OPENCV)) {
            return new OpenCVImage(imagePath);
        }
        throw new OpusCVException(ErrorCode.UNSUPPORTED_PROVIDER);
    }

    private ImageProvider() {

    }
}
