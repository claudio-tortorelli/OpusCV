package test.opusCV.json;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.StepType;
import claudiosoft.opusCV.step.GenericStep;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class JsonSimpleBean extends GenericStep {

    private String name;
    private int count;
    private double precision;
    private List<Integer> listInt;

    public JsonSimpleBean() {
        this(null);
    }

    public JsonSimpleBean(BasicConsoleLogger logger) {
        this(logger, null, 0, 0.0, null);
    }

    public JsonSimpleBean(BasicConsoleLogger logger, String name, int count, double precision, List<Integer> listInt) {
        super(logger);
        this.type = StepType.TEST;
        this.stepCount = 0;
        this.name = name;
        this.count = count;
        this.precision = precision;
        this.listInt = listInt;
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
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        super.toJson(writer);
        final JsonObject json = new JsonObject();
        json.put("name", this.getName());
        json.put("count", this.getCount());
        json.put("precision", this.getPrecision());
        json.put("list_int", this.getListInt());
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
