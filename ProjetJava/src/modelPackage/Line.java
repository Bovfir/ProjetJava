package modelPackage;

public class Line {
    private Integer id, quantity,order, product;
    private double price;

    public Line(Integer id, Integer quantity, Integer order, Integer product, double price) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
        this.price = price;
    }
    public Line(Integer quantity, Integer order, Integer product, double price){
        this.quantity = quantity;
        this.order = order;
        this.product = product;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public Integer getOrder() {
        return order;
    }
    public Integer getProduct() {
        return product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
