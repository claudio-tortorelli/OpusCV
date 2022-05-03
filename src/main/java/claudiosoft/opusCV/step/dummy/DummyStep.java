package claudiosoft.opusCV.step.dummy;

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

    public DummyStep() throws IOException {
        super();
        this.category = StepCategory.DUMMY;
        this.objTypeName = ObjectTypeName.DUMMY_STEP.get();
        this.counter = 0;
        this.precision = 0.0;
        this.listInt = new ArrayList();
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
