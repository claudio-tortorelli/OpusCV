package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.EngineType;
import claudiosoft.opusCV.common.JsonData;
import com.github.cliftonlabs.json_simple.JsonObject;

/**
 *
 * @author Claudio
 */
public class Configuration extends JsonData {

    private EngineType defaultEngine;
    private String processFolder;

    public Configuration(JsonObject jsonIn) {
        super(jsonIn);
        this.defaultEngine = ConfKey.getDefaultEngine(json);
        this.processFolder = ConfKey.getProcessFolder(json);
    }

}
