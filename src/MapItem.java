public class MapItem {
    private String type;
    private String content;
    private double size;
    private int id;

    public MapItem(String type, String content, int id) {
        this.type = type;
        this.content = content;
        this.id = id;
        this.setSize(0.1);
    }

    public MapItem(String type, String content, int id, double size) {
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
