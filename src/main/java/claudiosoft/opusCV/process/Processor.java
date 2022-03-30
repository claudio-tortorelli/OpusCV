package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.step.BaseStep;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class Processor {

    private final List<BaseStep> steps;
    private final BasicConsoleLogger logger;

    public Processor() {
        this.steps = new LinkedList<>();
        this.logger = BasicConsoleLogger.get();
    }

    public void addStep(BaseStep step) {
        steps.add(step);
        logger.info(step.getClass().getSimpleName() + " added to process");
    }

    public void doProcess() throws OpusCVException {
        logger.info("start main process");
        long startTime = System.currentTimeMillis();
        for (BaseStep step : steps) {
            step.execute();
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        logger.info("main process end in " + estimatedTime / 1000 + " sec");
    }

}
