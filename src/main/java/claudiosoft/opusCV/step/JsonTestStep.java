package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.StepType;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class JsonTestStep extends BaseStep {

    private String name;
    private int count;
    private double precision;
    private List<Integer> listInt;

    public JsonTestStep() {
        this(null, 0, 0.0, null);
    }

    public JsonTestStep(String name, int count, double precision, List<Integer> listInt) {
        super();
        this.type = StepType.TEST;
        this.stepCount = 0;
        this.name = name;
        this.count = count;
        this.precision = precision;
        this.listInt = listInt;
    }

    public JsonTestStep(JsonObject json) throws IOException {
        super(json);
        fromJson(json);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        json.put("name", this.getName());
        json.put("count", this.getCount());
        json.put("precision", this.getPrecision());
        json.put("list_int", this.getListInt());
        json.toJson(writer);
    }

    @Override
    protected void fromJson(JsonObject json) throws IOException {
        super.fromJson(json);
        name = json.getString(Keys.NAME);
        count = json.getInteger(Keys.COUNT);
        precision = json.getDouble(Keys.PRECISION);
        listInt = json.getCollection(Keys.LIST_INT);
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
