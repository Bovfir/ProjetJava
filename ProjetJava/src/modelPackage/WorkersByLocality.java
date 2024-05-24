package modelPackage;

public class WorkersByLocality {
    private String lastName,firstName,bakery,locality;
    private Integer postalCode;

    public WorkersByLocality(String lastName, String firstName, String bakery, String locality, Integer postalCode){
        this.lastName = lastName;
        this.firstName = firstName;
        this.bakery = bakery;
        this.locality = locality;
        this.postalCode = postalCode;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBakery() {
        return bakery;
    }

    public String getLocality() {
        return locality;
    }

    public Integer getPostalCode() {
        return postalCode;
    }
}
