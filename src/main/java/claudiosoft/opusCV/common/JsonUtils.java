package claudiosoft.opusCV.common;

import claudiosoft.opusCV.step.BaseStep;
import claudiosoft.opusCV.step.MacroStep;
import claudiosoft.opusCV.step.StepCategory;
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

    public static String objToJson(Object obj) {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return gson.toJson(obj);
    }

    public static Object objFromJson(String json) throws OpusCVException {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        JsonElement objNameId = gson.fromJson(json, JsonObject.class).get(BaseStep.OBJ_TYPENAME);
        return objFromJson(json, objNameId);
    }

    private static Object objFromJson(String json, JsonElement objNameId) throws OpusCVException {
        if (objNameId == null) {
            throw new OpusCVException(ErrorCode.JSON_READ);
        }
        String typeStr = objNameId.getAsString();
        if (DummyObject.class.getSimpleName().equals(typeStr)) {
            return (DummyObject) gson.fromJson(json, DummyObject.class);
        } else if (DummyStep.class.getSimpleName().equals(typeStr)) {
            return (DummyStep) gson.fromJson(json, DummyStep.class);
        } else if (MacroStep.class.getSimpleName().equals(typeStr)) {
            MacroStep macro = new MacroStep();
            JsonObject jsonMacroObject = gson.fromJson(json, JsonObject.class);
            JsonElement objSubSteps = jsonMacroObject.get(MacroStep.OBJ_SUBSTEPS);
            if (objSubSteps == null) {
                throw new OpusCVException(ErrorCode.INCOMPLETE_MACRO_OBJ);
            }
            for (int iSub = 0; iSub < objSubSteps.getAsJsonArray().size(); iSub++) {
                String jsonSubStep = objSubSteps.getAsJsonArray().get(iSub).toString();
                JsonElement subObjNameId = gson.fromJson(jsonSubStep, JsonObject.class).get(BaseStep.OBJ_TYPENAME);
                macro.getSubSteps().add((BaseStep) objFromJson(jsonSubStep, subObjNameId));
            }
            macro.setObjTypeName(jsonMacroObject.get(BaseStep.OBJ_TYPENAME).getAsString());
            macro.setIndex(jsonMacroObject.get(BaseStep.OBJ_INDEX).getAsInt());
            macro.setProvider(Provider.valueOf(jsonMacroObject.get(BaseStep.OBJ_PROVIDER).getAsString()));
            macro.setCategory(StepCategory.valueOf(jsonMacroObject.get(BaseStep.OBJ_CATEGORY).getAsString()));

            return macro;
        }
        throw new OpusCVException(ErrorCode.UNSUPPORTED_JSON_OBJ);
    }
}
