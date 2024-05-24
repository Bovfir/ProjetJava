package modelPackage;

public class Bakery {
    private Integer id,locality;
    private String name,streetAndNUmber;

    public Bakery(Integer id, String name, String streetAndNUmber, Integer locality) {
        this.id = id;
        this.name = name;
        this.streetAndNUmber = streetAndNUmber;
        this.locality = locality;
    }
    public Integer getId() {
        return id;
    }
    public Integer getLocality() {
        return locality;
    }
    public String getName() {
        return name;
    }
    public String getStreetAndNUmber() {
        return streetAndNUmber;
    }
}
