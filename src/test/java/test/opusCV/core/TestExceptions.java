/**
 *
 */
package test.opusCV.core;

import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.opusCV.BaseJUnitTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExceptions extends BaseJUnitTest {

    @BeforeClass
    public static void setUpClass() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    @Test
    public void tOpusCVExceptions() {
        try {
            Configuration.getInstance();
            Assert.fail();
        } catch (OpusCVException ex) {
            Assert.assertTrue(ex.getCode() == ErrorCode.INIT_ERROR);
        }
    }

}
