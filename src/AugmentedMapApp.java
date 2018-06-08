import TUIO.*;
import javax.swing.*;
import java.util.HashMap;

public class AugmentedMapApp {

    public static String DIRECTORY_SEPARATOR ;

    public static void main(String[] args) {

        // Define de Directory separator given the current os running the app
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            DIRECTORY_SEPARATOR = "\\";

        } else {
            DIRECTORY_SEPARATOR = "/";
        }

        // Construct the frame
        JFrame frame = new JFrame();
        frame.setTitle("Augmented Map App");

        // Construct the image
        String path = System.getProperty("user.dir")+DIRECTORY_SEPARATOR+"ressources"+DIRECTORY_SEPARATOR+"maps"+DIRECTORY_SEPARATOR+"proto1.png";
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

        CamListener CamListener = new CamListener(labelImage,fetchMapItemList(1));
        TuioClient client = new TuioClient(port);

        client.addTuioListener(CamListener);

        client.connect();
    }

    public static HashMap<Integer,MapItem> fetchMapItemList(int MapId){
        HashMap<Integer,MapItem> MapItemList = new HashMap<Integer,MapItem>();

        // TODO : Récupérer les items en fonction d'une liste en base de données, API...
        // Pour notre prototype, on simule des données
        if(MapId == 1){
            MapItemList.put(0,new MapItem("text","Mer",0));
            MapItemList.put(1,new MapItem("image","Black.png",1));
            MapItemList.put(2,new MapItem("image","sims.png",2,0.17));
            MapItemList.put(3,new MapItem("text","Colline Est",3));
        }

        if(MapId == 2){
            MapItemList.put(0,new MapItem("text","UTT",0));
            MapItemList.put(1,new MapItem("image","Black.png",1));
            MapItemList.put(2,new MapItem("image","sims.png",2,0.17));
            MapItemList.put(3,new MapItem("text","Amphithéatre M104",3));
        }

        return MapItemList;
    }

}
