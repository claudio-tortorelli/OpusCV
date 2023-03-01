package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;

/**
 *
 * @author Claudio
 */
public class Processor {

    private final BasicConsoleLogger logger;

    public Processor() {
        this.logger = BasicConsoleLogger.get();
    }

    public void doProcess(Process process) throws OpusCVException {
        logger.info("start main process");
        long startTime = System.currentTimeMillis();
        //TODO implement process execution
//        for (BaseStep step : process.getSteps()) {
//            step.execute();
//        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        logger.info("main process end in " + estimatedTime / 1000 + " sec");
    }

}
