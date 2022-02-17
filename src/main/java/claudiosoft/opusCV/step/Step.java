package claudiosoft.opusCV.step;

import claudiosoft.opusCV.common.OpusCVException;

/**
 *
 * @author Claudio
 */
public interface Step {

    public abstract void checkPrerequisites() throws OpusCVException;

    public abstract void prepare() throws OpusCVException;

    public abstract void doProcess() throws OpusCVException;

    public abstract void finalize() throws OpusCVException;

}
