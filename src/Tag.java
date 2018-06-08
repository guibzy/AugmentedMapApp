import TUIO.TuioObject;

import javax.swing.*;

public abstract class Tag {

    private TuioObject TuioObject;
    private ScaledImageLabel Map;
    private MapItem MapItem;

    public Tag(TUIO.TuioObject tuioObject, ScaledImageLabel map, MapItem mapItem) {
        TuioObject = tuioObject;
        Map = map;
        MapItem = mapItem;
    }

    public MapItem getMapItem() {
        return MapItem;
    }

    public void setMapItem(MapItem mapItem) {
        MapItem = mapItem;
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
