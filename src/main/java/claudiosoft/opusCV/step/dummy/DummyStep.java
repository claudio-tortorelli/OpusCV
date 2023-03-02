package claudiosoft.opusCV.step.dummy;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.StepCategory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class DummyStep extends BaseStep {

    protected int counter;
    protected double precision;
    protected List<Integer> listInt;

    public DummyStep() throws IOException, OpusCVException {
        super(ObjectTypeName.DUMMY_STEP, StepCategory.DUMMY, CVProvider.NONE);
        this.counter = 0;
        this.precision = 0.0;
        this.listInt = new ArrayList();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
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
