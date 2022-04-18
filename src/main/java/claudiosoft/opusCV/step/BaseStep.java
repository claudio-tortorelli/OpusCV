package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;

/**
 *
 * @author Claudio
 */
public abstract class BaseStep {

    private transient long startTime;
    private transient long estimatedTime;

    protected transient BasicConsoleLogger logger;

    protected StepType type;
    protected int index;
    protected EngineType engine;
    protected String name;

    protected BaseStep() {
        this.logger = BasicConsoleLogger.get();
        this.type = (StepType) StepKey.TYPE.getDefaultValue();
        this.index = (int) StepKey.INDEX.getDefaultValue();
        this.engine = (EngineType) StepKey.ENGINE.getDefaultValue();
        this.name = (String) StepKey.NAME.getDefaultValue();
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
