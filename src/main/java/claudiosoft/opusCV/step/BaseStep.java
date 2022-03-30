package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;

/**
 *
 * @author Claudio
 */
public abstract class BaseStep implements JsonData {

    private long startTime;
    private long estimatedTime;

    protected StepType type;
    protected int index;
    protected BasicConsoleLogger logger;
    protected EngineType engine;
//    protected List<BaseStep> subSteps; //TODO?

    protected BaseStep() {
        this.type = StepType.BASE;
        this.engine = EngineType.OPENCV;
        this.index = 0;
        this.logger = BasicConsoleLogger.get();
    }

    protected BaseStep(JsonObject json) throws IOException {
        type = StepType.valueOf(json.getString(Keys.TYPE));
        index = json.getInteger(Keys.INDEX);
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

    @Override
    public void toJson(JsonObject json) {
        json.put(Keys.TYPE, type.name());
        json.put(Keys.ENGINE, engine.name());
        json.put(Keys.INDEX, index);
    }

    @Override
    public JsonObject fromJson(String json) throws JsonException {
        return (JsonObject) Jsoner.deserialize(json);
    }

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
