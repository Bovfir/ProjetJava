package modelPackage;

public class Locality {
    private Integer id,postalCode;
    private String wording;

    public Locality(Integer id, Integer postalCode, String wording) {
        this.id = id;
        this.postalCode = postalCode;
        this.wording = wording;
    }
    public Integer getId() {
        return id;
    }
    public Integer getPostalCode() {
        return postalCode;
    }
    public String getWording() {
        return wording;
    }
}
