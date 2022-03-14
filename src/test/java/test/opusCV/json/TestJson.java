/**
 *
 */
package test.opusCV.json;

import claudiosoft.opusCV.step.StepFactory;
import claudiosoft.opusCV.step.StepType;
import claudiosoft.opusCV.step.dummy.DummyStep;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.opusCV.BaseJUnitTest;

/**
 * https://cliftonlabs.github.io/json-simple/
 *
 * @author Claudio
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJson extends BaseJUnitTest {

    @BeforeClass
    public static void setUpClass() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    @Test
    public void tSerializeDeserizalize() throws IOException, InterruptedException, JsonException {
        DummyStep toBeSerialized = new DummyStep();
        String json = Jsoner.serialize(toBeSerialized);
        json = Jsoner.prettyPrint(json);
        System.out.println(json);

        JsonObject testBean = (JsonObject) Jsoner.deserialize(json);
        DummyStep deserialized = (DummyStep) StepFactory.get(testBean);
        Assert.assertTrue(deserialized != null);
        Assert.assertTrue(deserialized.getType() == StepType.TEST);
    }

}
