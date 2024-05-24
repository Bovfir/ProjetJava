package modelPackage;

public class Status {
    private Integer id;
    private String wording;
    public Status(Integer id, String wording) {
        this.id = id;
        this.wording = wording;
    }
    public Integer getId() {
        return id;
    }
    public String getWording() {
        return wording;
    }
}
