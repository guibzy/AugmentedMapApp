import javax.swing.*;
import java.awt.*;

public class TagImage extends Tag {

    public TagImage(TUIO.TuioObject tuioObject, ScaledImageLabel map, MapItem mapItem) {
        super(tuioObject, map, mapItem);
    }

    @Override
    public void drawOnImage(JLabel image, int x, int y) {

        ImageIcon ImageIconFromTag = new ImageIcon(System.getProperty("user.dir")+ AugmentedMapApp.DIRECTORY_SEPARATOR+"ressources"+ AugmentedMapApp.DIRECTORY_SEPARATOR+"tags"+ AugmentedMapApp.DIRECTORY_SEPARATOR+getMapItem().getContent());
        Image ImageFromTag = ImageIconFromTag.getImage();
        int ImageFromTagWidth = ImageFromTag.getWidth(null);
        int ImageFromTagHeight = ImageFromTag.getHeight(null);

        // Define the max width or height in funciton of the size given
        int maxWidth = (int)(this.getMap().getDrawn_width()*getMapItem().getSize());
        int maxHeight = (int)(this.getMap().getDrawn_height()*getMapItem().getSize());

        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        // Si la largeur et longueur de l'image à dessiner sont inférieures aux limites définies, pas de traitement à faire
        if(ImageFromTagWidth < maxWidth && ImageFromTagHeight < maxHeight){
            x2 = x + ImageFromTagWidth;
            y2 = x + ImageFromTagHeight;
        }else{
            double ratio = 0;
            // calculer la plus grande difference entre max et ImageFromTag
            if((ImageFromTagHeight - maxHeight)>(ImageFromTagWidth - maxWidth)){
                ratio = ((double)maxHeight)/((double)ImageFromTagHeight);
            }else{
                ratio = ((double)maxWidth)/((double)ImageFromTagWidth);
            }
            int newWidth = (int)(ImageFromTagWidth*ratio);
            int newHeight = (int)(ImageFromTagHeight*ratio);

            x2 = x+newWidth;
            y2 = y+newHeight;
        }

        // Récupération du graphic du calque de l'image pour le modifier
        Graphics2D g =(Graphics2D) image.getGraphics();

        //Rotation
        double angle = this.getTuioObject().getAngleDegrees();
        int a = x+(x2-x)/2;
        int b = y+(y2-y)/2;
        g.rotate(-(Math.toRadians(angle)),a,b);

        // Dessin
        g.drawImage(ImageFromTag, x, y, x2, y2, 0, 0, ImageFromTagWidth , ImageFromTagHeight, null);
    }
}
