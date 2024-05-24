package modelPackage;

import java.time.LocalDate;

public class LineResearch {
    private LocalDate orderDate;
    private Integer quantity;
    private String wording, bakery;

    public LineResearch(LocalDate orderDate, Integer quantity, String wording, String bakery){
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.wording = wording;
        this.bakery = bakery;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public String getWording() {
        return wording;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getBakery() {
        return bakery;
    }
    public void setWording(String wording) {
        this.wording = wording;
    }
    public void setBakery(String bakery) {
        this.bakery = bakery;
    }
}
