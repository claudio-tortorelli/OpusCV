package claudiosoft.opusCV.image;

import claudiosoft.opusCV.common.EngineType;

/**
 *
 * @author Claudio
 */
public abstract class Image {

    protected EngineType engine;
    protected double[] size;
    protected int depth;

    public Image() {
        this(EngineType.OPENCV);
    }

    public Image(EngineType engine) {
        this.engine = engine;
        this.size = new double[2];
        this.depth = 3;
    }

    public EngineType getEngine() {
        return engine;
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

}
