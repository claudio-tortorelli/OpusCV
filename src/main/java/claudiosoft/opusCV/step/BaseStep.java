package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import com.github.cliftonlabs.json_simple.JsonObject;

/**
 *
 * @author Claudio
 */
public abstract class BaseStep extends JsonData {

    private long startTime;
    private long estimatedTime;

    protected BasicConsoleLogger logger;

    protected StepType type;
    protected int index;
    protected EngineType engine;
    protected String name;

    protected BaseStep(JsonObject jsonIn) {
        super(jsonIn);
        this.logger = BasicConsoleLogger.get();
        this.type = (StepType) StepKey.getValueFromJson(json, StepKey.TYPE);
        this.index = (int) StepKey.getValueFromJson(json, StepKey.INDEX);
        this.engine = (EngineType) StepKey.getValueFromJson(json, StepKey.ENGINE);
        this.name = (String) StepKey.getValueFromJson(json, StepKey.NAME);
    }

    public void execute() throws OpusCVException {
        logger.info("start " + getClass().getSimpleName());
        startTime = System.currentTimeMillis();

        checkPrerequisites();
        prepare();
        doProcess();
        finalize();

        estimatedTime = System.currentTimeMillis() - startTime;
        logger.debug(getClass().getSimpleName() + " done in " + estimatedTime / 1000 + " sec");
    }

    public abstract void checkPrerequisites() throws OpusCVException;

    public abstract void prepare() throws OpusCVException;

    public abstract void doProcess() throws OpusCVException;

    public abstract void finalize() throws OpusCVException;

    public StepType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public EngineType getEngine() {
        return engine;
    }

    public String getName() {
        return name;
    }
}
