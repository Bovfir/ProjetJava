package modelPackage;

public class MeansOfPayment {
    private Integer id,cardNumber,typeOfPayment, registrationNumber;
    private String wording,information,paypalAccount;
    private double amount;

    public MeansOfPayment(Integer id, String wording, String information,Integer typeOfPayment, Integer registrationNUmber) {
        this.id = id;
        this.wording = wording;
        this.information = information;
        this.typeOfPayment = typeOfPayment;
        this.registrationNumber = registrationNUmber;
    }

    @Override
    public String toString() {
        return wording;
    }

    public String getWording() {
        return wording;
    }
    public String getInformation() {
        return information;
    }
    public Integer getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public Integer getCardNumber() {
        return cardNumber;
    }
    public String getPaypalAccount() {
        return paypalAccount;
    }
    public Integer getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setPaypalAccount(String paypalAccount) {
        this.paypalAccount = paypalAccount;
    }
    public void setWording(String wording) {
        this.wording = wording;
    }
}
