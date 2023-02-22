package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.JsonObject;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.step.BaseStep;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class Process extends JsonObject {

    private final String processFolder;
    private final List<BaseStep> steps;
    private final BasicConsoleLogger logger;

    public Process(String processFolder, String receipt) throws OpusCVException {
        super(ObjectTypeName.PROCESS);
        this.logger = BasicConsoleLogger.get();
        this.processFolder = processFolder;
        this.steps = new LinkedList<>();
        fromJsonReceipt(receipt);
        validate();
    }

    public final List<BaseStep> getSteps() {
        return steps;
    }

    public final String getProcessFolder() {
        return processFolder;
    }

    private void fromJsonReceipt(String receipt) {
        parseSteps(receipt);
    }

    private void parseSteps(String receipt) {
//        steps.add(step);
//        logger.info(step.getClass().getSimpleName() + " added to process");
    }

    private void validate() throws OpusCVException {
        if (processFolder.isEmpty()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "missing process folder", null);
        }
        if (new File(processFolder).exists()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "process folder not found", null);
        }
        if (steps.isEmpty()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "missing steps", null);
        }
    }
}
