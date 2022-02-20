package claudiosoft.opusCV.common;

import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.util.ArrayList;

/**
 *
 * @author Claudio
 */
public class Keys {

    public static final JsonKey TYPE = Jsoner.mintJsonKey("type", StepType.BASE.name());
    public static final JsonKey STEPCOUNT = Jsoner.mintJsonKey("stepCount", 0);
    public static final JsonKey NAME = Jsoner.mintJsonKey("name", "");
    public static final JsonKey COUNT = Jsoner.mintJsonKey("count", 0);
    public static final JsonKey PRECISION = Jsoner.mintJsonKey("precision", 0.0);
    public static final JsonKey LIST_INT = Jsoner.mintJsonKey("list_int", new ArrayList());
}
