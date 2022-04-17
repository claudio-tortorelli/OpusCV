package claudiosoft.opusCV.step;

import claudiosoft.opusCV.step.dummy.DummyStep;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;

/**
 *
 * @author Claudio
 */
public class StepFactory {

    public static BaseStep get(JsonObject json) throws IOException {
        BaseStep step = null;
        final StepType type = (StepType) StepKey.getValueFromJson(json, StepKey.TYPE);
        if (type.equals(StepType.DUMMY)) {
            step = new DummyStep(json);
        } else if (type.equals(StepType.IMG)) {
//            step = new DummyStep(json); //TODO
        }
        return step;
    }

    private StepFactory() {

    }
}
