package modelPackage;

public class LoyalityCard {
    private Integer loyalityCardId;
    private Integer pointsNumber;

    public LoyalityCard(Integer loyalityCardId, Integer pointsNumber){
        this.loyalityCardId = loyalityCardId;
        this.pointsNumber = pointsNumber;
    }

    public Integer getLoyalityCardId() {
        return loyalityCardId;
    }

    public Integer getPointsNumber() {
        return pointsNumber;
    }
}
