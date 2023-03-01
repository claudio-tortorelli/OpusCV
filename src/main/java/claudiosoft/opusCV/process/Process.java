package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class Process {

    private final String processFolder;
    private final List<ProcessStep> steps;
    private final BasicConsoleLogger logger;
    private short stepCount;

    public Process(File processFile) throws OpusCVException {
        this(processFile, Configuration.get().getProcessFolder());
    }

    public Process(File processFile, String processFolder) throws OpusCVException {
        this.logger = BasicConsoleLogger.get();
        this.processFolder = processFolder;
        this.steps = new LinkedList<>();
        this.stepCount = 1;

        parseSteps(processFile);
        validate();
    }

    public final List<ProcessStep> getSteps() {
        return steps;
    }

    public final String getProcessFolder() {
        return processFolder;
    }

    private void parseSteps(File processFile) {
        logger.info("start parsing process");
        //TODO parse
        //logger.info(step.getClass().getSimpleName() + " added to process");
        logger.info("end parsing process");
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
