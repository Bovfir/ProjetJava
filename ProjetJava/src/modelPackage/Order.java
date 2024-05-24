package modelPackage;

import java.time.LocalDate;

public class Order {
    private Integer orderNumber, invoiceNumber, customer,status,meansOfPayment, number;
    private LocalDate orderDate;


    public Order(Integer orderNumber, Integer invoice, Integer customer, Integer status, Integer meansOfPayment, LocalDate orderDate, Integer number){
        this.invoiceNumber = invoice;
        this.customer = customer;
        this.status = status;
        this.meansOfPayment = meansOfPayment;
        this.number = number;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }
    public Order(Integer customer, Integer status, Integer meansOfPayment, LocalDate orderDate, Integer number){
        this.customer = customer;
        this.status = status;
        this.meansOfPayment = meansOfPayment;
        this.number = number;
        this.orderDate = orderDate;
    }
    public Order(Integer customer,Integer meansOfPayment, LocalDate orderDate, Integer number){
        this.customer = customer;
        this.meansOfPayment = meansOfPayment;
        this.number = number;
        this.orderDate = orderDate;
    }
    public Integer getNumber() {
        return number;
    }
    public Integer getCustomer() {
        return customer;
    }
    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }
    public Integer getMeansOfPayment() {
        return meansOfPayment;
    }
    public Integer getOrderNumber() {
        return orderNumber;
    }
    public Integer getStatus() {
        return status;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
}
