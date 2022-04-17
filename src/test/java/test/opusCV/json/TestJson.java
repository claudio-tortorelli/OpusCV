/**
 *
 */
package test.opusCV.json;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.StepFactory;
import claudiosoft.opusCV.step.StepKey;
import claudiosoft.opusCV.step.StepType;
import claudiosoft.opusCV.step.dummy.DummyStep;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public void tJsonObjectOutputInput() throws JsonException {

        System.out.println("write object to json");

        // this should be inside any json object data
        JsonObject objOut = new JsonObject();
        objOut.put(StepKey.DUMMY_COUNT, 1);
        objOut.put(StepKey.NAME, "testObj");
        List<Integer> list = new ArrayList<>();
        list.add(10);
        objOut.put(StepKey.DUMMY_LIST_INT, list);

        // object to json
        String toJson = Jsoner.prettyPrint(objOut.toJson());
        System.out.println(toJson);

        // this is from json
        JsonObject objIn = (JsonObject) Jsoner.deserialize(toJson);
        System.out.println("json readed to object");
    }

    @Test
    public void tSerializeDeserizalizeJsonData() throws OpusCVException, JsonException, IOException {
        JsonObject jsonIn = new JsonObject();
        jsonIn.put(StepKey.TYPE, StepType.DUMMY);
        jsonIn.put(StepKey.INDEX, 0);
        jsonIn.put(StepKey.ENGINE, EngineType.OPENCV);
        jsonIn.put(StepKey.NAME, "Dummy");
        jsonIn.put(StepKey.DUMMY_COUNT, 0);
        jsonIn.put(StepKey.DUMMY_LIST_INT, new ArrayList());
        jsonIn.put(StepKey.DUMMY_PRECISION, 0.0);

        DummyStep toBeSerialized = (DummyStep) StepFactory.get(jsonIn);
        String json = JsonData.toString(toBeSerialized.getJson());
        System.out.println(Jsoner.prettyPrint(json));

        DummyStep toBeDeserialized = new DummyStep();
        JsonObject testBean = (JsonObject) toBeDeserialized.fromString(json);
        DummyStep deserialized = (DummyStep) StepFactory.get(testBean);
        Assert.assertTrue(deserialized != null);
        Assert.assertTrue(deserialized.getType() == StepType.DUMMY);
    }

    @Test
    public void tReadProcess() {

    }
}
