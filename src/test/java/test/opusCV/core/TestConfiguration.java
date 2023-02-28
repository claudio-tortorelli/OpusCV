/**
 *
 */
package test.opusCV.core;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.OpusCVException;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.opusCV.BaseJUnitTest;
import test.opusCV.TestResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConfiguration extends BaseJUnitTest {

    @BeforeClass
    public static void setUpClass() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    @Test
    public void tConfDefaultInit() throws IOException, OpusCVException {
        Configuration.initialize();
        Assert.assertTrue(Configuration.get().getDefaultCVProvider() == CVProvider.OPENCV);
    }

    @Test
    public void tConfDoubleInit() throws IOException {
        Configuration.initialize();
        Configuration.initialize();
    }

    @Test
    public void tConfFileInit() throws IOException, OpusCVException {
        Configuration.initialize(TestResource.extractToFile("sampleConf/opuscv.properties"));
        Assert.assertTrue(Configuration.get().getVersion() == 2);
    }

}
