package businessPackage;

public  class  CalculatePrice {

    public double totalPriceTVA(int quantity, double price,int tva){
        return quantity * price * (1+(tva/100.));
    }
    public double totalPriceWTVA(int quantity, double price){
        return quantity * price;
    }
}
