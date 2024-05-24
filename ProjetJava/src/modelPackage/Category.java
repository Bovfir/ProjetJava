package modelPackage;

public class Category {
    private Integer id;
    private String wording,information;

    public Category(Integer id, String wording, String information) {
        this.id = id;
        this.wording = wording;
        this.information = information;
    }

    public String getWording() {
        return wording;
    }
    public int getId() {
        return id;
    }
    public String getInformation() {
        return information;
    }
}
