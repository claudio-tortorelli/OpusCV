package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public class OpusCVException extends Exception {

    public OpusCVException(ErrorCode errCode) {
        this(errCode, null, null);
    }

    public OpusCVException(ErrorCode errCode, Throwable ex) {
        this(errCode, null, ex);
    }

    public OpusCVException(ErrorCode errCode, String msg, Throwable ex) {
        super(msg != null ? errCode.getCodeMessage(msg) : errCode.getCodeMessage(), ex);
    }

}
