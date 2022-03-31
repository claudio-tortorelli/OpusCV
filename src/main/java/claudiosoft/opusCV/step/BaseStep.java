package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import claudiosoft.opusCV.common.Keys;
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

    protected StepType type;
    protected int index;
    protected BasicConsoleLogger logger;
    protected EngineType engine;
//    protected List<BaseStep> subSteps; //TODO?

    protected BaseStep(JsonObject jsonIn) {
        super();
        this.type = StepType.BASE;
        this.index = 0;
        this.engine = EngineType.OPENCV;
        this.logger = BasicConsoleLogger.get();
        if (jsonIn != null) {
            this.type = StepType.valueOf(jsonIn.getString(Keys.TYPE));
            this.index = jsonIn.getInteger(Keys.INDEX);
        }
        jsonOut.put(Keys.TYPE, type.name());
        jsonOut.put(Keys.ENGINE, engine.name());
        jsonOut.put(Keys.INDEX, index);
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

}
