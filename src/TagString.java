import javax.swing.*;
import java.awt.*;

public class TagString extends Tag {

    private Font font;
    private String content;

    public TagString(TUIO.TuioObject tuioObject,String content) {
        super(tuioObject);
        this.setContent(content);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void drawOnImage(JLabel image, int x, int y){
        Graphics g = image.getGraphics();
        g.setFont(image.getGraphics().getFont().deriveFont(30f));
        g.drawString(this.content,x,y);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
