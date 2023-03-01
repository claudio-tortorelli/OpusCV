package claudiosoft.opusCV.common;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    private String processFolder;

    public static Configuration initialize() throws IOException, URISyntaxException {
        return initialize(null);
    }

    public static Configuration initialize(File confFile) throws IOException, URISyntaxException {
        if (confFile == null && conf != null) {
            return conf;
        }
        conf = new Configuration(confFile);
        return conf;
    }

    public static Configuration get() throws OpusCVException {
        if (conf == null) {
            throw new OpusCVException(ErrorCode.INIT_ERROR, "configuration not initialized", null);
        }
        return conf;
    }

    private Configuration(File confFile) throws IOException, URISyntaxException {
        // default conf
        this.version = Constants.CONFIGURATION_VERSION;
        this.defaultCVProvider = CVProvider.OPENCV;
        this.processFolder = new File(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getAbsolutePath();

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
            } else if (line.startsWith(ConfigurationEntries.PROCESS_FOLDER)) {
                this.processFolder = line.substring(ConfigurationEntries.PROCESS_FOLDER.length() + 1);
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

    public String getProcessFolder() {
        return processFolder;
    }

}
