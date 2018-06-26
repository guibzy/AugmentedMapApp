import TUIO.*;
import javax.swing.*;
import java.util.HashMap;

public class AugmentedMapApp {

    public static String DIRECTORY_SEPARATOR ;

    public static void main(String[] args) {

        // Defines de Directory separator given the current os running the app
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            DIRECTORY_SEPARATOR = "\\";

        } else {
            DIRECTORY_SEPARATOR = "/";
        }

        // Construct the frame
        JFrame frame = new JFrame();
        frame.setTitle("Augmented Map App");

        // Construct the map
        String nameMap = "proto1.png";
        String path = System.getProperty("user.dir")+DIRECTORY_SEPARATOR+"ressources"+DIRECTORY_SEPARATOR+"maps"+DIRECTORY_SEPARATOR+nameMap;
        ScaledImageLabel labelImage = new ScaledImageLabel();
        labelImage.setIcon(new ImageIcon(path));

        // Add the label to the frame
        frame.add(labelImage);

        // Configure the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        int port = 3333;

        //exemple
        if (args.length==1) {
            try { port = Integer.parseInt(args[0]); }
            catch (Exception e) { System.out.println("usage: java TuioDump [port]"); }
        } else if (args.length>1) System.out.println("usage: java TuioDump [port]");

        // Define the CamListener
        CamListener CamListener = new CamListener(labelImage, fetchAugmentedInformationList(1));
        TuioClient client = new TuioClient(port);

        client.addTuioListener(CamListener);

        client.connect();
    }

    /**
     * Given the id of the Map, fetch the list of Augmented Information to add
     * @param MapId
     * @return
     */
    public static HashMap<Integer, AugmentedInformation> fetchAugmentedInformationList(int MapId){
        HashMap<Integer, AugmentedInformation> AugmentedInformationList = new HashMap<Integer, AugmentedInformation>();

        // TODO : Fetch the Augmented Information using a DB, API o whatever you want, and build it life the following exemple
        if(MapId == 1){
            AugmentedInformationList.put(0,new AugmentedInformation("text","Eau",0));
            AugmentedInformationList.put(1,new AugmentedInformation("image","Black.png",1));
            AugmentedInformationList.put(2,new AugmentedInformation("image","sims.png",2,0.17));
            AugmentedInformationList.put(3,new AugmentedInformation("image","smiley_smile2.png",3));
        }

        if(MapId == 2){
            AugmentedInformationList.put(0,new AugmentedInformation("text","UTT",0));
            AugmentedInformationList.put(1,new AugmentedInformation("image","Black.png",1));
            AugmentedInformationList.put(2,new AugmentedInformation("image","sims.png",2,0.17));
            AugmentedInformationList.put(3,new AugmentedInformation("text","Amphithéatre M104",3));
        }

        return AugmentedInformationList;
    }

}
