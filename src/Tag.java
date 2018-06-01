import TUIO.TuioObject;

public class Tag {

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
}
