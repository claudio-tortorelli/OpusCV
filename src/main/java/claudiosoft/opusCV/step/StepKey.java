package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import java.util.ArrayList;

/**
 *
 * @author Claudio
 */
public class StepKey {

    ///////// BASE
    public static final JsonData TYPE = new JsonData("type", StepType.BASE);
    public static final JsonData ENGINE = new JsonData("engine", EngineType.OPENCV);
    public static final JsonData INDEX = new JsonData("index", 0);
    public static final JsonData NAME = new JsonData("name", "");

    /////////// DUMMY
    public static final JsonData DUMMY_COUNT = new JsonData("count", 0);
    public static final JsonData DUMMY_PRECISION = new JsonData("precision", 0.0);
    public static final JsonData DUMMY_LIST_INT = new JsonData("list_int", new ArrayList());

}
