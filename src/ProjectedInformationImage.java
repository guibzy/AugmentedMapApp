import javax.swing.*;
import java.awt.*;

/**
 * Class to use when you want to project Image information
 */
public class ProjectedInformationImage extends ProjectedInformation {

    public ProjectedInformationImage(TUIO.TuioObject tuioObject, ScaledImageLabel map, AugmentedInformation augmentedInformation) {
        super(tuioObject, map, augmentedInformation);
    }

    @Override
    public void drawOnImage(JLabel image, int x, int y) {

        ImageIcon ImageIconFromAI = new ImageIcon(System.getProperty("user.dir")+ AugmentedMapApp.DIRECTORY_SEPARATOR+"ressources"+ AugmentedMapApp.DIRECTORY_SEPARATOR+"images"+ AugmentedMapApp.DIRECTORY_SEPARATOR+ getAugmentedInformation().getContent());
        Image ImageFromAI = ImageIconFromAI.getImage();
        int ImageFromAIWidth = ImageFromAI.getWidth(null);
        int ImageFromAIHeight = ImageFromAI.getHeight(null);

        // Define the max width or height in function of the size given
        int maxWidth = (int)(this.getMap().getDrawn_width()* getAugmentedInformation().getSize());
        int maxHeight = (int)(this.getMap().getDrawn_height()* getAugmentedInformation().getSize());

        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        // If the image's dimensions are smaller than the map's ones, then no need to transform the dimensions
        if(ImageFromAIWidth < maxWidth && ImageFromAIHeight < maxHeight){
            x2 = x + ImageFromAIWidth;
            y2 = x + ImageFromAIHeight;
        }else{
            double ratio = 0;
            // Get the biggest difference between max and ImageFromAI to calculate the ratio
            if((ImageFromAIHeight - maxHeight)>(ImageFromAIWidth - maxWidth)){
                ratio = ((double)maxHeight)/((double)ImageFromAIHeight);
            }else{
                ratio = ((double)maxWidth)/((double)ImageFromAIWidth);
            }
            int newWidth = (int)(ImageFromAIWidth*ratio);
            int newHeight = (int)(ImageFromAIHeight*ratio);

            x2 = x+newWidth;
            y2 = y+newHeight;
        }

        Graphics2D g =(Graphics2D) image.getGraphics();

        //Rotation
        double angle = this.getTuioObject().getAngleDegrees();
        int a = x+(x2-x)/2;
        int b = y+(y2-y)/2;
        g.rotate(-(Math.toRadians(angle)),a,b);

        // Drawing
        g.drawImage(ImageFromAI, x, y, x2, y2, 0, 0, ImageFromAIWidth , ImageFromAIHeight, null);
    }
}
