package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.CVProvider;
import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.JsonObject;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;

/**
 *
 * @author Claudio
 */
public abstract class BaseStep extends JsonObject {

    private transient long startTime;
    private transient long estimatedTime;

    protected transient BasicConsoleLogger logger;

    public static final String OBJ_CATEGORY = "category";
    public static final String OBJ_PROVIDER = "provider";

    protected StepCategory category;
    protected CVProvider provider;

    public BaseStep(ObjectTypeName objName, StepCategory category) throws OpusCVException {
        this(objName, category, Configuration.get().getDefaultCVProvider());
    }

    public BaseStep(ObjectTypeName objName, StepCategory category, CVProvider provider) throws OpusCVException {
        super(objName);
        this.logger = BasicConsoleLogger.get();
        this.category = category;
        this.provider = provider;
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

    public StepCategory getCategory() {
        return category;
    }

    public void setCategory(StepCategory category) {
        this.category = category;
    }

    public CVProvider getProvider() {
        return provider;
    }

    public void setProvider(CVProvider provider) {
        this.provider = provider;
    }

}
