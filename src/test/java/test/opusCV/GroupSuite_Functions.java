package test.opusCV;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import test.opusCV.core.TConfiguration;
import test.opusCV.core.TOpenCV;
import test.opusCV.image.TOpenCVImage;
import test.opusCV.json.TJson;
import test.opusCV.json.TSteps;
import test.opusCV.process.TProcess;

/**
 * Sorted Test suite
 *
 * @author Claudio
 */
@SuiteClasses({
    TConfiguration.class,
    TOpenCV.class,
    TOpenCVImage.class,
    TJson.class,
    TSteps.class,
    TProcess.class
}
)
@RunWith(Suite.class)
public class GroupSuite_Functions {

}
