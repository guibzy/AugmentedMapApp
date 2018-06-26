import TUIO.TuioObject;

import javax.swing.*;

/**
 * Abstract class that give the model for a ProjectedInformation class.
 */
public abstract class ProjectedInformation {

    private TuioObject TuioObject;
    private ScaledImageLabel Map;
    private AugmentedInformation AugmentedInformation;

    public ProjectedInformation(TUIO.TuioObject tuioObject, ScaledImageLabel map, AugmentedInformation augmentedInformation) {
        TuioObject = tuioObject;
        Map = map;
        AugmentedInformation = augmentedInformation;
    }

    public AugmentedInformation getAugmentedInformation() {
        return AugmentedInformation;
    }

    public void setAugmentedInformation(AugmentedInformation augmentedInformation) {
        AugmentedInformation = augmentedInformation;
    }

    public TUIO.TuioObject getTuioObject() {
        return TuioObject;
    }

    public ScaledImageLabel getMap() {
        return Map;
    }

    public void setMap(ScaledImageLabel map) {
        Map = map;
    }

    public void setTuioObject(TUIO.TuioObject tuioObject) {
        TuioObject = tuioObject;
    }

    public abstract void drawOnImage(JLabel image, int x, int y);
}
