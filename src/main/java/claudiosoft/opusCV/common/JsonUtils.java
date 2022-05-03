package claudiosoft.opusCV.common;

import claudiosoft.opusCV.step.MacroStep;
import claudiosoft.opusCV.step.dummy.DummyObject;
import claudiosoft.opusCV.step.dummy.DummyStep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
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
        JsonElement objType = jsonObject.get(ObjectTypeName.OBJ_NAME_ID);
        if (objType == null) {
            throw new OpusCVException(ErrorCode.JSON_READ);
        }
        String typeStr = objType.getAsString();
        if (DummyObject.class.getSimpleName().equals(typeStr)) {
            return (DummyObject) gson.fromJson(json, DummyObject.class);
        } else if (DummyStep.class.getSimpleName().equals(typeStr)) {
            return (DummyStep) gson.fromJson(json, DummyStep.class);
        } else if (MacroStep.class.getSimpleName().equals(typeStr)) {
            MacroStep macro = new MacroStep();
            //TODO, devono essere creati e aggiunti in modo ricorsivo i subSteps
            return (MacroStep) gson.fromJson(json, MacroStep.class);
        }
        throw new OpusCVException(ErrorCode.JSON_READ);
    }
}
