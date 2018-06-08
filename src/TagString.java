import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class TagString extends Tag {

    private Font font;

    public TagString(TUIO.TuioObject tuioObject, ScaledImageLabel map, MapItem mapItem) {
        super(tuioObject, map, mapItem);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void drawOnImage(JLabel image, int x, int y){
        // Récupération du graphic du calque de l'image pour le modifier
        Graphics2D g =(Graphics2D) image.getGraphics();

        //Rotation
        double angle = this.getTuioObject().getAngleDegrees();

        // Définition de la police
        // TODO : permettre la personnlisation de la taille et du choix de police
        g.setFont(image.getGraphics().getFont().deriveFont(30f));

        String text = getMapItem().getContent();

        // Predict the width and height of the text that will be drawn
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = g.getFont();
        int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
        int textheight = (int)(font.getStringBounds(text, frc).getHeight());

        // Rotation
        g.rotate(-(Math.toRadians(angle)),x+textwidth/2,y+textheight/2);

        // Drawing
        g.drawString(text,x,y);
    }
}
