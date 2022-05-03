package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class MacroStep extends BaseStep {

    protected List<BaseStep> subSteps;

    public MacroStep() {
        this(new LinkedList<>());
    }

    public MacroStep(List<BaseStep> subSteps) {
        super();
        this.category = StepCategory.MACRO;
        this.objTypeName = ObjectTypeName.MACRO_STEP.get();
        this.subSteps = subSteps;
        this.engine = EngineType.NONE;
    }

    @Override
    public void execute() throws OpusCVException {
        super.execute();
    }

    @Override
    public void checkPrerequisites() throws OpusCVException {
        for (BaseStep step : subSteps) {
            step.checkPrerequisites();
        }
    }

    @Override
    public void prepare() throws OpusCVException {
        for (BaseStep step : subSteps) {
            step.prepare();
        }
    }

    @Override
    public void doProcess() throws OpusCVException {
        for (BaseStep step : subSteps) {
            step.doProcess();
        }
    }

    @Override
    public void finalize() throws OpusCVException {
        for (BaseStep step : subSteps) {
            step.finalize();
        }
    }

    public List<BaseStep> getSubSteps() {
        return subSteps;
    }
}
