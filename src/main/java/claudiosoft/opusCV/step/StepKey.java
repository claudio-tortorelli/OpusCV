package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author Claudio
 */
public class StepKey {

    ///////// BASE
    public static final JsonKey TYPE = Jsoner.mintJsonKey("type", StepType.BASE);
    public static final JsonKey ENGINE = Jsoner.mintJsonKey("engine", EngineType.OPENCV);
    public static final JsonKey INDEX = Jsoner.mintJsonKey("index", 0);
    public static final JsonKey NAME = Jsoner.mintJsonKey("name", "");

    /////////// DUMMY
    public static final JsonKey DUMMY_COUNT = Jsoner.mintJsonKey("count", 0);
    public static final JsonKey DUMMY_PRECISION = Jsoner.mintJsonKey("precision", 0.0);
    public static final JsonKey DUMMY_LIST_INT = Jsoner.mintJsonKey("list_int", new ArrayList());

    public static Object getValueFromJson(JsonObject json, JsonKey key) {
        for (Entry<String, Object> entry : json.entrySet()) {
            if (entry.getKey().equals(key.getKey())) {
                return entry.getValue();
            }
        }
        return key.getValue();
    }

}
