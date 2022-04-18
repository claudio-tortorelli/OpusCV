package claudiosoft.opusCV.common;

import claudiosoft.opusCV.step.dummy.Book;
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
        String objType = jsonObject.get("objType").getAsString();
        switch (objType) {
            case "Book":
                return (Book) gson.fromJson(json, Book.class);
            case "DummyStep":
                return (DummyStep) gson.fromJson(json, DummyStep.class);
        }
        throw new OpusCVException(ErrorCode.JSON_READ);
    }
}
