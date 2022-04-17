package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.EngineType;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

/**
 *
 * @author Claudio
 */
public class ConfKey {

    public static final JsonKey PROCESS_FOLDER = Jsoner.mintJsonKey("process_folder", "");

    public static String getProcessFolder(JsonObject json) {
        return json.getStringOrDefault(PROCESS_FOLDER);
    }

    public static final JsonKey DEFAULT_ENGINE = Jsoner.mintJsonKey("default_engine", EngineType.OPENCV);

    public static EngineType getDefaultEngine(JsonObject json) {
        if (json.getString(DEFAULT_ENGINE) != null) {
            return EngineType.valueOf(json.getString(DEFAULT_ENGINE));
        }
        return (EngineType) DEFAULT_ENGINE.getValue();
    }
}
