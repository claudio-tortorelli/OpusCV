package claudiosoft.opusCV.process;

import claudiosoft.opusCV.step.BaseStep;
import java.io.File;

/**
 *
 * @author Claudio
 */
public class ProcessStep {

    private short id;

    private String inFolder;
    private String tempFolder;
    private String outFolder;

    private int res;

    private BaseStep step;

    private boolean keepTemp;

    public ProcessStep(BaseStep step, short id, String parentFolder) {
        this(step, id, parentFolder, false);
    }

    public ProcessStep(BaseStep step, short id, String parentFolder, boolean keepTemp) {
        this.step = step;
        this.id = id;
        this.inFolder = String.format("%s%s%d_in", parentFolder, File.separator, id);
        this.tempFolder = String.format("%s%s%d_temp", parentFolder, File.separator, id);
        this.outFolder = String.format("%s%s%d_out", parentFolder, File.separator, id);
        this.keepTemp = keepTemp;
    }

    public short getId() {
        return id;
    }

    public String getInFolder() {
        return inFolder;
    }

    public String getTempFolder() {
        return tempFolder;
    }

    public String getOutFolder() {
        return outFolder;
    }

    public int getRes() {
        return res;
    }

    public BaseStep getStep() {
        return step;
    }

    public boolean isKeepTemporaryFiles() {
        return keepTemp;
    }
}
