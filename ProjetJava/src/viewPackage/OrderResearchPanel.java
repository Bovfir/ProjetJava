package viewPackage;

import modelPackage.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderResearchPanel extends JPanel {
    private JTable result;
    private JSpinner orderDateMin;
    private JSpinner orderDateMax;
    private JComboBox customer;
    private ArrayList<Customer> customers;
    public OrderResearchPanel(Container mainContainer) {
        this.setLayout(new BorderLayout());
        Formatter formatter = new Formatter();
        // Choice panel
        JPanel orderResearchFrom = new JPanel();
        orderResearchFrom.setLayout(new GridLayout(2, 4, 5, 5));
        customers = formatter.getAllCustomers();

        JLabel customerLabel = new JLabel("Client : ");
        customerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderResearchFrom.add(Box.createRigidArea(null));
        orderResearchFrom.add(customerLabel);

        DefaultComboBoxModel<String> customerModel = new DefaultComboBoxModel<>();
        customerModel.addElement("Selectionner un client");
        for (Customer customer : customers) {
            customerModel.addElement(customer.getEmail());
        }
        customer = new JComboBox<>(customerModel);
        orderResearchFrom.add(customer);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        Date minDate = calendar.getTime();
        calendar.set(2100, Calendar.DECEMBER, 31);
        Date maxDate = calendar.getTime();

        JLabel orderDateMinLabel = new JLabel("Date de Début: ");
        orderDateMinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderResearchFrom.add(Box.createRigidArea(null));
        orderResearchFrom.add(orderDateMinLabel);

        orderDateMin = new JSpinner(new SpinnerDateModel(new Date(), minDate, maxDate, Calendar.DAY_OF_MONTH));
        orderDateMin.setEditor(new JSpinner.DateEditor(orderDateMin, "dd-MM-yyyy"));
        orderResearchFrom.add(orderDateMin);

        JLabel orderDateMaxLabel = new JLabel("Date de Fin : ");
        orderDateMaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderResearchFrom.add(orderDateMaxLabel);

        orderDateMax = new JSpinner(new SpinnerDateModel(new Date(), minDate, maxDate, Calendar.DAY_OF_MONTH));
        orderDateMax.setEditor(new JSpinner.DateEditor(orderDateMax, "dd-MM-yyyy"));
        orderResearchFrom.add(orderDateMax);

        // Result table
        result = new JTable();
        result.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(result);

        // adding elements
        this.add(orderResearchFrom, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(new ButtonsPanel(mainContainer, this), BorderLayout.SOUTH);
    }
    public Customer getSelectedCustomer(){
        return customers.get(customer.getSelectedIndex()-1);
    }
    public LocalDate getOrderDateMin(){
        return ((Date)orderDateMin.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public LocalDate getOrderDateMax(){
        return ((Date)orderDateMax.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public JTable getResult(){
        return result;
    }
    public int getIndexOfSelectedCustomer(){
        return customer.getSelectedIndex();
    }
}