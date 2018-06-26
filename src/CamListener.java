import TUIO.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Class that handles the reactivision events captured by the webcam. (adding, update of withdraw of a marker)
 */
public class CamListener implements TuioListener {

    private Set<ProjectedInformation> projectedInformationList;
    private ScaledImageLabel Map;
    private HashMap<Integer, AugmentedInformation> MapItemList;

    public CamListener(ScaledImageLabel Map, HashMap<Integer, AugmentedInformation> MapItemList){
        setProjectedInformationList(new HashSet<ProjectedInformation>());
        this.Map = Map;
        setMapItemList(MapItemList);
    }

    public Set<ProjectedInformation> getProjectedInformationList() {
        return projectedInformationList;
    }

    public void setProjectedInformationList(Set<ProjectedInformation> projectedInformationList) {
        this.projectedInformationList = projectedInformationList;
    }

    public HashMap<Integer, AugmentedInformation> getMapItemList() {
        return MapItemList;
    }

    public void setMapItemList(HashMap<Integer, AugmentedInformation> mapItemList) {
        MapItemList = mapItemList;
    }

    public void addTuioObject(TuioObject tobj) {
        AugmentedInformation AugmentedInformation = this.MapItemList.get(tobj.getSymbolID());

        // If no information recognized for the captured marker, no need to project something
        if(AugmentedInformation == null){
            return;
        }

        ProjectedInformation ProjectedInformation;
        if("text".equals(AugmentedInformation.getType())){
            ProjectedInformation = new ProjectedInformationString(tobj,this.Map, AugmentedInformation);
        }else if("image".equals(AugmentedInformation.getType())){
            ProjectedInformation = new ProjectedInformationImage(tobj,this.Map, AugmentedInformation);
        }else{
            return;
        }

        // Adding of the new ProjectedInformation to the list of Projected information currently projected
        getProjectedInformationList().add(ProjectedInformation);

        this.redrawAugmentedInformationList(true);
    }

    public void updateTuioObject(TuioObject tobj) {
        this.redrawAugmentedInformationList(true);
    }

    public void removeTuioObject(TuioObject tobj) {
        this.projectedInformationList.removeIf(projectedInformation_temp -> projectedInformation_temp.getTuioObject().getSymbolID() == tobj.getSymbolID());

        this.redrawAugmentedInformationList(true);
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

    /**
     * Redraw te list of Augmented Informations
     *
     * @param resetBeforeDraw If true, the map will be reset before drawing the Augmented Informations
     */
    private void redrawAugmentedInformationList(boolean resetBeforeDraw){
        if(resetBeforeDraw){
            // Reset the map is necessary
            this.Map.repaint();
        }

        Iterator<ProjectedInformation> iter = this.projectedInformationList.iterator();

        while (iter.hasNext()) {
            ProjectedInformation projectedInformation_temp = iter.next();
            Runnable run = () -> {
                projectedInformation_temp.drawOnImage(Map, Map.getX0() + (int) (projectedInformation_temp.getTuioObject().getX() * Map.getDrawn_width()), Map.getY0() + (int) (projectedInformation_temp.getTuioObject().getY() * Map.getDrawn_height()));

            };
            SwingUtilities.invokeLater(run);
        }
    }
}
