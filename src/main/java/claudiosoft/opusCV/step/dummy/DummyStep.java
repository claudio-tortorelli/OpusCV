package claudiosoft.opusCV.step.dummy;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.StepType;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class DummyStep extends BaseStep {

    protected final String name;
    protected final int counter;
    protected final double precision;
    protected final List<Integer> listInt;

    public DummyStep() {
        this("jsonTestStep", 0, 0.0, new ArrayList<Integer>());
    }

    public DummyStep(String name, int count, double precision, List<Integer> listInt) {
        super();
        this.type = StepType.TEST;
        this.name = name;
        this.counter = count;
        this.precision = precision;
        this.listInt = listInt;
    }

    public DummyStep(JsonObject json) throws IOException {
        super(json);
        name = json.getString(Keys.TEST_NAME);
        counter = json.getInteger(Keys.TEST_COUNT);
        precision = json.getDouble(Keys.TEST_PRECISION);
        listInt = json.getCollection(Keys.TEST_LIST_INT);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return counter;
    }

    public double getPrecision() {
        return precision;
    }

    public List<Integer> getListInt() {
        return listInt;
    }

    @Override
    public String toJson() throws OpusCVException {
        final StringWriter writer = new StringWriter();
        final JsonObject json = new JsonObject();
        super.toJson(json);
        json.put(Keys.TEST_NAME, this.getName());
        json.put(Keys.TEST_COUNT, this.getCount());
        json.put(Keys.TEST_PRECISION, this.getPrecision());
        json.put(Keys.TEST_LIST_INT, this.getListInt());
        try {
            json.toJson(writer);
        } catch (final IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new OpusCVException(ErrorCode.JSON_WRITE);
        }
        return writer.toString();
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        // fake process
    }

    @Override
    public void doProcess() throws OpusCVException {
        // fake process
    }

}
