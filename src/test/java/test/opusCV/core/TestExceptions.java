/**
 *
 */
package test.opusCV.core;

import java.io.IOException;
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
    public void tOpusCVExceptions() throws IOException, InterruptedException {
        //TODO
    }

}
