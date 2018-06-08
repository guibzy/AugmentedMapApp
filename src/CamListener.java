import TUIO.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CamListener implements TuioListener {

    private Set<Tag> TagList;
    private ScaledImageLabel Map;
    private HashMap<Integer,MapItem> MapItemList;

    public CamListener(ScaledImageLabel Map, HashMap<Integer,MapItem> MapItemList){
        setTagList(new HashSet<Tag>());
        this.Map = Map;
        setMapItemList(MapItemList);
    }

    public Set<Tag> getTagList() {
        return TagList;
    }

    public void setTagList(Set<Tag> tagList) {
        TagList = tagList;
    }

    public HashMap<Integer,MapItem> getMapItemList() {
        return MapItemList;
    }

    public void setMapItemList(HashMap<Integer,MapItem> mapItemList) {
        MapItemList = mapItemList;
    }

    public void addTuioObject(TuioObject tobj) {
        MapItem MapItem = this.MapItemList.get(tobj.getSymbolID());

        // S'il n'y a pas d'information reconnue pour le tag capturé par la caméra, on ne fait rien
        if(MapItem == null){
            return;
        }

        Tag toto;
        if("text".equals(MapItem.getType())){
            toto = new TagString(tobj,this.Map,MapItem);
        }else if("image".equals(MapItem.getType())){
            toto = new TagImage(tobj,this.Map,MapItem);
        }else{
            return;
        }

        // Ajout du nouveau tag à la liste des tag présents
        getTagList().add(toto);

        // Redissiner les éléments présents dans la liste de tag (y compris le nouveau)
        this.redrawTagList(true);
    }

    public void updateTuioObject(TuioObject tobj) {
        // Redissiner les éléments présents dans la liste de tag
        this.redrawTagList(true);
    }

    public void removeTuioObject(TuioObject tobj) {
        this.TagList.removeIf(Tag_temp -> Tag_temp.getTuioObject().getSymbolID() == tobj.getSymbolID());

        // Redissiner les éléments présents dans la liste de tag (pour ne plus afficher celui qui a été enlevé)
        this.redrawTagList(true);
    }

    public void addTuioCursor(TuioCursor tcur) {
        System.out.println("add cur "+tcur.getCursorID()+" ("+tcur.getSessionID()+") "+tcur.getX()+" "+tcur.getY());
    }

    public void updateTuioCursor(TuioCursor tcur) {
        System.out.println("set cur "+tcur.getCursorID()+" ("+tcur.getSessionID()+") "+tcur.getX()+" "+tcur.getY()+" "+tcur.getMotionSpeed()+" "+tcur.getMotionAccel());
    }

    public void removeTuioCursor(TuioCursor tcur) {
        System.out.println("del cur "+tcur.getCursorID()+" ("+tcur.getSessionID()+")");
    }

    public void addTuioBlob(TuioBlob tblb) {
        System.out.println("add blb "+tblb.getBlobID()+" ("+tblb.getSessionID()+") "+tblb.getX()+" "+tblb.getY()+" "+tblb.getAngle()+" "+tblb.getWidth()+" "+tblb.getHeight()+" "+tblb.getArea());
    }

    public void updateTuioBlob(TuioBlob tblb) {
        System.out.println("set blb "+tblb.getBlobID()+" ("+tblb.getSessionID()+") "+tblb.getX()+" "+tblb.getY()+" "+tblb.getAngle()+" "+tblb.getWidth()+" "+tblb.getHeight()+" "+tblb.getArea()+" "+tblb.getMotionSpeed()+" "+tblb.getRotationSpeed()+" "+tblb.getMotionAccel()+" "+tblb.getRotationAccel());
    }

    public void removeTuioBlob(TuioBlob tblb) {
        System.out.println("del blb "+tblb.getBlobID()+" ("+tblb.getSessionID()+")");
    }

    public void refresh(TuioTime frameTime) {
        System.out.println("frame "+frameTime.getFrameID()+" "+frameTime.getTotalMilliseconds());
    }

    private void redrawTagList(boolean repaintBeforeDraw){
        if(repaintBeforeDraw){
            // Réinitialisation de la carte comme il y a un changement
            this.Map.repaint();
        }

        Iterator<Tag> iter = this.TagList.iterator();

        while (iter.hasNext()) {
            Tag Tag_temp = iter.next();
            Runnable run = () -> {
                Tag_temp.drawOnImage(Map, Map.getX0() + (int) (Tag_temp.getTuioObject().getX() * Map.getDrawn_width()), Map.getY0() + (int) (Tag_temp.getTuioObject().getY() * Map.getDrawn_height()));

            };
            SwingUtilities.invokeLater(run);
        }
    }
}
