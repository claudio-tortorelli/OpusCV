package claudiosoft.opusCV.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Singleton configuration object
 *
 * @author Claudio
 */
public class Configuration {

    private static Configuration conf;

    private int version;
    private CVProvider defaultCVProvider;

    public static Configuration initialize() throws IOException {
        return initialize(null);
    }

    public static Configuration initialize(File confFile) throws IOException {
        if (conf != null) {
            return conf;
        }
        conf = new Configuration(confFile);
        return conf;
    }

    public static Configuration getInstance() throws OpusCVException {
        if (conf == null) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "configuration not initialized", null);
        }
        return conf;
    }

    private Configuration(File confFile) throws IOException {
        // default conf
        this.version = Constants.CONFIGURATION_VERSION;
        this.defaultCVProvider = CVProvider.OPENCV;

        if (confFile == null) {
            return;
        }
        // override by file
        for (String line : Files.readAllLines(confFile.toPath())) {
            line = line.trim();
            if (line.startsWith(ConfigurationEntries.CONF_VER)) {
                this.version = Integer.parseInt(line.substring(ConfigurationEntries.CONF_VER.length() + 1));
            } else if (line.startsWith(ConfigurationEntries.DEFAULT_CV_PROVIDER)) {
                this.defaultCVProvider = retrieveCVProvider(line.substring(ConfigurationEntries.DEFAULT_CV_PROVIDER.length() + 1));
            }
        }
    }

    private CVProvider retrieveCVProvider(String providerName) {
        for (CVProvider prov : CVProvider.values()) {
            if (prov.name().equalsIgnoreCase(providerName)) {
                return prov;
            }
        }
        return CVProvider.OPENCV; // default
    }

    public int getVersion() {
        return version;
    }

    public CVProvider getDefaultCVProvider() {
        return defaultCVProvider;
    }
}
