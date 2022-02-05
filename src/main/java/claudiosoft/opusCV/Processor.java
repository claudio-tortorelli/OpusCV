package claudiosoft.opusCV;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.Options;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.GenericStep;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class Processor {

    private final Options options;
    private List<GenericStep> steps;
    private BasicConsoleLogger logger;

    public Processor(Options opts) {
        this.options = opts;
        this.steps = new LinkedList<>();
        this.logger = new BasicConsoleLogger(this.options.getLoggerLevel());
    }

    public Options getOptions() {
        return options;
    }

    public void addStep(GenericStep step) {
        steps.add(step);
        logger.info(step.getClass().getSimpleName() + " added to process");
    }

    public void doProcess() throws OpusCVException {
        logger.info("start main process");
        long startTime = System.currentTimeMillis();
        for (GenericStep step : steps) {
            step.prepare();
            step.checkPrerequisites();
            step.doProcess();
            step.finalize();
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        logger.info("main process end in " + estimatedTime / 1000 + " sec");
    }

}
