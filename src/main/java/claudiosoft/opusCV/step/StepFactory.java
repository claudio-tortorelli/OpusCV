package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.Keys;
import claudiosoft.opusCV.common.StepType;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;

/**
 *
 * @author Claudio
 */
public class StepFactory {

    public static BaseStep get(JsonObject json) throws IOException {
        BaseStep step = null;

        final String type = json.getString(Keys.TYPE);
        if (type.equals(StepType.TEST.name())) {
            step = new JsonTestStep(json);
        }
        return step;
    }

    private StepFactory() {

    }
}
