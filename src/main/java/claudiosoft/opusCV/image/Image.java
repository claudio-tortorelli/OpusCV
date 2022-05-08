package claudiosoft.opusCV.image;

import claudiosoft.opusCV.common.Provider;

/**
 *
 * @author Claudio
 */
public abstract class Image {

    protected Provider provider;
    protected String path;
    protected double[] size;
    protected int depth;

    public Image() {
        this(Provider.OPENCV);
    }

    public Image(Provider provider) {
        this.provider = provider;
        this.size = new double[2];
        this.depth = 3;
    }

    public Provider getProvider() {
        return provider;
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
