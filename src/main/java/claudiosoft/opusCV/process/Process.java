package claudiosoft.opusCV.process;

import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.step.BaseStep;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class Process {

    private Configuration conf;
    private final List<BaseStep> steps;
    private final BasicConsoleLogger logger;

    public Process(String jsonIn) {
        this.logger = BasicConsoleLogger.get();
        this.conf = new Configuration();
        //TODO parse steps
        this.steps = new LinkedList<>();
    }

    public void addStep(BaseStep step) {
        steps.add(step);
        logger.info(step.getClass().getSimpleName() + " added to process");
    }

    public final List<BaseStep> getSteps() {
        return steps;
    }

    public final Configuration getConf() {
        return conf;
    }

}
