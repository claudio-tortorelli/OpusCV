package claudiosoft.opusCV;

import java.util.ArrayList;

/**
 *
 * @author Claudio
 */
public class AreaSerie {

    private ArrayList<Double> areaValues;
    private int baseSerieIndex;

    public AreaSerie(int base) {
        areaValues = new ArrayList<>();
        baseSerieIndex = base;
    }

    public ArrayList<Double> getAreaValues() {
        return areaValues;
    }

    public int getBaseSerieIndex() {
        return baseSerieIndex;
    }

    public int getLocMinAreaIndex() {
        double minLoc = Double.MAX_VALUE;
        int minFrameIndex = 0;
        for (int i = 0; i < areaValues.size(); i++) {
            double curValue = areaValues.get(i);
            if (curValue < minLoc) {
                minLoc = curValue;
                minFrameIndex = i;
            }
        }
        return minFrameIndex;
    }

    public int getMidAreaIndex() {
        return areaValues.size() / 2;
    }
}
