package claudiosoft.opusCV.step.dummy;

import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.StepKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class DummyStep extends BaseStep {

    protected int counter;
    protected double precision;
    protected List<Integer> listInt;

    public DummyStep() throws IOException {
        this(null); // preset values
    }

    public DummyStep(JsonObject jsonIn) throws IOException {
        super(jsonIn);
        this.counter = (int) StepKey.getValueFromJson(json, StepKey.DUMMY_COUNT);
        this.precision = (double) StepKey.getValueFromJson(json, StepKey.DUMMY_PRECISION);
        this.listInt = (List<Integer>) StepKey.getValueFromJson(json, StepKey.DUMMY_LIST_INT);
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
