package claudiosoft.opusCV.steps;

import claudiosoft.opusCV.BasicConsoleLogger;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public abstract class GenericStep implements Step {

    private long startTime;
    private long estimatedTime;

    protected BasicConsoleLogger logger;
    protected List<GenericStep> subSteps;

    protected GenericStep(BasicConsoleLogger logger) {
        this.logger = logger;
        this.subSteps = new LinkedList<>();
    }

    @Override
    public void prepare() throws Exception {
        logger.info("start " + getClass().getSimpleName());
        startTime = System.currentTimeMillis();
        for (GenericStep subStep : subSteps) {
            subStep.prepare();
        }
    }

    @Override
    public void finalize() throws Exception {
        for (GenericStep subStep : subSteps) {
            subStep.finalize();
        }
        estimatedTime = System.currentTimeMillis() - startTime;
        logger.debug(getClass().getSimpleName() + " done in " + estimatedTime / 1000 + " sec");
    }

    public void addStep(GenericStep step) {
        subSteps.add(step);
    }
}
