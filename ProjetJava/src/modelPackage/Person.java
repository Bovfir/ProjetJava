package modelPackage;

import exceptionPackage.*;

import java.time.LocalDate;

public class Person {
    private Integer registrationNumber,locality,phoneNumber;
    private String firstName,lastName,email,address;
    private LocalDate birthday;

    public Person(Integer registrationNumber, String lastName, String firstName, String email, Integer phoneNumber, LocalDate birthday, String address, Integer locality) throws SettorException{
        setRegistrationNumber(registrationNumber);
        setLastName(lastName);
        setFirstName(firstName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setBirthday(birthday);
        this.address = address;
        this.locality = locality;
    }
    public Integer getRegistrationNumber() {
        return registrationNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public String getAddress() {
        return address;
    }
    public Integer getLocality() {
        return locality;
    }
    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public void setFirstName(String firstName) throws SettorException {
        if(firstName == null || containsNumbers(firstName) || firstName.contains(" ")) {
            throw new SettorException(firstName + " est une valeur invalide.");
        }
        this.firstName = firstName;
    }
    public void setLastName(String lastName) throws SettorException{
        if(lastName == null || containsNumbers(lastName)) {
            throw new SettorException(lastName + " est une valeur invalide.");
        }
        this.lastName = lastName;
    }
    public void setPhoneNumber(Integer phoneNumber) throws SettorException {
        if(phoneNumber != null){
            String phoneNumberStr = phoneNumber.toString();
            if(phoneNumberStr.length() < 9){
                throw new SettorException(phoneNumber + " est une valeur invalide.");
            }
            this.phoneNumber = phoneNumber;
        }
        else {
            this.phoneNumber = null;
        }
    }
    public void setEmail(String email) throws SettorException {
        if (!email.contains("@")) {
            throw new SettorException(email + " est une valeur invalide.");
        } else {
            if (!validationEmail(email)) {
                throw  new SettorException(email + " est une valeur invalide.");
            }
            this.email = email;
        }
    }
    public void setBirthday(LocalDate birthday) throws SettorException {
        LocalDate dateNow = LocalDate.now();
        LocalDate date18YearsAgo = dateNow.minusYears(18);
        LocalDate date1900 = LocalDate.of(1900, 1, 1);
        if(birthday.isAfter(date18YearsAgo) || birthday.isBefore(date1900)){
            throw new SettorException(birthday + " est une valeur invalide.");
        }
        this.birthday = birthday;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean containsNumbers(String value) {
        for (char c : value.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    public boolean validationEmail(String email) {
        int atIndex = -1;
        boolean valid = false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                atIndex = i;
            } else if (email.charAt(i) == '.' && atIndex != -1) {
                valid = true;
                break;
            }
        }
        return valid;
    }

}
