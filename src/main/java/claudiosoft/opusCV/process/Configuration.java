package claudiosoft.opusCV.process;

import claudiosoft.opusCV.common.Provider;

/**
 *
 * @author Claudio
 */
public class Configuration {

    private Provider defaultProvider;
    private String processFolder;

    public Configuration() {
        this.defaultProvider = Provider.OPENCV;
        this.processFolder = "";
    }

    public Provider getDefaultProvider() {
        return defaultProvider;
    }

    public void setDefaultProvider(Provider defaultProvider) {
        this.defaultProvider = defaultProvider;
    }

    public String getProcessFolder() {
        return processFolder;
    }

    public void setProcessFolder(String processFolder) {
        this.processFolder = processFolder;
    }

}
