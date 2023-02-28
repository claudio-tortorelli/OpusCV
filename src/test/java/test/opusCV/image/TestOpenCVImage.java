/**
 *
 */
package test.opusCV.image;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Utils;
import claudiosoft.opusCV.image.ImageProvider;
import claudiosoft.opusCV.image.OpenCVImage;
import claudiosoft.opusCV.step.core.OpenCVNative;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.opusCV.BaseJUnitTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOpenCVImage extends BaseJUnitTest {

    @BeforeClass
    public static void setUpClass() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    @Test
    public void tLoadImage() throws IOException, InterruptedException, OpusCVException {
        File sampleImage = Utils.getFileFromRes("sampleImage/image-test.jpg");
        Assume.assumeTrue("sample image present", sampleImage != null);
        Assume.assumeTrue("opencv loaded", OpenCVNative.isLoaded());

        OpenCVImage image = (OpenCVImage) ImageProvider.get().image(sampleImage.getAbsolutePath(), CVProvider.OPENCV);
        Assert.assertTrue(image != null);
        Assert.assertTrue(image.getRaw().width() == 1024.0 && image.getRaw().height() == 640.0);
    }

}
