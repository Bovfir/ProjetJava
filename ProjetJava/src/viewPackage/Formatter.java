package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Formatter {
    private ApplicationController controller;
    public Formatter(){
        try{
            controller = new ApplicationController();
        }catch (ConnectionException exception){
            String message = getMessage(500) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error message",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void closeConnection(){
        try{
            controller.closeConnection();
        }catch (ConnectionException exception){
            String message = getMessage(510) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error message",JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Worker> getAllWorkers(){
        ArrayList<Worker> workers = new ArrayList<>();
        try {
            workers = controller.getAllWorkers();
        }catch (AllValuesException exception){
            String message = getMessage(210) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return workers;
    }

    public ArrayList<Locality> getAllLocalities(){
        ArrayList<Locality> localities = new ArrayList<>();
        try{
            localities = controller.getAllLocalities();
        }catch (AllValuesException exception){
            String message = getMessage(240) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return localities;
    }

    public ArrayList<Bakery> getAllBakeries(){
        ArrayList<Bakery> bakeries = new ArrayList<>();
        try{
            bakeries = controller.getAllBakeries();
        }catch (AllValuesException exception){
            String message = getMessage(280) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return bakeries;
    }

    public ArrayList<Worker> getAllWorkersByBakeries(int id){
        ArrayList<Worker> workersManager = new ArrayList<>();
        try{
            workersManager = controller.getAllWorkersByBakeries(id);
        }catch (AllValuesException exception){
            String message = getMessage(211) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return workersManager;
    }

    public void deleteWorker(int id){
        try{
            controller.deleteWorker(id);
        }catch (DeleteValueException exception){
            String message = getMessage(410) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateWorker(Worker worker){
        try{
            controller.updateWorker(worker);
        }catch (UpdateValueException exception){
            String message = getMessage(310) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addWorker(Worker worker){
        try{
            controller.addWorker(worker);
        }catch (AddValueException exception){
            String message = getMessage(110) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Integer> getAllPostalCodes(){
        ArrayList<Integer> postalCodes = new ArrayList<>();
        try{
            postalCodes = controller.getAllPostalCodes();
        }catch (AllValuesException exception){
            String message = getMessage(243) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return postalCodes;
    }

    public ArrayList<WorkersByLocality> getWorkersByLocality(ArrayList<Integer> list){
        ArrayList<WorkersByLocality> workersByLocalities = new ArrayList<>();
        try{
            workersByLocalities = controller.getWorkersByLocality(list);
        }catch (AllValuesException exception){
            String message = getMessage(242) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return workersByLocalities;
    }

    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        try{
            categories = controller.getAllCategories();
        }catch (AllValuesException exception){
            String message = getMessage(270) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return categories;
    }
    public ArrayList<LineResearch> getLineByCategory(String value){
        ArrayList<LineResearch> lines = new ArrayList<>();
        try {
            lines = controller.getLineByCategory(value);
        }catch (AllValuesException exception){
            String message = getMessage(250) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return lines;
    }

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            customers = controller.getAllCustomers();
        }catch (AllValuesException exception){
            String message = getMessage(260) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return customers;
    }

    public ArrayList<Locality> getLocalityWithBakery(){
        ArrayList<Locality> localities = new ArrayList<>();
        try{
            localities = controller.getLocalityWithBakery();
        }catch (AllValuesException exception){
            String message = getMessage(241) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return localities;
    }

    public ArrayList<Category> getCategoryByBakery(String value){
        ArrayList<Category> categories = new ArrayList<>();
        try{
            categories = controller.getCategoryByBakery(value);
        }catch (AllValuesException exception){
            String message = getMessage(271) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return categories;
    }

    public ArrayList<Bakery> getBakeriesByLocalities(String value){
        ArrayList<Bakery> bakeries = new ArrayList<>();
        try{
            bakeries = controller.getBakeriesByLocalities(value);
        }catch (AllValuesException exception){
            String message = getMessage(281) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return bakeries;
    }

    public double totalPriceWTVA(int quantity, double price){
        double totalPriceWTVA;
        totalPriceWTVA = controller.totalPriceWTVA(quantity,price);
        return totalPriceWTVA;
    }

    public double totalPriceTVA(int quantity, double price,int tva){
        double totalPriceTVA;
        totalPriceTVA = controller.totalPriceTVA(quantity,price,tva);
        return totalPriceTVA;
    }

    public ArrayList<Product> getProductsByCategoryAndBakery(String category, String bakery){
        ArrayList<Product> products = new ArrayList<>();
        try{
            products = controller.getProductsByCategoryAndBakery(category,bakery);
        }catch (AllValuesException exception){
            String message = getMessage(220) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return products;
    }

    public ArrayList<MeansOfPayment> getMeansOfPaymentsByCustomer(Integer id){
        ArrayList<MeansOfPayment> meansOfPayments = new ArrayList<>();
        try{
            meansOfPayments = controller.getMeansOfPaymentsByCustomer(id);
        }catch (AllValuesException exception){
            String message = getMessage(230) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return meansOfPayments;
    }

    public TypeOfPayment getTypeOfPaymentById(Integer id){
        TypeOfPayment type = null;
        try{
            type = controller.getTypeOfPaymentById(id);
        }catch (AllValuesException exception){
            String message = getMessage(231) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return type;
    }

    public void modifyAmount(double amount,Integer id){
        try{
            controller.modifyAmount(amount,id);
        }catch (UpdateValueException exception){
            String message = getMessage(320) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }
    public Integer getMaxNumber(Integer id){
        Integer number = null;
        try{
            number = controller.getMaxNumber(id);
            return number;
        }catch (AllValuesException exception){
            String message = getMessage(221) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return number;
    }

    public void addNewOrder(Order order){
        try{
            controller.addNewOrder(order);
        }catch (AddValueException exception){
            String message = getMessage(120) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer getOrderId(Integer number,Integer customerId){
        Integer orderId = null;
        try{
           orderId = controller.getOrderId(number,customerId);
        }catch (AllValuesException exception){
            String message = getMessage(222) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return orderId;
    }
    public void addLine(Line line){
        try{
            controller.addLine(line);
        }catch (AddValueException exception){
            String message = getMessage(130) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<OrderResearch> getOrderResearch(Integer id, LocalDate date1, LocalDate date2){
        ArrayList<OrderResearch> orderResearches = new ArrayList<>();
        try{
           orderResearches = controller.getOrderResearch(id,date1,date2);
        } catch (AllValuesException exception){
            String message = getMessage(223) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return orderResearches;
    }

    public ArrayList<String> getAllEmails(){
        ArrayList<String> emails = new ArrayList<>();
        try{
            emails = controller.getAllEmails();
        }catch (AllValuesException exception){
            String message = getMessage(212) + exception.getMessage();
            JOptionPane.showMessageDialog(null,message,"Error Message",JOptionPane.ERROR_MESSAGE);
        }
        return emails;
    }

    public String getMessage(int code){
        return "[Code : " + code + "] ";
    }
}
