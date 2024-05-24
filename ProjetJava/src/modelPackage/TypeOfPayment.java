package modelPackage;

public class TypeOfPayment {
    private Integer id;
    private String wording;

    public TypeOfPayment(Integer id, String wording) {
        this.id = id;
        this.wording = wording;
    }

    public String getWording() {
        return wording;
    }

    public Integer getId() {
        return id;
    }
}
