package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public class OpusCVException extends Exception {

    public OpusCVException(ErrorCode errCode) {
        this(errCode, null);
    }

    public OpusCVException(String message) {
        this(message, null);
    }

    public OpusCVException(ErrorCode errCode, Throwable ex) {
        this(errCode.getCodeMessage(), ex);
    }

    public OpusCVException(String errMsg, Throwable ex) {
        super(errMsg, ex);
    }
}
