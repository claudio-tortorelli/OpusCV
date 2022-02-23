package claudiosoft.opusCV.step.video;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import java.io.File;
import java.util.LinkedList;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 *
 * @author Claudio
 */
public class Video2PicStep extends VideoGrabberStep {

    private int bufferSize;
    private File outFolder;

    public Video2PicStep(File video, File outFolder) {
        super(video);
        this.bufferSize = 2;
        this.outFolder = outFolder;
    }

    @Override
    public void doProcess() throws OpusCVException {
        logger.info("processing " + video.getAbsolutePath());

        VideoCapture cap = new VideoCapture(video.getAbsolutePath());
        if (!cap.isOpened()) {
            throw new OpusCVException(ErrorCode.VID_CANNOT_OPEN);
        }

        int totFrame = (int) cap.get(Videoio.CAP_PROP_FRAME_COUNT);
        if (endFrame == Integer.MAX_VALUE) {
            endFrame = totFrame;
        }
        int finalFrame = Integer.min(totFrame, endFrame);

        logger.info("size " + video.length() / (1024 * 1024) + " MB - " + totFrame + " frames - from " + startFrame + " to " + finalFrame);

        LinkedList<Mat> frameBuffer = new LinkedList<>();
        LinkedList<Mat> workImageBuffer = new LinkedList<>();

        LinkedList<Double> sumsSerie = new LinkedList<>();

        Mat frame = new Mat();
        int processedFrames = 0;
        try {
            for (int frame_count = startFrame; frame_count < finalFrame; frame_count++) {
                if (frame_count % stepFrame != 0) {
                    continue;
                }
                cap.set(Videoio.CAP_PROP_POS_FRAMES, frame_count);
                if (!cap.read(frame)) {
                    logger.error("failed to extract frame " + frame_count);
                    continue;
                }
//                logger.debug("processing frame " + frame_count);
//
//                Mat workImage = null;
//                for (GenericStep subStep : next) {
//                    if (ImageStep.class.isAssignableFrom(subStep.getClass())) {
//                        ImageStep imgStep = ((ImageStep) subStep);
//                        if (workImage == null) {
//                            imgStep.setImage(frame);
//                        } else {
//                            imgStep.setImage(workImage);
//                        }
//                        if (imgStep instanceof ShowImageStep) {
//                            double percentProgress = (processedFrames * stepFrame) * 100 / (double) (endFrame - startFrame);
//                            String msg = String.format("frame %d / %d - %.1f %%", startFrame + (processedFrames * stepFrame), (endFrame - startFrame), percentProgress);
//                            ((ShowImageStep) imgStep).setText(msg);
//                        }
//                        imgStep.checkPrerequisites();
//                        imgStep.doProcess();
//                        workImage = imgStep.getImage(); // update work image
//                    }
//                }
//
//                // handle buffers as fifo
//                frameBuffer.add(frame);
//                workImageBuffer.add(workImage);
//                if (frameBuffer.size() > bufferSize) {
//                    frameBuffer.removeFirst();
//                    workImageBuffer.removeFirst();
//                }
//
//                //TODO...to be handled better! in a pic
//                if (frameBuffer.size() >= 2) {
//                    Mat prevImg = workImageBuffer.get(0);
//
//                    //TODO: two images must be equalized before sub
//                    // oppure eseguire una media utilizzando un buffer
////                    Mat prevImgHSV = new Mat(prevImg.rows(), prevImg.cols(), CvType.CV_8UC1);
////                    Imgproc.cvtColor(prevImg, prevImgHSV, Imgproc.COLOR_RGB2GRAY);
////
//                    Mat thresholdWork = new Mat(workImage.rows(), workImage.cols(), CvType.CV_8UC1);
////                    Imgproc.threshold(workImage, thresholdWork, 128, 255, 0);
//                    Imgproc.adaptiveThreshold(workImage, thresholdWork, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 15, 4);
//
//                    Mat thresholdPrev = new Mat(workImageBuffer.get(0).rows(), workImageBuffer.get(0).cols(), CvType.CV_8UC1);
////                    Imgproc.threshold(workImageBuffer.get(0), thresholdPrev, 128, 255, 0);
//                    Imgproc.adaptiveThreshold(workImageBuffer.get(0), thresholdPrev, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 15, 4);
//
//                    Mat subtractResult = new Mat(workImage.rows(), workImage.cols(), CvType.CV_8UC1);
//                    Core.absdiff(thresholdPrev, thresholdWork, subtractResult);
//
////                    Imgcodecs.imwrite("c:\\canc\\threshold.jpg", thresholdedImage);
//                    //double sumDiff = Core.sumElems(subtractResult).val[0];
//                    sumsSerie.add((double) Core.countNonZero(subtractResult));
//                }
//                processedFrames++;
            }

//            double minSerie = Double.MAX_VALUE;
//            for (double val : sumsSerie) {
//                if (val < minSerie) {
//                    minSerie = val;
//                }
//            }
//
//            File sumsSerieFile = new File(outFolder.getAbsolutePath() + File.separator + Constants.SUM_SERIE_FILENAME);
//            sumsSerieFile.delete();
//            sumsSerieFile.createNewFile();
//            for (Double diffVal : sumsSerie) {
//                Files.write(sumsSerieFile.toPath(), String.format("%.2f\n", diffVal - minSerie).getBytes(), StandardOpenOption.APPEND);
//            }
        } finally {
            cap.release();
            logger.info(processedFrames + " frames processed");
        }
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        if (outFolder == null) {
            throw new OpusCVException("Invalid output folder");
        }
        super.checkPrerequisites();
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public File getOutFolder() {
        return outFolder;
    }

    public void setOutFolder(File outFolder) {
        this.outFolder = outFolder;
    }

}
