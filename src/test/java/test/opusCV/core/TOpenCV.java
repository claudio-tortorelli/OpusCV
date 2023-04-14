/**
 *
 */
package test.opusCV.core;

import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.step.core.OpenCVNative;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import test.opusCV.BaseJUnitTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TOpenCV extends BaseJUnitTest {

    public TOpenCV() {
        super(false, false);
    }

    @BeforeClass
    public static void setUpClass() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    @Test
    public void tCheckOpenCVAvailable() throws IOException, InterruptedException, OpusCVException {
        Assume.assumeTrue("opencv loaded", OpenCVNative.isLoaded());
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        Assert.assertTrue(mat != null);
        BasicConsoleLogger.get().info("opencv test: mat = " + mat.dump());
    }

}
