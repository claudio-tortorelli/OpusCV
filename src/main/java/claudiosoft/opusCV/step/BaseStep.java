package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.ObjectTypeName;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.Provider;
import claudiosoft.opusCV.logger.BasicConsoleLogger;

/**
 *
 * @author Claudio
 */
public abstract class BaseStep {

    private transient long startTime;
    private transient long estimatedTime;

    protected transient BasicConsoleLogger logger;

    public static final String OBJ_TYPENAME = "objTypeName";
    public static final String OBJ_CATEGORY = "category";
    public static final String OBJ_INDEX = "index";
    public static final String OBJ_PROVIDER = "provider";

    protected String objTypeName;
    protected StepCategory category;
    protected int index;
    protected Provider provider;

    protected BaseStep() {
        this.logger = BasicConsoleLogger.get();
        this.category = StepCategory.BASE;
        this.objTypeName = ObjectTypeName.BASE_STEP.get();
        this.index = 0;
        this.provider = Provider.OPENCV;
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

    public String getObjTypeName() {
        return objTypeName;
    }

    public void setObjTypeName(String objTypeName) {
        this.objTypeName = objTypeName;
    }

    public StepCategory getCategory() {
        return category;
    }

    public void setCategory(StepCategory category) {
        this.category = category;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
