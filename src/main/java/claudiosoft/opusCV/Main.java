package claudiosoft.opusCV;

import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import claudiosoft.opusCV.process.Processor;
import java.io.IOException;

/**
 * TODO: eliminazione protected BaseStep() { this(null); } ogni step deve essere
 * generato solo dal json e nel prepare inizializza gli oggetti interni
 *
 * @author Claudio
 */
public class Main {

    private static BasicConsoleLogger logger = BasicConsoleLogger.get();

    public static void main(String[] args) throws IOException {

        try {
            Processor process = new Processor();
            //TODO, enable processor's steps
            // start process
            process.doProcess();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(Constants.RET_CODE_ERR);
        }
        System.exit(Constants.RET_CODE_OK);
    }
}
