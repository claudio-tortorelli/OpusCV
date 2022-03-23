package claudiosoft.opusCV.common;

import claudiosoft.opusCV.step.StepType;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.util.ArrayList;

/**
 *
 * @author Claudio
 */
public class Keys {

    public static final JsonKey TYPE = Jsoner.mintJsonKey("type", StepType.BASE.name());
    public static final JsonKey ENGINE = Jsoner.mintJsonKey("engine", EngineType.OPENCV.name());
    public static final JsonKey INDEX = Jsoner.mintJsonKey("index", -1);
    public static final JsonKey PROCESS_FOLDER = Jsoner.mintJsonKey("process_folder", "");

    public static final JsonKey TEST_NAME = Jsoner.mintJsonKey("name", "");
    public static final JsonKey TEST_COUNT = Jsoner.mintJsonKey("count", 0);
    public static final JsonKey TEST_PRECISION = Jsoner.mintJsonKey("precision", 0.0);
    public static final JsonKey TEST_LIST_INT = Jsoner.mintJsonKey("list_int", new ArrayList());
}
