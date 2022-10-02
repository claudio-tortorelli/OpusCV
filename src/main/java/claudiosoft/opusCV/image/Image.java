package claudiosoft.opusCV.image;

import java.io.File;

/**
 *
 * @author Claudio
 */
public abstract class Image {

    protected String path;
    protected double[] size;
    protected int depth;

    public Image() {
        this.path = "";
        this.size = new double[2];
        this.depth = 3;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return new File(path).getName();
    }

    public String getAbsolutePath() {
        return new File(path).getAbsolutePath();
    }

    public double[] getSize() {
        return size;
    }

    public void setSize(double[] size) {
        this.size = size;
    }

    public void setSize(double width, double height) {
        this.size[0] = width;
        this.size[1] = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    abstract public Object getRaw();

    abstract public void release();

}
