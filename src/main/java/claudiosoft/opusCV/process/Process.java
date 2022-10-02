package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.JsonObject;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.step.BaseStep;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class Process extends JsonObject {

    private Configuration conf;
    private final List<BaseStep> steps;
    private final BasicConsoleLogger logger;

    public Process(String receipt) throws OpusCVException {
        super(ObjectTypeName.PROCESS);
        this.logger = BasicConsoleLogger.get();
        this.conf = null;
        this.steps = new LinkedList<>();
        fromJsonReceipt(receipt);
        isReady();
    }

    public final List<BaseStep> getSteps() {
        return steps;
    }

    public final Configuration getConf() {
        return conf;
    }

    private void fromJsonReceipt(String receipt) {
        parseConfig(receipt);
        parseSteps(receipt);
    }

    private void parseConfig(String receipt) {

    }

    private void parseSteps(String receipt) {
//        steps.add(step);
//        logger.info(step.getClass().getSimpleName() + " added to process");
    }

    private void isReady() throws OpusCVException {
        if (conf == null) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "missing configuration", null);
        }
        if (steps.isEmpty()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "missing steps", null);
        }
    }
}
