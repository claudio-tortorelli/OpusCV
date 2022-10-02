package claudiosoft.opusCV;

import claudiosoft.opusCV.common.Constants;
import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.IOException;

/**
 * TODO: creare un file recipt json con conf e steps da parsare creare step
 * container
 *
 * la conf deve essere statica?
 *
 * l'image step dovrebbe avere un array di immagini e non una sola
 *
 * il provider di default deve essere definito nella conf e richiamato dove
 * serve
 *
 * @author Claudio
 */
public class Main {

    private static BasicConsoleLogger logger = BasicConsoleLogger.get();

    public static void main(String[] args) throws IOException {

        try {
            //TODO parse recipt
            //TODO, enable processor's steps
            // start process
            //new Processor().doProcess();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(Constants.RET_CODE_ERR);
        }
        System.exit(Constants.RET_CODE_OK);
    }
}
