package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.BasicConsoleLogger;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.common.StepType;
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
public abstract class GenericStep implements Step, Jsonable {

    private long startTime;
    private long estimatedTime;

    protected StepType type;
    protected int stepCount;
    protected BasicConsoleLogger logger;
    protected List<GenericStep> subSteps;

    protected GenericStep() {
        this(null);
    }

    protected GenericStep(BasicConsoleLogger logger) {
        this.type = StepType.GENERIC;
        this.stepCount = 0;
        this.logger = logger;
        this.subSteps = new LinkedList<>();
    }

    @Override
    public void prepare() throws OpusCVException {
        logger.info("start " + getClass().getSimpleName());
        startTime = System.currentTimeMillis();
        for (GenericStep subStep : subSteps) {
            subStep.prepare();
        }
    }

    @Override
    public void finalize() throws OpusCVException {
        for (GenericStep subStep : subSteps) {
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

    public void addStep(GenericStep step) {
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

    public List<GenericStep> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(List<GenericStep> subSteps) {
        this.subSteps = subSteps;
    }

}
