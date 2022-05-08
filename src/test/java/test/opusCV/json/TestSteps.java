package test.opusCV.json;

import claudiosoft.opusCV.common.JsonUtils;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.MacroStep;
import claudiosoft.opusCV.step.dummy.DummyStep;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Claudio
 */
public class TestSteps {

    @BeforeClass
    public static void setUpClass() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Setup Error");
        }
    }

    /**
     * Complex recursive serializalization and deserialization of object step
     *
     * @throws OpusCVException
     * @throws IOException
     */
    @Test
    public void tMacroStep() throws OpusCVException, IOException {
        List<BaseStep> steps = new LinkedList<>();
        steps.add(new DummyStep());
        steps.add(new DummyStep());

        MacroStep stepMacro = new MacroStep(steps);
        String jsonMacro = JsonUtils.objToJson(stepMacro);
        System.out.println(jsonMacro);

        MacroStep stepMacro2 = (MacroStep) JsonUtils.objFromJson(jsonMacro);

        String firstJson = JsonUtils.objToJson(stepMacro);
        String secondJson = JsonUtils.objToJson(stepMacro2);
        Assert.assertTrue(firstJson.equals(secondJson));
    }
}
