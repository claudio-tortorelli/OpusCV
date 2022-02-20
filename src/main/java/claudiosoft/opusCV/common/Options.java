package claudiosoft.opusCV.common;

import claudiosoft.opusCV.logger.BasicConsoleLogger;
import java.io.File;

/**
 *
 * @author Claudio
 */
public class Options {

    private BasicConsoleLogger.LogLevel loggerLevel;
    private File file;
    private int startFrame;
    private int endFrame;
    private int stepFrame;
    private int bufferSize;
    private File outFolder;
    private File frameFolder;
    private String frameBaseName;
    private int frameRotation;
    private int videoRotation;

    public Options() {
        this.loggerLevel = BasicConsoleLogger.LogLevel.NORMAL;
        this.file = null;
        this.startFrame = 0;
        this.endFrame = Integer.MAX_VALUE;
        this.stepFrame = 1;
        this.bufferSize = 1;
        this.frameFolder = null;
        this.frameBaseName = null;
        this.outFolder = null;
        this.frameRotation = -1;
        this.videoRotation = -1;
    }

    public BasicConsoleLogger.LogLevel getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(BasicConsoleLogger.LogLevel loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getStartFrame() {
        return startFrame;
    }

    public void setStartFrame(int startFrame) {
        this.startFrame = startFrame;
    }

    public int getEndFrame() {
        return endFrame;
    }

    public void setEndFrame(int endFrame) {
        this.endFrame = endFrame;
    }

    public int getStepFrame() {
        return stepFrame;
    }

    public void setStepFrame(int stepFrame) {
        this.stepFrame = stepFrame;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public File getFrameFolder() {
        return frameFolder;
    }

    public void setFrameFolder(File framefolder) {
        this.frameFolder = framefolder;
    }

    public String getFrameBaseName() {
        return frameBaseName;
    }

    public void setFrameBaseName(String frameBaseName) {
        this.frameBaseName = frameBaseName;
    }

    public int getFrameRotation() {
        return frameRotation;
    }

    public void setFrameRotation(int rotating) {
        this.frameRotation = rotating;
    }

    public File getOutFolder() {
        return outFolder;
    }

    public void setOutFolder(File outFolder) {
        this.outFolder = outFolder;
    }

    public int getVideoRotation() {
        return videoRotation;
    }

    public void setVideoRotation(int videoRotation) {
        this.videoRotation = videoRotation;
    }
}
