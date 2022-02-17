/**
 *
 */
package test.opusCV.json;

import com.github.cliftonlabs.json_simple.JsonException;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsonSimpleTests extends BaseJUnitTest {

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
        List<Integer> array = new ArrayList();
        array.add(1);
        array.add(2);
        array.add(3);
        JsonSimpleBean bean = new JsonSimpleBean(null, "test", 0, 0.1, array);

        String json = Jsoner.serialize(bean);

        // pretty print
        json = Jsoner.prettyPrint(json);

        System.out.println(json);

        Object testBean = Jsoner.deserialize(json);
        if (testBean instanceof JsonSimpleBean) {
            Assert.assertTrue(true);
        }
    }

}
