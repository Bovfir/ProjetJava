package modelPackage;

import java.time.LocalDate;

public class OrderResearch {
    private Integer orderNumber;
    private String statusWording,paymentWording;
    private LocalDate orderDate;

    public OrderResearch(Integer orderNumber,String statusWording, LocalDate orderDate,String paymentWording){
        this.orderDate = orderDate;
        this.statusWording = statusWording;
        this.orderNumber = orderNumber;
        this.paymentWording = paymentWording;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
    public Integer getOrderNumber() {
        return orderNumber;
    }
    public String getStatus() {
        return statusWording;
    }
    public String getPaymentWording() {
        return paymentWording;
    }
}
