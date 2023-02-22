package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.CVProvider;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class MacroStep extends BaseStep {

    public static final String OBJ_SUBSTEPS = "subSteps";

    protected List<BaseStep> subSteps;

    public MacroStep(ObjectTypeName objName, StepCategory category, CVProvider provider) {
        super(objName, category, provider);
        this.subSteps = new LinkedList<>();
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

    public void setSubSteps(List<BaseStep> subSteps) {
        this.subSteps = subSteps;
    }
}
