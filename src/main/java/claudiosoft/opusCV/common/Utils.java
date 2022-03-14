package claudiosoft.opusCV.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Claudio
 */
public class Utils {

    public static File getFileFromRes(String resPath) throws IOException {

        URL resUrl = Thread.currentThread().getContextClassLoader().getResource(resPath);
        if (resUrl == null) {
            throw new FileNotFoundException("not found " + resPath);
        }
        String urlStr = resUrl.toString();
        InputStream is = null;
        File tmp = null;
        try {
            if (urlStr.startsWith("jar:")) {
                final JarURLConnection connection = (JarURLConnection) resUrl.openConnection();
                is = connection.getInputStream();
                tmp = File.createTempFile("native", ".tmp");
                java.nio.file.Files.copy(is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return tmp;
            } else if (urlStr.startsWith("file:")) {
                return new File(resUrl.getFile());
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (tmp != null) {
                tmp.deleteOnExit();
            }
        }
        throw new FileNotFoundException("not found " + resPath);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(date);
    }

    /**
     * Supported OS
     */
    public static enum OS {
        WINDOWS,
        LINUX,
        OSX,
        UNKNOWN
    }

    public static OS getOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("mac") || osName.startsWith("darwin")) {
            return OS.OSX;
        } else if (osName.startsWith("linux")) {
            return OS.LINUX;
        } else if (osName.startsWith("windows")) {
            return OS.WINDOWS;
        }
        return OS.UNKNOWN;
    }
}
