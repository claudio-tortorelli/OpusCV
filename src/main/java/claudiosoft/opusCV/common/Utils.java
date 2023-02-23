package claudiosoft.opusCV.common;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
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
import java.util.LinkedList;
import java.util.List;
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

    /**
     * https://github.com/drewnoakes/metadata-extractor/wiki/Getting-Started-(Java)
     *
     * @throws ImageProcessingException
     * @throws IOException
     */
    public static List<String> getMetadata(File mediaFile) throws ImageProcessingException, IOException {
        List<String> extractedValues = new LinkedList<>();
        Metadata metadata = ImageMetadataReader.readMetadata(mediaFile);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                extractedValues.add(tag.toString());
            }
        }
        return extractedValues;
    }

    //    public static Options parseArgs(String[] args) {
//
//        System.out.println("Start parsing input data");
//
//        Options opts = new Options();
//        for (int iAr = 0; iAr < args.length; iAr++) {
//            if (args[iAr] == null || args[iAr].isEmpty()) {
//                continue;
//            }
//
//            String param = args[iAr].toLowerCase().trim();//TODO, not work on linux
//            if (param.startsWith("loglevel=")) {
//                String tmp = param.replace("loglevel=", "");
//                if (tmp.equals("info")) {
//                    opts.setLoggerLevel(BasicConsoleLogger.LogLevel.NORMAL);
//                } else if (tmp.equals("debug")) {
//                    opts.setLoggerLevel(BasicConsoleLogger.LogLevel.DEBUG);
//                }
//            } else if (param.startsWith("video.file=")) {
//                opts.setFile(new File(param.replace("video.file=", "")));
//            } else if (param.startsWith("video.startframe=")) {
//                opts.setStartFrame(Integer.parseInt(param.replace("video.startframe=", "")));
//            } else if (param.startsWith("video.endframe=")) {
//                opts.setEndFrame(Integer.parseInt(param.replace("video.endframe=", "")));
//            } else if (param.startsWith("video.stepframe=")) {
//                opts.setStepFrame(Integer.parseInt(param.replace("video.stepframe=", "")));
//            } else if (param.startsWith("video.buffersize=")) {
//                opts.setBufferSize(Integer.parseInt(param.replace("video.buffersize=", "")));
//            } else if (param.startsWith("video.outfolder=")) {
//                opts.setOutFolder(new File(param.replace("video.outfolder=", "")));
//            } else if (param.startsWith("video.rotate=")) {
//                String rot = param.replace("video.rotate=", "");
//                if (rot.equals("90cv")) {
//                    opts.setVideoRotation(Core.ROTATE_90_CLOCKWISE);
//                } else if (rot.equals("90ccv")) {
//                    opts.setVideoRotation(Core.ROTATE_90_COUNTERCLOCKWISE);
//                } else if (rot.equals("180")) {
//                    opts.setVideoRotation(Core.ROTATE_180);
//                }
//            } else if (param.startsWith("frame.folder=")) {
//                opts.setFrameFolder(new File(param.replace("frame.folder=", "")));
//            } else if (param.startsWith("frame.basename=")) {
//                opts.setFrameBaseName(param.replace("frame.basename=", ""));
//            } else if (param.startsWith("frame.rotate=")) {
//                String rot = param.replace("frame.rotate=", "");
//                if (rot.equals("90cv")) {
//                    opts.setFrameRotation(Core.ROTATE_90_CLOCKWISE);
//                } else if (rot.equals("90ccv")) {
//                    opts.setFrameRotation(Core.ROTATE_90_COUNTERCLOCKWISE);
//                } else if (rot.equals("180")) {
//                    opts.setFrameRotation(Core.ROTATE_180);
//                }
//            } else {
//                throw new IllegalArgumentException("unrecognized input argument: " + param);
//            }
//        }
//        return opts;
//    }
}
