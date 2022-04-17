package claudiosoft.opusCV.common;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import java.io.StringWriter;

/**
 *
 * @author Claudio
 */
public abstract class JsonData {

    protected JsonObject json;

    public JsonData(JsonObject jsonIn) {
        if (jsonIn == null) {
            jsonIn = new JsonObject();
        }
        this.json = jsonIn;

    }

    public JsonObject getJson() {
        return json;
    }

    public static String toString(JsonObject json) throws OpusCVException {
        final StringWriter writer = new StringWriter();
        try {
            json.toJson(writer);
        } catch (final IOException ex) {
            throw new OpusCVException(ErrorCode.JSON_WRITE);
        }
        return writer.toString();
    }

    public static JsonObject fromString(String json) throws JsonException {
        return (JsonObject) Jsoner.deserialize(json);
    }

}
