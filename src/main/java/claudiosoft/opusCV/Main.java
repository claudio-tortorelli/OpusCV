package claudiosoft.opusCV;

import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.File;
import java.io.IOException;

/**
 * TODO l'image step dovrebbe avere un array di immagini e non una sola
 *
 *
 * @author Claudio
 */
public class Main {

    private static BasicConsoleLogger logger = BasicConsoleLogger.get();

    public static void main(String[] args) throws IOException {

        try {
            File confFile = null;
            if (args.length > 0 && args[0].toLowerCase().startsWith("conf=")) {
                confFile = new File(args[0].substring("conf=".length()));
                if (!confFile.exists()) {
                    throw new IOException("config file not found");
                }
            }
            Configuration.initialize(confFile);

            // start process
            //new Processor().doProcess();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(Constants.RET_CODE_ERR);
        }
        System.exit(Constants.RET_CODE_OK);
    }

}
