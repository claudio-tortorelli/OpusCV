package claudiosoft.opusCV.videoprocessor;

/**
 * Temporary class to test te frame extraction opencv post process
 *
 * @author Claudio
 */
public class FrameExtractor {

//    private File frameFolder;
//    private String frameName;
//    private BasicConsoleLogger logger;
//    private final String outFolder;
//
//    private final boolean debugBox = true;
//
//    public FrameExtractor(Options options, BasicConsoleLogger logger) throws Exception {
//        this.frameFolder = options.getFrameFolder();
//        this.frameName = options.getFrameBaseName();
//        this.logger = logger;
//        if (this.frameFolder == null) {
//            throw new Exception("Invalid frame folder");
//        }
//        if (this.frameName == null || this.frameName.isEmpty()) {
//            this.frameName = "frame";
//        }
//
//        outFolder = new File(this.frameFolder + File.separator + "out").getAbsolutePath();
//        Files.createDirectories(new File(outFolder).toPath());
//        final File[] files = new File(outFolder).listFiles();
//        for (File f : files) {
//            f.delete();
//        }
//    }
//
//    public void doProcess() {
//        File[] files = frameFolder.listFiles(new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.toLowerCase().endsWith(".jpg");
//            }
//        });
//
//        if (files == null) {
//            logger.info("no frame found");
//            return;
//        }
//
//        HighGui.namedWindow("FrameExtractor - Pictures", 0);
//        for (File frame : files) {
//
//            String curFrameName = frame.getName().toLowerCase();
//            logger.info("processing " + curFrameName);
//
//            Mat rgbSrc = Imgcodecs.imread(frame.getAbsolutePath(), Imgcodecs.IMREAD_COLOR);
//
//            final double maxArea = (rgbSrc.width() * rgbSrc.height());
//            final double minArea = (rgbSrc.width() * rgbSrc.height()) / 24.0;
//            final int threshold = 100;
//
//            List<MatOfPoint> contours = findFrameContours(rgbSrc, curFrameName, threshold);
//
//            List<Rect> tempRect = new LinkedList<>();
//            for (MatOfPoint contour : contours) {
//                double area = Imgproc.contourArea(contour);
//                if (area < minArea || area > maxArea) {
//                    continue;
//                }
//                double epsilon = 0.1 * Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true);
//                MatOfPoint2f approx = new MatOfPoint2f();
//                Imgproc.approxPolyDP(new MatOfPoint2f(contour.toArray()), approx, epsilon, true);
//
//                MatOfPoint points = new MatOfPoint(approx.toArray());
//                // Get bounding rect of contour
//                tempRect.add(Imgproc.boundingRect(points));
//            }
//
//            if (debugBox) {
//                String outPath = outFolder + File.separator + curFrameName.replace(".jpg", "") + "_0box1" + ".jpg";
//                drawBox(tempRect, rgbSrc, outPath, new Scalar(100, 220, 100));
//            }
//
//            final double proportionRatio = 16 / (double) 9;
//
//            List<Rect> finalImagesRect = new LinkedList<>();
//            for (Rect curRect : tempRect) {
//                //check proportions
//                double ratio;
//                if (curRect.width > curRect.height) {
//                    ratio = curRect.width / (double) curRect.height;
//                } else {
//                    ratio = curRect.height / (double) curRect.width;
//                }
//                if (ratio > proportionRatio) {
//                    continue;
//                }
//                finalImagesRect.add(curRect);
//            }
//            if (debugBox) {
//                String outPath = outFolder + File.separator + curFrameName.replace(".jpg", "") + "_0box1" + ".jpg";
//                drawBox(finalImagesRect, rgbSrc, outPath, new Scalar(100, 220, 100));
//            }
//
//            // sort from bigger to smaller
//            Map<Double, Rect> unsortMap = new HashMap<Double, Rect>();
//            for (Rect rect : finalImagesRect) {
//                unsortMap.put(rect.area(), rect);
//            }
//            Map<Double, Rect> sortMap = new TreeMap<Double, Rect>(
//                    new Comparator<Double>() {
//                @Override
//                public int compare(Double o1, Double o2) {
//                    return o1.compareTo(o2); // from smaller to bigger
////                    return o2.compareTo(o1); // from bigger to smaller
//                }
//            });
//            sortMap.putAll(unsortMap);
//
//            finalImagesRect = new LinkedList<>();
//            for (Map.Entry<Double, Rect> entry : sortMap.entrySet()) {
//                finalImagesRect.add(entry.getValue());
//            }
//
//            //exclude too similar
//            final double toleranceAreaRatio = 0.8;
//            final int toleranceBounds = 30;
//
//            tempRect = finalImagesRect;
//            finalImagesRect = new LinkedList<>();
//            for (int iRect = 0; iRect < tempRect.size(); iRect++) {
//                Rect curRect = tempRect.get(iRect);
//                boolean similar = false;
//                for (int jRect = iRect + 1; jRect < tempRect.size(); jRect++) {
//                    Rect otherRect = tempRect.get(jRect);
//                    if (areRectSimilar(curRect, otherRect, toleranceBounds, toleranceAreaRatio)) {
//                        similar = true;
//                        break;
//                    }
//                }
//                if (!similar) {
//                    finalImagesRect.add(curRect);
//                }
//            }
//            if (debugBox) {
//                String outPath = outFolder + File.separator + curFrameName.replace(".jpg", "") + "_0box1" + ".jpg";
//                drawBox(finalImagesRect, rgbSrc, outPath, new Scalar(100, 220, 100));
//            }
//
//            if (finalImagesRect.isEmpty()) {
//                logger.info("no images recognized in " + curFrameName);
//                continue;
//            }
//
//            Mat roiSrc = rgbSrc;
//
//            // crop page
//            final boolean cropPage = false;
//            if (cropPage) {
//                int minX = rgbSrc.width(), minY = rgbSrc.height(), maxX = 0, maxY = 0;
//                for (Rect imgRect : finalImagesRect) {
//                    if (imgRect.tl().x < minX) {
//                        minX = (int) imgRect.tl().x;
//                    }
//                    if (imgRect.tl().y < minY) {
//                        minY = (int) imgRect.tl().y;
//                    }
//                    if (imgRect.br().x > maxX) {
//                        maxX = (int) imgRect.br().x;
//                    }
//                    if (imgRect.br().y > maxY) {
//                        maxY = (int) imgRect.br().y;
//                    }
//                }
//
//                int padding = 0;
//                Rect pageRect = new Rect(new Point(minX - padding, minY - padding), new Point(maxX + padding, maxY + padding));
//                pageRect.x = Math.max(0, pageRect.x);
//                pageRect.y = Math.max(0, pageRect.y);
//                pageRect.width = Math.min(rgbSrc.width(), pageRect.width - 1);
//                pageRect.height = Math.min(rgbSrc.height(), pageRect.height - 1);
//
//                roiSrc = new Mat(rgbSrc, pageRect);
//            }
//            String outPath = outFolder + File.separator + curFrameName;
//
//            Imgcodecs.imwrite(outPath, roiSrc);
//
//            // cut internal images
//            int images = 1;
//            for (Rect imgRect : finalImagesRect) {
//                Mat roiImg = new Mat(rgbSrc, imgRect);
//                outPath = outFolder + File.separator + curFrameName.replace(".jpg", "") + "_" + images++ + ".jpg";
//                Imgcodecs.imwrite(outPath, roiImg);
//                HighGui.imshow("FrameExtractor - Pictures", roiImg);
//                HighGui.waitKey(30);
//            }
//
//            logger.info(finalImagesRect.size() + " images taken in " + curFrameName);
//        }
//
//        /**
//         * doppioni, find match template dimensione minima, controlla volti
//         * all'interno test inversione threshold per vedere se ne trova altri
//         * test di inclusione tra rect
//         */
//    }
//
//    private List<MatOfPoint> findFrameContours(Mat rgbSrc, String curFrameName, int threshVal) {
//
//        Mat graySrc = new Mat(rgbSrc.size(), CvType.CV_8U);
//        Imgproc.cvtColor(rgbSrc, graySrc, Imgproc.COLOR_BGR2GRAY);
//
//        Mat dstThreshold = new Mat(graySrc.width(), graySrc.height(), graySrc.type());
//        Imgproc.threshold(graySrc, dstThreshold, threshVal, 255, THRESH_BINARY);
//        //            HighGui.imshow("FrameExtractor - Pictures", dstThreshold);
//        //            HighGui.waitKey(0);
//        //            outPath = outFolder + File.separator + curFrameName.replace(".jpg", "") + "_0threshold" + ".jpg";
//        //            Imgcodecs.imwrite(outPath, dstThreshold);
//
//        Mat dstAdapt = new Mat(graySrc.width(), graySrc.height(), graySrc.type());
//
////        Imgproc.adaptiveThreshold(dstThreshold, dstAdapt, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 11, 8);
//        Imgproc.adaptiveThreshold(dstThreshold, dstAdapt, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 15, 4);
//        //            HighGui.imshow("FrameExtractor - Pictures", dstAdapt);
//        //            HighGui.waitKey(0);
//        //            outPath = outFolder + File.separator + curFrameName.replace(".jpg", "") + "_adaptive" + ".jpg";
//        //            Imgcodecs.imwrite(outPath, dstAdapt);
//
//        List<MatOfPoint> contours = new ArrayList<>();
//        Mat contImage = new Mat();
//        Imgproc.findContours(dstAdapt, contours, contImage, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
//        logger.info(contours.size() + " contours found on " + curFrameName);
//        return contours;
//    }
//
//    private boolean areRectSimilar(Rect r1, Rect r2, int toleranceBounds, double toleranceAreaRatio) {
//        double dist = Math.sqrt(Math.pow(r1.tl().x - r2.tl().x, 2.0) + Math.pow(r1.tl().y - r2.tl().y, 2.0));
//        if (dist < toleranceBounds) {
//            return true;
//        }
//        dist = Math.sqrt(Math.pow(r1.br().x - r2.br().x, 2.0) + Math.pow(r1.br().y - r2.br().y, 2.0));
//        if (dist < toleranceBounds) {
//            return true;
//        }
//
//        if (r1.contains(r2.tl()) && r1.contains(r2.br()) || r2.contains(r1.tl()) && r2.contains(r1.br())) {
//            // one rect contain the other
//            double ratioArea;
//            if (r1.area() > r2.area()) {
//                ratioArea = r2.area() / r1.area();
//            } else {
//                ratioArea = r1.area() / r2.area();
//            }
//            if (ratioArea > toleranceAreaRatio) {
//                return true;
//            }
////            return true;
//        }
//        return false;
//    }
//
//    private void drawBox(List<Rect> rects, Mat image, String path, Scalar color) {
//        //Random rng = new Random(12345);
//        Mat boundRgb = image.clone();
//        for (Rect imgRect : rects) {
////            Scalar color = new Scalar(10 + rng.nextInt(150), 100 + rng.nextInt(150), 100 + rng.nextInt(150));
//            Imgproc.rectangle(boundRgb, imgRect.tl(), imgRect.br(), color, 2);
//        }
//        Imgcodecs.imwrite(path, boundRgb);
//    }
//
//    private double matchImages(Mat img, Mat img2) {
//
//        int minCol = Math.min(img.cols(), img2.cols());
//        int minRow = Math.min(img.rows(), img2.rows());
//
//        Point tl = new Point((img.cols() - minCol) / 2, (img.rows() - minRow) / 2);
//        Point br = new Point(img.cols() - (img.cols() - minCol) / 2, img.rows() - (img.rows() - minRow) / 2);
//        Rect roiRect = new Rect(tl, br);
//        Mat roi = new Mat(img, roiRect);
//
//        Imgcodecs.imwrite("c:\\canc\\roi.jpg", roi);
//
//        Point tl2 = new Point((img2.cols() - minCol) / 2, (img2.rows() - minRow) / 2);
//        Point br2 = new Point(img2.cols() - (img2.cols() - minCol) / 2, img2.rows() - (img2.rows() - minRow) / 2);
//        Rect roiRect2 = new Rect(tl2, br2);
//        Mat roi2 = new Mat(img2, roiRect2);
//
//        Imgcodecs.imwrite("c:\\canc\\roi2.jpg", roi2);
//
//        Mat result = new Mat();
//        result.create(1, 1, CvType.CV_32FC1);
//        Imgproc.matchTemplate(roi, roi2, result, Imgproc.TM_CCOEFF);
//        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, CvType.CV_32FC1);
//        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
//        return mmr.maxVal;
//    }
}
