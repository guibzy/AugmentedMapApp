/***
 * Class the defines and Augmented Information
 */
public class AugmentedInformation {

    /**
     * Possible values : image or text
     */
    private String type;

    /**
     * If type value is "image", you need to give the name of the Image located in ressources/images folder.
     */
    private String content;

    /**
     * Defines the size of the Augmented Information. It is a value between 0 and 1.
     * The size of Projected Information will be define thanks to this percentage and the map's size.
     * Read the drawOnImage method on ProjectionInformation classes to understand the use of this field.
     */
    private double size;

    /**
     * id corresponding to the Reactivision Marker's id
     */
    private int id;

    public AugmentedInformation(String type, String content, int id) {
        this.type = type;
        this.content = content;
        this.id = id;
        this.setSize(0.1);
    }

    public AugmentedInformation(String type, String content, int id, double size) {
        this.type = type;
        this.content = content;
        this.id = id;
        if(size>1){
            this.setSize(0.1);
        }else{
            this.setSize(size);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
