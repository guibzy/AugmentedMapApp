import javax.swing.*;

public class AugmentedMapApp {

    public static void main(String[] args) {
        // Construct the frame
        JFrame frame = new JFrame();
        frame.setTitle("Augmented Map App");

        // Construct the image
        String path = System.getProperty("user.dir")+"\\ressources\\proto1.png";
        JLabel labelImage = new ScaledImageLabel();
        labelImage.setIcon(new ImageIcon(path));

        // Add the label to the frame
        frame.add(labelImage);

        // Configue the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
