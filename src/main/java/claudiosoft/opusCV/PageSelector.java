package claudiosoft.opusCV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 *
 * @author Claudio
 */
public class PageSelector {

    private Options options;
    private BasicConsoleLogger logger;

    public PageSelector(Options options, BasicConsoleLogger logger) {
        this.options = options;
        this.logger = logger;
    }

    public void doProcess() throws IOException {

        logger.info("start frame analysis...");

        // reload serie file
        File sumsSerieFile = new File(options.getOutFolder().getAbsolutePath() + File.separator + Constants.SUM_SERIE_FILENAME);
        if (!sumsSerieFile.exists()) {
            throw new FileNotFoundException("Sum serie file not found!");
        }

        double maxSerie = 0.0;
        List<Double> sumsSerie = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(sumsSerieFile.toPath());
            for (String sumDiff : lines) {
                double curVal = Double.parseDouble(sumDiff.replace(",", "."));
                sumsSerie.add(curVal);
                if (curVal > maxSerie) {
                    maxSerie = curVal;
                }
            }
        } catch (NullPointerException | NumberFormatException ex) {
            throw new IOException("Sum serie file is corrupted", ex);
        }
        logger.debug("maximum of serie: " + maxSerie);

        // serie analysis
        boolean useMin = false;

        // calcolo minimo valori serie = m
        double minSerie = Double.MAX_VALUE;
        for (double val : sumsSerie) {
            if (val < minSerie) {
                minSerie = val;
            }
        }
        logger.debug("minimum of serie: " + minSerie);

        // sezione in aree di d minima sotto un sigma = 3*m
        double sigma = 0.0;
        if (useMin) {
            sigma = 3 * minSerie;
        } else {
            sigma = maxSerie / 8.0;
        }
        final int minSerieSize = 3;

        ArrayList<AreaSerie> locMinArea = new ArrayList<>();
        AreaSerie curArea = null;

        for (int i = 0; i < sumsSerie.size(); i++) {
            double val = sumsSerie.get(i);
            if (val < sigma) {
                if (curArea == null) {
                    curArea = new AreaSerie(i);
                }
                curArea.getAreaValues().add(val);
            } else if (curArea != null) {
                if (curArea.getAreaValues().size() > minSerieSize) {
                    locMinArea.add(curArea);
                }
                curArea = null;
            }
        }

        if (curArea != null) {
            if (curArea.getAreaValues().size() > minSerieSize) {
                locMinArea.add(curArea);
            }
            curArea = null;
        }
        logger.debug("number of areas (candidate pages): " + locMinArea.size());

        logger.info("start page selection...");

        final int histHeight = 480;
        Mat serieHisto = new Mat(histHeight, sumsSerie.size(), CvType.CV_8UC3);

        serieHisto.setTo(new Scalar(255, 255, 255));
        for (int i = 0; i < sumsSerie.size(); i++) {
            double normalized = histHeight - histHeight * (sumsSerie.get(i) / maxSerie);
            Point pt1 = new Point(i, histHeight);
            Point pt2 = new Point(i, normalized);
            Imgproc.line(serieHisto, pt1, pt2, new Scalar(0, 0, 0), 1);
        }

        VideoCapture cap = new VideoCapture(options.getFile().getAbsolutePath());

        if (!cap.isOpened()) {
            throw new IOException("Cannot open the video file");
        }

        Mat frame = new Mat();
        Mat rotatedFrame = new Mat(frame.size(), frame.type());

        int pageIndex = 1;
        int frameProgess = options.getStartFrame();

        try {
            // per ogni area si prende il minimo e da quello si ricava il relativo frame
            for (AreaSerie area : locMinArea) {
                int bestFrameIndex = area.getLocMinAreaIndex();

                int serieIndex = area.getBaseSerieIndex() + bestFrameIndex + 1;
                double normalized = histHeight - histHeight * (sumsSerie.get(serieIndex) / maxSerie);
                Point pt1 = new Point(serieIndex, histHeight);
                Point pt2 = new Point(serieIndex, normalized);
                Imgproc.line(serieHisto, pt1, pt2, new Scalar(0, 0, 255), 1);

                int videoIndex = frameProgess + (options.getStepFrame() * area.getBaseSerieIndex()) + bestFrameIndex;
                cap.set(Videoio.CAP_PROP_POS_FRAMES, videoIndex);
                if (!cap.read(frame)) {
                    throw new IOException("Invalid frame index to extract");
                }

                if (options.getFrameRotation() >= 0) {
                    Core.rotate(frame, rotatedFrame, options.getFrameRotation());
                } else {
                    rotatedFrame = frame;
                }
                Imgcodecs.imwrite(options.getOutFolder().getAbsolutePath() + File.separator + "page_" + pageIndex + "-" + videoIndex + ".jpg", rotatedFrame);
                pageIndex++;

                Mat resized = new Mat();
                Imgproc.resize(rotatedFrame, resized, new Size(rotatedFrame.width() * 0.5, rotatedFrame.height() * 0.5));
                HighGui.imshow("Serie", serieHisto);
                HighGui.imshow("Page", resized);
                HighGui.waitKey(0);

                Imgproc.line(serieHisto, pt1, pt2, new Scalar(0, 0, 0), 1);
                frameProgess += (options.getStepFrame() * area.getAreaValues().size());
            }
        } finally {
            cap.release();
            logger.info(pageIndex + " pages selected");
        }
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public BasicConsoleLogger getLogger() {
        return logger;
    }

    public void setLogger(BasicConsoleLogger logger) {
        this.logger = logger;
    }

}
