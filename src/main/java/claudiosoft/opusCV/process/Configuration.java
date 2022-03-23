package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.Keys;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author Claudio
 */
public class Configuration implements Jsonable {

    private EngineType engine;
    private String processFolder;

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        toJson(json);
        json.toJson(writer);

    }

    protected void toJson(JsonObject json) throws IOException {
        json.put(Keys.ENGINE, engine.name());
        json.put(Keys.PROCESS_FOLDER, processFolder);
    }

}
