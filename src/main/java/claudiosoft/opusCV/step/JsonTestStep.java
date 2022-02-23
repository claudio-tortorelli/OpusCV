package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.StepType;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class JsonTestStep extends BaseStep {

    protected String name;
    protected int counter;
    protected double precision;
    protected List<Integer> listInt;

    public JsonTestStep() {
        this("jsonTestStep", 0, 0.0, new ArrayList<Integer>());
    }

    public JsonTestStep(String name, int count, double precision, List<Integer> listInt) {
        super();
        this.type = StepType.TEST;
        this.name = name;
        this.counter = count;
        this.precision = precision;
        this.listInt = listInt;
    }

    public JsonTestStep(JsonObject json) throws IOException {
        super(json);
        name = json.getString(Keys.TEST_NAME);
        counter = json.getInteger(Keys.TEST_COUNT);
        precision = json.getDouble(Keys.TEST_PRECISION);
        listInt = json.getCollection(Keys.TEST_LIST_INT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return counter;
    }

    public void setCount(int count) {
        this.counter = count;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public List<Integer> getListInt() {
        return listInt;
    }

    public void setListInt(List<Integer> listInt) {
        this.listInt = listInt;
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        JsonObject json = new JsonObject();
        super.toJson(json);
        json.put(Keys.TEST_NAME, this.getName());
        json.put(Keys.TEST_COUNT, this.getCount());
        json.put(Keys.TEST_PRECISION, this.getPrecision());
        json.put(Keys.TEST_LIST_INT, this.getListInt());
        json.toJson(writer);
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doProcess() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
