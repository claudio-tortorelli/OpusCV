/**
 *
 */
package test.opusCV.json;

import claudiosoft.opusCV.common.JsonUtils;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.dummy.DummyObject;
import claudiosoft.opusCV.step.dummy.DummyStep;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.opusCV.BaseJUnitTest;

/**
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
    public void tToGson() throws OpusCVException, IOException {
        DummyObject book = new DummyObject("Thinking in Java", "978-0131872486", 1998, new String[]{"Bruce Eckel"});
        // convert book object to JSON
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(book);
        Assert.assertTrue(json != null);
        System.out.println(json);
    }

    @Test
    public void tToJsonUtils() throws OpusCVException, IOException {
        DummyObject book = new DummyObject("Thinking in Java", "978-0131872486", 1998, new String[]{"Bruce Eckel"});
        // convert book object to JSON
        String json = JsonUtils.objToJson(book);
        Assert.assertTrue(json != null);
        System.out.println(json);
    }

    @Test
    public void tToJsonUtils2() throws OpusCVException, IOException {
        DummyStep dummy = new DummyStep();
        String json = JsonUtils.objToJson(dummy);
        Assert.assertTrue(json != null);
        System.out.println(json);
    }

    @Test
    public void tFromJsonUtils() throws OpusCVException, IOException {

        String json = "{\n"
                + "  \"objTypeName\": \"DummyObject\",\n"
                + "  \"title\": \"Thinking in Java\",\n"
                + "  \"isbn\": \"978-0131872486\",\n"
                + "  \"year\": 1998,\n"
                + "  \"authors\": [\n"
                + "    \"Bruce Eckel\"\n"
                + "  ]\n"
                + "}";

        DummyObject book = (DummyObject) JsonUtils.objFromJson(json);
        Assert.assertTrue(book != null);
    }

    @Test
    public void tFromJsonUtils2() throws OpusCVException, IOException {

        String json = "{\n"
                + "  \"objTypeName\": \"DummyStep\",\n"
                + "  \"counter\": 0,\n"
                + "  \"precision\": 0.0,\n"
                + "  \"listInt\": [],\n"
                + "  \"type\": \"BASE\",\n"
                + "  \"index\": 0,\n"
                + "  \"provider\": \"OPENCV\",\n"
                + "  \"name\": \"\"\n"
                + "}";

        DummyStep dummy = (DummyStep) JsonUtils.objFromJson(json);
        Assert.assertTrue(dummy != null);
    }
}
