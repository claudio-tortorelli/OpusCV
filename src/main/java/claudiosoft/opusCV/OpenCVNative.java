package claudiosoft.opusCV;

import java.io.File;

/**
 *
 * @author Claudio
 */
public class OpenCVNative {

    private static boolean isLoaded = false;

    static {
        try {
            File nativeTemp = Utils.getFileFromRes("nat/opencv/win/" + Constants.NATIVE_WIN);
            System.load(nativeTemp.getAbsolutePath());
            isLoaded = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }

    public static boolean isLoaded() {
        return isLoaded;
    }

}
