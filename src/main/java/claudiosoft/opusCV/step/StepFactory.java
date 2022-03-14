package claudiosoft.opusCV.step;

import claudiosoft.opusCV.step.dummy.DummyStep;
import claudiosoft.opusCV.common.Keys;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;

/**
 *
 * @author Claudio
 */
public class StepFactory {

    public static BaseStep get(JsonObject json) throws IOException {
        BaseStep step = null;
        final StepType type = StepType.valueOf(json.getString(Keys.TYPE));
        if (type.equals(StepType.TEST)) {
            step = new DummyStep(json);
        } else if (type.equals(StepType.IMG)) {
            step = new DummyStep(json);
        }
        return step;
    }

    private StepFactory() {

    }
}
