package claudiosoft.opusCV.step.dummy;

import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.StepType;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class DummyStep extends BaseStep {

    protected String name;
    protected int counter;
    protected double precision;
    protected List<Integer> listInt;

    public DummyStep() throws IOException {
        this(null); // preset values
    }

    public DummyStep(JsonObject jsonIn) throws IOException {
        super(jsonIn);
        this.type = StepType.TEST;
        this.name = "jsonTestStep";
        this.counter = 0;
        this.precision = 0.0;
        this.listInt = new ArrayList<>();

        if (jsonIn != null) {
            this.name = jsonIn.getString(Keys.TEST_NAME);
            this.counter = jsonIn.getInteger(Keys.TEST_COUNT);
            this.precision = jsonIn.getDouble(Keys.TEST_PRECISION);
            this.listInt = jsonIn.getCollection(Keys.TEST_LIST_INT);
        }
        jsonOut.put(Keys.TYPE, this.type.name());
        jsonOut.put(Keys.ENGINE, this.engine.name());
        jsonOut.put(Keys.INDEX, this.index);
        jsonOut.put(Keys.TEST_NAME, this.name);
        jsonOut.put(Keys.TEST_COUNT, this.counter);
        jsonOut.put(Keys.TEST_PRECISION, this.precision);
        jsonOut.put(Keys.TEST_LIST_INT, this.listInt);
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
    public void checkPrerequisites() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prepare() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doProcess() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalize() throws OpusCVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
