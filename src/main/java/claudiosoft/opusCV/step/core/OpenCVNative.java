package claudiosoft.opusCV.step.core;

import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Utils;
import claudiosoft.opusCV.common.Utils.OS;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.File;

/**
 *
 * @author Claudio
 */
public class OpenCVNative {

    private static boolean isReady = false;

    static {
        try {
            File nativeTemp = null;
            if (Utils.getOperatingSystem().equals(OS.WINDOWS)) {
                nativeTemp = Utils.getFileFromRes("nat/opencv/win/" + Constants.NATIVE_WIN);
            }
            if (nativeTemp == null) {
                throw new OpusCVException(ErrorCode.INIT_OPENCV_NOT_LOADED);
            }
            System.load(nativeTemp.getAbsolutePath());
            isReady = true;
        } catch (Exception ex) {
            BasicConsoleLogger.get().error(ex.getMessage(), ex);
        } finally {

        }
    }

    public static boolean isLoaded() {
        return isReady;
    }

}
