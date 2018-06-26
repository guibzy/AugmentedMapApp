import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 * Class to use when you want to project String information
 */
public class ProjectedInformationString extends ProjectedInformation {

    private Font font;

    public ProjectedInformationString(TUIO.TuioObject tuioObject, ScaledImageLabel map, AugmentedInformation augmentedInformation) {
        super(tuioObject, map, augmentedInformation);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void drawOnImage(JLabel image, int x, int y){
        Graphics2D g =(Graphics2D) image.getGraphics();

        //Rotation
        double angle = this.getTuioObject().getAngleDegrees();

        // Defines the font
        // TODO : Permits to choose the size and Font to use
        g.setFont(image.getGraphics().getFont().deriveFont(30f));

        String text = getAugmentedInformation().getContent();

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
