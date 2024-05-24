package modelPackage;

import java.time.LocalDate;

import exceptionPackage.*;

public class Customer extends Person {
    private Integer loyalityCard;
    public Customer(Integer registrationNumber, String lastName, String firstName, String email, Integer phoneNumber, LocalDate birthday, String address, Integer locality,Integer loyalityCard) throws SettorException{
        super(registrationNumber,lastName,firstName,email,phoneNumber,birthday,address,locality);
        this.loyalityCard = loyalityCard;
    }

    public Customer(Integer registrationNumber, String lastName, String firstName, String email, LocalDate birthday, Integer locality,Integer loyalityCard) throws SettorException{
        this(registrationNumber,lastName,firstName,email,null,birthday,null,locality,loyalityCard);
    }

    public Integer getLoyalityCard() {
        return loyalityCard;
    }
}
