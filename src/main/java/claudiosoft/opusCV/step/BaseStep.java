package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.StepType;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public abstract class BaseStep implements Step, Jsonable {

    private long startTime;
    private long estimatedTime;

    protected StepType type;
    protected int stepCount;
    protected BasicConsoleLogger logger; //TODO, rimuovere da membro
    protected List<BaseStep> subSteps;

    protected BaseStep() {
        this.type = StepType.BASE;
        this.stepCount = 0;
        this.logger = BasicConsoleLogger.get();
        this.subSteps = new LinkedList<>();
    }

    protected BaseStep(JsonObject json) throws IOException {
        fromJson(json);
    }

    @Override
    public void prepare() throws OpusCVException {
        logger.info("start " + getClass().getSimpleName());
        startTime = System.currentTimeMillis();
        for (BaseStep subStep : subSteps) {
            subStep.prepare();
        }
    }

    @Override
    public void finalize() throws OpusCVException {
        for (BaseStep subStep : subSteps) {
            subStep.finalize();
        }
        estimatedTime = System.currentTimeMillis() - startTime;
        logger.debug(getClass().getSimpleName() + " done in " + estimatedTime / 1000 + " sec");
    }

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("type", type.name());
        json.put("stepCount", stepCount);
        json.toJson(writer);
    }

    protected void toJson(JsonObject json) throws IOException {
        json.put("type", type.name());
        json.put("stepCount", stepCount);
    }

    protected void fromJson(JsonObject json) throws IOException {
        type = StepType.valueOf(json.getString(Keys.TYPE));
        stepCount = json.getInteger(Keys.STEPCOUNT);
    }

    public void addStep(BaseStep step) {
        subSteps.add(step);
    }

    public StepType getType() {
        return type;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public BasicConsoleLogger getLogger() {
        return logger;
    }

    public void setLogger(BasicConsoleLogger logger) {
        this.logger = logger;
    }

    public List<BaseStep> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(List<BaseStep> subSteps) {
        this.subSteps = subSteps;
    }

}
