package test.opusCV.json;

import org.junit.BeforeClass;

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
//    @Test
//    public void tMacroStep() throws OpusCVException, IOException {
//        List<BaseStep> steps = new LinkedList<>();
//        steps.add(new DummyStep());
//        steps.add(new DummyStep());
//        //TODO qui gestire come altri oggetti con json string
//        MacroStep stepMacro = new MacroStep(steps);
//        String jsonMacro = JsonUtils.objToJson(stepMacro);
//        System.out.println(jsonMacro);
//
//        MacroStep stepMacro2 = (MacroStep) JsonUtils.objFromJson(jsonMacro);
//
//        String firstJson = JsonUtils.objToJson(stepMacro);
//        String secondJson = JsonUtils.objToJson(stepMacro2);
//        Assert.assertTrue(firstJson.equals(secondJson));
//    }
}
