package claudiosoft.opusCV.image;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Provider;

/**
 *
 * @author Claudio
 */
public class ImageProvider {

    public static Image get(String imagePath) throws OpusCVException {
        return get(imagePath, Provider.OPENCV);
    }

    public static Image get(String imagePath, Provider provider) throws OpusCVException {
        if (provider.equals(Provider.OPENCV)) {
            return new OpenCVImage(imagePath);
        }
        throw new OpusCVException(ErrorCode.UNSUPPORTED_PROVIDER);
    }

    private ImageProvider() {

    }
}
