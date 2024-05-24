package controllerPackage;

import businessPackage.*;
import modelPackage.*;
import exceptionPackage.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationController {
    private DBManager dbManager;
    private WorkerManager workerManager;
    private ProductManager productManager;
    private OrderManager orderManager;
    private CustomerManager customerManager;
    private CategoryManager categoryManager;
    private BakeryManager bakeryManager;
    private LocalityManager localityManager;
    private LineManager lineManager;
    private MeansOfPaymentManager meansOfPaymentManager;
    private CalculatePrice calculatePriceManager;


    public ApplicationController() throws ConnectionException {
        this.dbManager = new DBManager();
        this.workerManager = new WorkerManager();
        this.productManager = new ProductManager();
        this.orderManager = new OrderManager();
        this.customerManager = new CustomerManager();
        this.categoryManager = new CategoryManager();
        this.bakeryManager = new BakeryManager();
        this.localityManager = new LocalityManager();
        this.lineManager = new LineManager();
        this.meansOfPaymentManager = new MeansOfPaymentManager();
        this.calculatePriceManager = new CalculatePrice();
    }
    public void closeConnection() throws ConnectionException{
        dbManager.closeConnection();
    }
    public ArrayList<Worker> getAllWorkers() throws AllValuesException {
        return workerManager.getAllWorkers();
    }
    public ArrayList<Worker> getAllWorkersByBakeries(int bakeryId) throws AllValuesException{
        return workerManager.getAllWorkersByBakeries(bakeryId);
    }
    public ArrayList<String> getAllEmails() throws AllValuesException{
        return workerManager.getAllEmails();
    }
    public void addWorker (Worker worker) throws AddValueException{
        workerManager.addWorker(worker);
    }
    public void updateWorker(Worker worker) throws UpdateValueException{
        workerManager.updateWorker(worker);
    }
    public void deleteWorker(int registrationNumber) throws DeleteValueException{
        workerManager.deleteWorker(registrationNumber);
    }
    public ArrayList<Product> getProductsByCategoryAndBakery(String category, String bakery) throws AllValuesException{
        return productManager.getProductsByCategoryAndBakery(category,bakery);
    }
    public Integer getMaxNumber(Integer client) throws AllValuesException{
        return orderManager.getMaxNumber(client);
    }
    public Integer getOrderId(Integer number, Integer customer) throws AllValuesException{
        return orderManager.getOrderId(number,customer);
    }
    public void addNewOrder(Order order) throws AddValueException{
        orderManager.addNewOrder(order);
    }
    public ArrayList<OrderResearch> getOrderResearch(Integer customer_id, LocalDate firstDate, LocalDate secondDate) throws AllValuesException{
        return orderManager.getOrderResearch(customer_id,firstDate,secondDate);
    }
    public ArrayList<Customer> getAllCustomers() throws AllValuesException{
        return customerManager.getAllCustomers();
    }
    public ArrayList<Category> getAllCategories() throws AllValuesException{
        return categoryManager.getAllCategories();
    }
    public ArrayList<Category> getCategoryByBakery(String bakeryName) throws AllValuesException{
        return categoryManager.getCategoryByBakery(bakeryName);
    }
    public ArrayList<Bakery> getAllBakeries() throws AllValuesException{
        return bakeryManager.getAllBakeries();
    }
    public ArrayList<Bakery> getBakeriesByLocalities(String locality) throws AllValuesException{
        return bakeryManager.getBakeriesByLocalities(locality);
    }
    public ArrayList<Locality> getAllLocalities() throws AllValuesException{
        return localityManager.getAllLocalities();
    }
    public ArrayList<Locality> getLocalityWithBakery() throws AllValuesException{
        return localityManager.getLocalityWithBakery();
    }
    public ArrayList<WorkersByLocality> getWorkersByLocality(ArrayList<Integer> localities2) throws AllValuesException{
        return localityManager.getWorkersByLocality(localities2);
    }
    public ArrayList<Integer> getAllPostalCodes() throws AllValuesException{
        return localityManager.getAllPostalCodes();
    }
    public void addLine(Line line) throws AddValueException{
        lineManager.addLine(line);
    }
    public ArrayList<LineResearch> getLineByCategory(String category) throws AllValuesException{
        return lineManager.getLineByCategory(category);
    }
    public ArrayList<MeansOfPayment> getMeansOfPaymentsByCustomer(Integer registrationNumber) throws AllValuesException{
        return meansOfPaymentManager.getMeansOfPaymentsByCustomer(registrationNumber);
    }
    public TypeOfPayment getTypeOfPaymentById(Integer id) throws AllValuesException{
        return meansOfPaymentManager.getTypeOfPaymentById(id);
    }
    public void modifyAmount(Double amount, Integer means_of_payment_id) throws UpdateValueException{
        meansOfPaymentManager.modifyAmount(amount, means_of_payment_id);
    }

    public double totalPriceTVA(int quantity, double price,int tva){
        return calculatePriceManager.totalPriceTVA(quantity, price, tva);
    }
    public double totalPriceWTVA(int quantity, double price){
        return calculatePriceManager.totalPriceWTVA(quantity,price);
    }
}
