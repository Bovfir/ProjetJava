package modelPackage;

import java.time.LocalDate;
import exceptionPackage.*;


public class Worker extends Person{
    private Boolean isAdmin;
    private Integer managerId,bakery;

    public Worker(Integer registrationNumber, String lastName, String firstName, String email, Integer phoneNumber, LocalDate birthday, String address, Integer locality,
                  Boolean isAdmin, Integer managerId, Integer bakery) throws SettorException{
        super(registrationNumber, lastName, firstName, email, phoneNumber, birthday, address, locality);
        setAdmin(isAdmin);
        setManagerId(managerId);
        setBakery(bakery);
    }
    public Worker(Integer registrationNumber, String lastName, String firstName, String email, LocalDate birthday, Integer locality,
                  Boolean isAdmin, Integer bakery) throws SettorException {
        this(registrationNumber,lastName,firstName,email,null,birthday,null,locality,isAdmin,null,bakery);
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
    public Integer getManagerId() {
        return managerId;
    }
    public Integer getBakery() {
        return bakery;
    }
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
    public void setBakery(Integer bakery) {
        this.bakery = bakery;
    }
}
