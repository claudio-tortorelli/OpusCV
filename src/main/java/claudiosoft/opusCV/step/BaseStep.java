package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.JsonObject;
import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Provider;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.process.Configuration;

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
    protected Provider provider;

    public BaseStep(ObjectTypeName objName, StepCategory category) {
        //TODO sistema situazione della config che dovrebbe essere statica
        //TODO anche i figli nn dovrebbero chiedere il provider nel costruttorre
        this(objName, category, Configuration.DefaultProvider);
    }

    public BaseStep(ObjectTypeName objName, StepCategory category, Provider provider) {
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
