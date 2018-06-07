import TUIO.*;
import javax.swing.*;

public class AugmentedMapApp {

    public static void main(String[] args) {
        // Construct the frame
        JFrame frame = new JFrame();
        frame.setTitle("Augmented Map App");

        // Construct the image
        String path = System.getProperty("user.dir")+"\\ressources\\proto1.png";
        ScaledImageLabel labelImage = new ScaledImageLabel();
        labelImage.setIcon(new ImageIcon(path));

        // Add the label to the frame
        frame.add(labelImage);

        // Configue the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        int port = 3333;

        //exemple
        if (args.length==1) {
            try { port = Integer.parseInt(args[0]); }
            catch (Exception e) { System.out.println("usage: java TuioDump [port]"); }
        } else if (args.length>1) System.out.println("usage: java TuioDump [port]");

        CamListener CamListener = new CamListener(labelImage);
        TuioClient client = new TuioClient(port);

        System.out.println("listening to TUIO messages at port "+port);
        client.addTuioListener(CamListener);

        client.connect();
    }

}
