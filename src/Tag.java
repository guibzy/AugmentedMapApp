import TUIO.TuioObject;

import javax.swing.*;

public abstract class Tag {

    private TuioObject TuioObject;

    public Tag(TuioObject tuioObject) {
        this.setTuioObject(tuioObject);
    }

    public TUIO.TuioObject getTuioObject() {
        return TuioObject;
    }

    public void setTuioObject(TUIO.TuioObject tuioObject) {
        TuioObject = tuioObject;
    }

    public abstract void drawOnImage(JLabel image, int x, int y);
}
