package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import claudiosoft.opusCV.common.Keys;
import com.github.cliftonlabs.json_simple.JsonObject;

/**
 *
 * @author Claudio
 */
public class Configuration extends JsonData {

    private EngineType defaultEngine;
    private String processFolder;

    public Configuration(JsonObject jsonIn) {
        super();
        this.defaultEngine = EngineType.OPENCV;
        this.processFolder = "";
        if (jsonIn != null) {
            this.defaultEngine = EngineType.valueOf(jsonIn.getString(Keys.ENGINE));
            this.processFolder = jsonIn.getString(Keys.PROCESS_FOLDER);
        }
        jsonOut.put(Keys.ENGINE, defaultEngine.name());
        jsonOut.put(Keys.PROCESS_FOLDER, processFolder);
    }

}
