package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.JsonData;
import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.io.StringWriter;

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
    protected JsonObject jsonOut;
//    protected List<BaseStep> subSteps; //TODO?

    protected BaseStep(JsonObject jsonIn) {
        this.type = StepType.BASE;
        this.index = 0;
        this.engine = EngineType.OPENCV;
        this.logger = BasicConsoleLogger.get();
        this.jsonOut = new JsonObject();
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

    @Override
    public String toJson() throws OpusCVException {
        final StringWriter writer = new StringWriter();
        try {
            jsonOut.toJson(writer);
        } catch (final IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new OpusCVException(ErrorCode.JSON_WRITE);
        }
        return writer.toString();
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
