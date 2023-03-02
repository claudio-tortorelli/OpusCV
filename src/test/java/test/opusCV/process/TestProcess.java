/**
 *
 */
package test.opusCV.process;

import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Utils;
import claudiosoft.opusCV.process.Process;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.opusCV.BaseJUnitTest;
import test.opusCV.TestResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProcess extends BaseJUnitTest {

    @BeforeClass
    public static void setUpClass() {
        try {
            Configuration.initialize(TestResource.extractToFile("sampleConf/opuscvTest.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    @Test
    @Ignore
    public void tSingleResizeImage() throws IOException, InterruptedException, OpusCVException, URISyntaxException {
        File processFile = TestResource.extractToFile("sampleProcess/singleStep_resizeImage.json");
        Process proc = new Process(processFile, Utils.getJarFolder() + File.separatorChar + Configuration.get().getProcessFolder());
    }

}
