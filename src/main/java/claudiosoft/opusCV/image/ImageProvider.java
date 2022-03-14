package claudiosoft.opusCV.image;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;

/**
 *
 * @author Claudio
 */
public class ImageProvider {

    public static Image get(String imagePath) throws OpusCVException {
        return get(imagePath, EngineType.OPENCV);
    }

    public static Image get(String imagePath, EngineType engine) throws OpusCVException {
        if (engine.equals(EngineType.OPENCV)) {
            return new OpenCVImage(imagePath);
        }
        throw new OpusCVException(ErrorCode.UNSUPPORTED_ENGINE);
    }

    private ImageProvider() {

    }
}
