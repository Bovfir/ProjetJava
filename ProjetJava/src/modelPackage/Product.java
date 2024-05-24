package modelPackage;

import java.time.LocalDate;

public class Product {
    private Integer productCode,minNumber,maxNumber,categoryId,bakeryId,delay;
    private String wording;
    private double price;
    private LocalDate sellingDate;
    private Boolean mustBePrepaid, isAvaible;


    public Product(Integer productCode, String wording, Double price,LocalDate sellingDate,Boolean mustBePrepaid, Boolean isAvaible, Integer categoryId, Integer bakeryId,Integer minNumber, Integer maxNumber) {
        this.productCode = productCode;
        this.wording = wording;
        this.price = price;
        this.sellingDate = sellingDate;
        this.mustBePrepaid = mustBePrepaid;
        this.isAvaible = isAvaible;
        this.categoryId = categoryId;
        this.bakeryId = bakeryId;
        setMinNumber(minNumber);
        setMaxNumber(maxNumber);
    }

    @Override
    public String toString() {
        return wording;
    }

    public String getWording() {
        return wording;
    }

    public Boolean getAvaible() {
        return isAvaible;
    }

    public Boolean getMustBePrepaid() {
        return mustBePrepaid;
    }
    public double getPrice() {
        return price;
    }

    public Integer getDelay() {
        return delay;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public Integer getMinNumber() {
        return minNumber;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public void setMinNumber(Integer minNumber) {
        if(minNumber < 1){
            this.minNumber = 1;
        }
        else {
            this.minNumber = minNumber;
        }
    }
    public void setMaxNumber(Integer maxNumber) {
        if(maxNumber < 1){
            this.maxNumber = 1;
        }
        else {
            this.maxNumber = maxNumber;
        }
    }
}
