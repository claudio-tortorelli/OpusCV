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

    protected JsonObject jsonOut;

    public JsonData() {
        this.jsonOut = new JsonObject();
    }

    public String toJson() throws OpusCVException {
        final StringWriter writer = new StringWriter();
        try {
            jsonOut.toJson(writer);
        } catch (final IOException ex) {
            throw new OpusCVException(ErrorCode.JSON_WRITE);
        }
        return writer.toString();
    }

    public JsonObject fromJson(String json) throws JsonException {
        return (JsonObject) Jsoner.deserialize(json);
    }

}
