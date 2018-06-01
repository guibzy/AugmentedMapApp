import TUIO.*;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Set;

public class CamListener implements TuioListener {

    private Set<TagString> TagList;
    private JLabel Map;

    public CamListener(JLabel Map){
        setTagList(new HashSet<TagString>());
        this.Map = Map;
    }

    public void addTuioObject(TuioObject tobj) {
        System.out.println("add obj "+tobj.getSymbolID()+" ("+tobj.getSessionID()+") "+tobj.getX()+" "+tobj.getY()+" "+tobj.getAngle());
        TagString toto = new TagString(tobj,"Toto "+tobj.getSymbolID());
        getTagList().add(toto);
        this.Map.repaint();
        // Draw la liste
        /*for (int i = 0; i < TagList.size(); i++) {
            TagString titi = TagList.get(i);
            System.out.println("DEBUT BOUCLE : "+titi.getTuioObject().getSymbolID());
            titi.drawOnImage(Map,100+titi.getTuioObject().getSymbolID()*100,100+titi.getTuioObject().getSymbolID()*100);
        }*/
        Iterator<TagString> crunchifyIterator = TagList.iterator();
        while (crunchifyIterator.hasNext()) {
            //System.out.println(crunchifyIterator.next());
            TagString titi = crunchifyIterator.next();
            System.out.println("DEBUT BOUCLE : "+titi.getTuioObject().getSymbolID());
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    titi.drawOnImage(Map,100+titi.getTuioObject().getSymbolID()*100,100+titi.getTuioObject().getSymbolID()*100);
                }
            };
            SwingUtilities.invokeLater(run);
        }
        //set

    }

    public void updateTuioObject(TuioObject tobj) {
        //System.out.println("set obj "+tobj.getSymbolID()+" ("+tobj.getSessionID()+") "+tobj.getX()+" "+tobj.getY()+" "+tobj.getAngle()+" "+tobj.getMotionSpeed()+" "+tobj.getRotationSpeed()+" "+tobj.getMotionAccel()+" "+tobj.getRotationAccel());
    }

    public void removeTuioObject(TuioObject tobj) {
        System.out.println("del obj "+tobj.getSymbolID()+" ("+tobj.getSessionID()+")");
        System.out.println("Taille de la liste : "+TagList.size());
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

    public Set<TagString> getTagList() {
        return TagList;
    }

    public void setTagList(Set<TagString> tagList) {
        TagList = tagList;
    }
}
