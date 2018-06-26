import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is an extended version of a JLabel which draws its icon image using
 * the ImageDrawer utility. I adapted the code of www.codejava.net
 *
 * source : http://www.codejava.net/java-se/graphics/drawing-an-image-with-automatic-scaling
 *
 */
public class ScaledImageLabel extends JLabel {

    /**
     * Height of the Image drawn
     */
    private int drawn_height;

    /**
     * Width of the Image drawn
     */
    private int drawn_width;

    /**
     * X coordinate of the top left corner origin point of the map
     */
    private int x0;

    /**
     * Y coordinate of the top left corner origin point of the map
     */
    private int y0;

    protected void paintComponent(Graphics g) {
        ImageIcon icon = (ImageIcon) getIcon();
        if (icon != null) {
           this.drawScaledImage(icon.getImage(), this, g);
        }
    }

    /**
     * Draw the image to scale to the parent canvas size
     *
     * @param image
     * @param canvas
     * @param g
     */
    private void drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;

        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }
        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
        setDrawn_height(canvasHeight);
        setDrawn_width(canvasWidth);
        setX0(x1);
        setY0(y1);
    }

    public int getDrawn_height() {
        return drawn_height;
    }

    public void setDrawn_height(int drawn_height) {
        this.drawn_height = drawn_height;
    }

    public int getDrawn_width() {
        return drawn_width;
    }

    public void setDrawn_width(int drawn_width) {
        this.drawn_width = drawn_width;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }
}