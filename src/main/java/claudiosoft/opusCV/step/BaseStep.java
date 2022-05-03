package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.ObjectTypeName;
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

    protected String objTypeName;
    protected StepCategory category;
    protected int index;
    protected EngineType engine;

    protected BaseStep() {
        this.logger = BasicConsoleLogger.get();
        this.category = StepCategory.BASE;
        this.objTypeName = ObjectTypeName.BASE_STEP.get();
        this.index = 0;
        this.engine = EngineType.OPENCV;
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

    public StepCategory getType() {
        return category;
    }

    public int getIndex() {
        return index;
    }

    public EngineType getEngine() {
        return engine;
    }

    public String getObjectTypeName() {
        return objTypeName;
    }

}
