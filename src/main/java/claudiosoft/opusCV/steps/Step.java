package claudiosoft.opusCV.steps;

/**
 *
 * @author Claudio
 */
public interface Step {

    public abstract void doProcess() throws Exception;

    public abstract void prepare() throws Exception;

    public abstract void finalize() throws Exception;

    public abstract void checkPrerequisites() throws Exception;
}
