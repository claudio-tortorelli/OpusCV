package claudiosoft.opusCV.common;

import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.common.ErrorCode;
import claudiosoft.opusCV.common.OpusCVException;
import claudiosoft.opusCV.step.dummy.DummyObject;
import claudiosoft.opusCV.step.dummy.DummyStep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 *
 * @author Claudio
 */
public class JsonUtils {

    private static Gson gson = null;

    public static String toJson(Object obj) {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return gson.toJson(obj);
    }

    public static Object fromJson(String json) throws OpusCVException {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String objType = jsonObject.get(Constants.OBJ_TYPE).getAsString();
        if (DummyObject.class.getName().endsWith(objType)) {
            return (DummyObject) gson.fromJson(json, DummyObject.class);
        } else if (DummyStep.class.getName().endsWith(objType)) {
            return (DummyStep) gson.fromJson(json, DummyStep.class);
        }
        throw new OpusCVException(ErrorCode.JSON_READ);
    }
}
