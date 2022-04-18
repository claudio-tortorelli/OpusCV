package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.EngineType;

/**
 *
 * @author Claudio
 */
public class Configuration {

    private EngineType defaultEngine;
    private String processFolder;

    public Configuration() {
        super();
        this.defaultEngine = (EngineType) ConfKey.DEFAULT_ENGINE.getDefaultValue();
        this.processFolder = (String) ConfKey.PROCESS_FOLDER.getDefaultValue();
    }

    public EngineType getDefaultEngine() {
        return defaultEngine;
    }

    public void setDefaultEngine(EngineType defaultEngine) {
        this.defaultEngine = defaultEngine;
    }

    public String getProcessFolder() {
        return processFolder;
    }

    public void setProcessFolder(String processFolder) {
        this.processFolder = processFolder;
    }

}
