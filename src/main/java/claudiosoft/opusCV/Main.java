package claudiosoft.opusCV;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.common.Options;
import claudiosoft.opusCV.step.image.CvtToGrayImageStep;
import claudiosoft.opusCV.step.image.ResizeImageStep;
import claudiosoft.opusCV.step.image.ShowImageStep;
import claudiosoft.opusCV.step.initialization.OpenCVInitStep;
import claudiosoft.opusCV.step.video.Video2PicStep;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.opencv.core.Core;

/**
 * TODO: error code enum
 *
 * opus cv exception con dettagli e print nel logger
 *
 * parser json input
 *
 * astrazione verso comandi opencv negli step in previsione di usare altr
 *
 * @author Claudio
 */
public class Main {

    private static BasicConsoleLogger logger = null;

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            ///////////////////////////////////////////////////////
            args = new String[20];
            args[0] = "loglevel=info";
            args[1] = "video.file=c:\\canc\\00040.avi";
//            args[2] = "video.startFrame=0";
//            args[3] = "video.endFrame=500";
            args[4] = "video.stepFrame=20";
//            args[5] = "video.bufferSize=2";
//            args[5] = "video.outFolder=C:\\canc\\testFrameRate10_color";
//            args[6] = "video.rotate=90cv";

//            args[7] = "frame.folder=C:\\canc\\Album_00040";
//            args[8] = "frame.basename=frame";
//            args[9] = "frame.rotate=90cv";
            ///////////////////////////////////////////////////////
        }

        Options opts = null;
        try {
            opts = parseArgs(args);
            if (opts.getOutFolder() == null) {
                String name = "";
                if (opts.getFile() != null) {
                    name = opts.getFile().getName().replaceFirst("[.][^.]+$", "");
                    opts.setOutFolder(new File(opts.getFile().getParent() + File.separator + "Album_" + name));
                } else if (opts.getFrameFolder() != null) {
                    opts.setOutFolder(opts.getFrameFolder());
                } else {
                    throw new IOException("Undefined output folder");
                }
            }
            Files.createDirectories(opts.getOutFolder().toPath());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.exit(Constants.RET_CODE_ERR);
        }

        try {
            logger = new BasicConsoleLogger(opts.getLoggerLevel());

            Processor process = new Processor(opts);
            process.addStep(new OpenCVInitStep(logger));
            if (opts.getFile() != null && opts.getFrameFolder() == null) {
                Video2PicStep videoStep = new Video2PicStep(opts.getFile(), opts.getOutFolder(), logger);
                if (opts.getStartFrame() >= 0) {
                    videoStep.setStartFrame(opts.getStartFrame());
                }
                if (opts.getEndFrame() >= 0) {
                    videoStep.setEndFrame(opts.getEndFrame());
                }
                if (opts.getStepFrame() >= 0) {
                    videoStep.setStepFrame(opts.getStepFrame());
                }
                videoStep.addStep(new ResizeImageStep(0.7, null, logger));
                videoStep.addStep(new ShowImageStep("OpusCV - Videograbbing", null, logger));
                videoStep.addStep(new ResizeImageStep(0.7, null, logger));
                videoStep.addStep(new CvtToGrayImageStep(null, logger));
                process.addStep(videoStep);
            }
            // start process
            process.doProcess();
        } catch (Exception ex) {
            if (logger != null) {
                logger.error(ex.getMessage(), ex);
            } else {
                ex.printStackTrace(System.out);
            }
            System.exit(Constants.RET_CODE_ERR);
        }
        System.exit(Constants.RET_CODE_OK);
    }

    public static Options parseArgs(String[] args) {

        System.out.println("Start parsing input data");

        Options opts = new Options();
        for (int iAr = 0; iAr < args.length; iAr++) {
            if (args[iAr] == null || args[iAr].isEmpty()) {
                continue;
            }

            String param = args[iAr].toLowerCase().trim();//TODO, not work on linux
            if (param.startsWith("loglevel=")) {
                String tmp = param.replace("loglevel=", "");
                if (tmp.equals("info")) {
                    opts.setLoggerLevel(BasicConsoleLogger.LogLevel.NORMAL);
                } else if (tmp.equals("debug")) {
                    opts.setLoggerLevel(BasicConsoleLogger.LogLevel.DEBUG);
                }
            } else if (param.startsWith("video.file=")) {
                opts.setFile(new File(param.replace("video.file=", "")));
            } else if (param.startsWith("video.startframe=")) {
                opts.setStartFrame(Integer.parseInt(param.replace("video.startframe=", "")));
            } else if (param.startsWith("video.endframe=")) {
                opts.setEndFrame(Integer.parseInt(param.replace("video.endframe=", "")));
            } else if (param.startsWith("video.stepframe=")) {
                opts.setStepFrame(Integer.parseInt(param.replace("video.stepframe=", "")));
            } else if (param.startsWith("video.buffersize=")) {
                opts.setBufferSize(Integer.parseInt(param.replace("video.buffersize=", "")));
            } else if (param.startsWith("video.outfolder=")) {
                opts.setOutFolder(new File(param.replace("video.outfolder=", "")));
            } else if (param.startsWith("video.rotate=")) {
                String rot = param.replace("video.rotate=", "");
                if (rot.equals("90cv")) {
                    opts.setVideoRotation(Core.ROTATE_90_CLOCKWISE);
                } else if (rot.equals("90ccv")) {
                    opts.setVideoRotation(Core.ROTATE_90_COUNTERCLOCKWISE);
                } else if (rot.equals("180")) {
                    opts.setVideoRotation(Core.ROTATE_180);
                }
            } else if (param.startsWith("frame.folder=")) {
                opts.setFrameFolder(new File(param.replace("frame.folder=", "")));
            } else if (param.startsWith("frame.basename=")) {
                opts.setFrameBaseName(param.replace("frame.basename=", ""));
            } else if (param.startsWith("frame.rotate=")) {
                String rot = param.replace("frame.rotate=", "");
                if (rot.equals("90cv")) {
                    opts.setFrameRotation(Core.ROTATE_90_CLOCKWISE);
                } else if (rot.equals("90ccv")) {
                    opts.setFrameRotation(Core.ROTATE_90_COUNTERCLOCKWISE);
                } else if (rot.equals("180")) {
                    opts.setFrameRotation(Core.ROTATE_180);
                }
            } else {
                throw new IllegalArgumentException("unrecognized input argument: " + param);
            }
        }
        return opts;
    }

}
