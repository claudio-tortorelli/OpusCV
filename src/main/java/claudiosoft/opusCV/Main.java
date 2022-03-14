package claudiosoft.opusCV;

import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.IOException;

/**
 *
 * @author Claudio
 */
public class Main {

    private static BasicConsoleLogger logger = BasicConsoleLogger.get();

    public static void main(String[] args) throws IOException {

        try {
            Processor process = new Processor();
            //TODO, enable processor's steps
            // start process
            process.doProcess();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(Constants.RET_CODE_ERR);
        }
        System.exit(Constants.RET_CODE_OK);
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
