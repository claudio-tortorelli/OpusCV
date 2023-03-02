package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.JsonUtils;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Utils;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.File;
import java.io.IOException;
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

    public Process(File processFile) throws OpusCVException, IOException {
        this(processFile, Configuration.get().getProcessFolder());
    }

    public Process(File processFile, String processFolder) throws OpusCVException, IOException {
        this.logger = BasicConsoleLogger.get();
        if (!processFolder.endsWith("/")) {
            processFolder += "/";
        }
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

    private void parseSteps(File processFile) throws IOException, OpusCVException {
        logger.info("start parsing process");
        String process = "";
        for (String line : Utils.readAllLines(processFile.toPath())) {
            process += line;
            process += "\n";
        }
        logger.debug("read process:");
        logger.debug(process);

        //TODO: non chiama il costruttore...
        Object obj = JsonUtils.objFromJson(process);
        logger.info("end parsing process");
    }

    private void validate() throws OpusCVException {
        if (processFolder.isEmpty()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "missing process folder", null);
        }
        if (!new File(processFolder).exists()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "process folder not found", null);
        }
        if (steps.isEmpty()) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "missing steps", null);
        }
    }
}
