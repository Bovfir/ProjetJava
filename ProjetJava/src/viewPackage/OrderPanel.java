package viewPackage;

import modelPackage.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class OrderPanel extends JPanel {
    private Formatter formatter;
    private JTextField productPriceTextField, totalPriceTextField, priceWithoutTvaTextField;
    private JComboBox<String> meansOfPaymentComboBox, customerComboBox,localitiesComboBox, bakeriesComboBox, categoriesComboBox, productComboBox;
    private JSpinner dateChoiceSpinner, quantitySpinner;
    private ArrayList<Customer> customers;
    private ArrayList<Locality> localities;
    private ArrayList<Bakery> bakeries;
    private ArrayList<Category> categories;
    private ArrayList<Product> products;
    private ArrayList<MeansOfPayment> meansOfPayments;
    public OrderPanel(Container mainContainer) {
        formatter = new Formatter();
        this.setLayout(new BorderLayout());

        // Left panel
        JPanel orderForm = new JPanel();
        orderForm.setLayout(new GridLayout(7, 2, 5, 5));
        customers = formatter.getAllCustomers();
        DefaultComboBoxModel<String> customerModel = new DefaultComboBoxModel<>();
        customerModel.addElement("<Utilisateur>");
        for (Customer customer : customers) {
            customerModel.addElement(customer.getEmail());
        }

        customerComboBox = new JComboBox<>(customerModel);
        customerComboBox.setMaximumRowCount(5);
        customerComboBox.addActionListener(new CustomerListener());
        customerComboBox.setToolTipText("Choisissez un utilisateur");
        JLabel userLabel = new JLabel("Utilisateur : ");
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(userLabel);
        orderForm.add(customerComboBox);

        localities = formatter.getLocalityWithBakery();
        DefaultComboBoxModel<String> localityModel = new DefaultComboBoxModel<>();
        localityModel.addElement("<Localité>");
        for (Locality locality : localities) {
            localityModel.addElement(locality.getWording() + "-" + locality.getPostalCode());
        }

        localitiesComboBox = new JComboBox<String>(localityModel);
        localitiesComboBox.setMaximumRowCount(6);
        localitiesComboBox.addActionListener(new LocalityListener());
        localitiesComboBox.setToolTipText("Choisissez une localité");
        JLabel localityLabel = new JLabel("Localité : ");
        localityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(localityLabel);
        orderForm.add(localitiesComboBox);

        bakeriesComboBox = new JComboBox<String>();
        bakeriesComboBox.setMaximumRowCount(6);
        bakeriesComboBox.addActionListener(new BakeryListener());
        bakeriesComboBox.setToolTipText("Choisissez une boulangerie");
        bakeriesComboBox.setEnabled(false);
        JLabel bakeryLabel = new JLabel("Boulangerie : ");
        bakeryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(bakeryLabel);
        orderForm.add(bakeriesComboBox);

        categoriesComboBox = new JComboBox<>();
        categoriesComboBox.setMaximumRowCount(6);
        categoriesComboBox.addActionListener(new CategoryListener());
        categoriesComboBox.setToolTipText("Choisissez une catégorie");
        categoriesComboBox.setEnabled(false);
        JLabel categoryLabel = new JLabel("Catégorie : ");
        categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(categoryLabel);
        orderForm.add(categoriesComboBox);

        productComboBox = new JComboBox<>();
        productComboBox.setMaximumRowCount(6);
        productComboBox.addActionListener(new ProductListener());
        productComboBox.setToolTipText("Choisissez un produit");
        productComboBox.setEnabled(false);
        JLabel productLabel = new JLabel("Produit : ");
        productLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(productLabel);
        orderForm.add(productComboBox);


        Calendar calendar = Calendar.getInstance();
        Date minDate = calendar.getTime();
        calendar.set(2040, Calendar.DECEMBER, 31);
        Date maxDate = calendar.getTime();
        Date currentDate = Calendar.getInstance().getTime();
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(currentDate);
        startDate.add(Calendar.DAY_OF_MONTH, 1);

        Date startDateChoice = startDate.getTime();

        dateChoiceSpinner = new JSpinner(new SpinnerDateModel(startDateChoice, minDate, maxDate, Calendar.DAY_OF_MONTH));
        dateChoiceSpinner.setEditor(new JSpinner.DateEditor(dateChoiceSpinner, "dd-MM-yyyy"));
        dateChoiceSpinner.setToolTipText("Choisissez une date");
        JLabel dateChoiceLabel = new JLabel("Date : ");
        dateChoiceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(dateChoiceLabel);
        orderForm.add(dateChoiceSpinner);

        quantitySpinner = new JSpinner();
        quantitySpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        quantitySpinner.setEditor(new JSpinner.NumberEditor(quantitySpinner, "#"));
        quantitySpinner.setToolTipText("Choisissez une quantité");
        quantitySpinner.setEnabled(false);
        quantitySpinner.addChangeListener(new PriceListener());
        JLabel quantityLabel = new JLabel("Quantité : ");
        quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderForm.add(quantityLabel);
        orderForm.add(quantitySpinner);

        // Right panel
        JPanel orderResum = new JPanel();
        orderResum.setLayout(new GridLayout(6, 2, 5, 5));
        orderResum.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("<html><h3>Paiement :</h3></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderResum.add(titleLabel);
        orderResum.add(Box.createRigidArea(null));

        JLabel productPriceLabel = new JLabel("Prix du produit :");
        productPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderResum.add(productPriceLabel);

        productPriceTextField = new JTextField();
        productPriceTextField.setEditable(false);
        productPriceTextField.setToolTipText("Prix du produit HTVA");
        productPriceTextField.setText("0");
        orderResum.add(productPriceTextField);

        JLabel priceWithoutTvaLabel = new JLabel("Prix HTVA :");
        priceWithoutTvaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderResum.add(priceWithoutTvaLabel);

        priceWithoutTvaTextField = new JTextField();
        priceWithoutTvaTextField.setEditable(false);
        priceWithoutTvaTextField.setToolTipText("Prix total HTVA");
        priceWithoutTvaTextField.setText("0");
        orderResum.add(priceWithoutTvaTextField);

        JLabel totalPriceLabel = new JLabel("Prix du total :");
        totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderResum.add(totalPriceLabel);

        totalPriceTextField = new JTextField();
        totalPriceTextField.setEditable(false);
        totalPriceTextField.setToolTipText("Prix total TVAC");
        totalPriceTextField.setText("0");
        orderResum.add(totalPriceTextField);

        JLabel paymentLabel = new JLabel("Moyen de paiement : ");
        paymentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderResum.add(paymentLabel);

        meansOfPaymentComboBox = new JComboBox<>();
        meansOfPaymentComboBox.setMaximumRowCount(5);
        meansOfPaymentComboBox.setEnabled(false);
        meansOfPaymentComboBox.setToolTipText("Choisissez un moyen de paiement");
        orderResum.add(meansOfPaymentComboBox);

        // Adding panels
        this.add(orderForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(mainContainer, this), BorderLayout.SOUTH);
        this.add(orderResum, BorderLayout.EAST);
    }
    private class BakeryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (bakeriesComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir une boulangerie");
                categoriesComboBox.setEnabled(false);
                productComboBox.setEnabled(false);
            } else {
                int index = bakeriesComboBox.getSelectedIndex();
                Bakery bakery = bakeries.get(index - 1);
                categories = formatter.getCategoryByBakery(bakery.getName());
                if (categories.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aucun produit disponnible dans cette boulangerie!!");
                    categoriesComboBox.setEnabled(false);
                    productComboBox.setModel(new DefaultComboBoxModel<>());
                    productComboBox.setEnabled(false);
                } else {
                    DefaultComboBoxModel<String> categoryModel = new DefaultComboBoxModel<>();
                    categoryModel.addElement("<Catégorie>");
                    for (Category category : categories) {
                        categoryModel.addElement(category.getWording() + "-" + category.getId());
                    }
                    categoriesComboBox.setEnabled(true);
                    categoriesComboBox.setModel(categoryModel);
                }
            }
        }
    }
    private class LocalityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = localitiesComboBox.getSelectedIndex();
            if (index == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir une localité");
                bakeriesComboBox.setEnabled(false);
                categoriesComboBox.setEnabled(false);
                productComboBox.setEnabled(false);
            } else {
                Locality locality = localities.get(index - 1);
                bakeries = formatter.getBakeriesByLocalities(locality.getWording());
                DefaultComboBoxModel<String> bakeryModel = new DefaultComboBoxModel<>();
                bakeryModel.addElement("<Boulangerie>");
                if (bakeriesComboBox.isEnabled()) {
                    categoriesComboBox.setEnabled(false);
                    productComboBox.setEnabled(false);
                }
                for (Bakery bakery : bakeries) {
                    bakeryModel.addElement(bakery.getName());
                }
                bakeriesComboBox.setModel(bakeryModel);
                bakeriesComboBox.setEnabled(true);
            }
        }
    }
    private class PriceListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int index = productComboBox.getSelectedIndex();
            Product product = products.get(index - 1);
            double cost, costWTVA;

            costWTVA = formatter.totalPriceWTVA((int) quantitySpinner.getValue(), product.getPrice());
            String patternWTVA = String.format(Locale.US,"%.2f", costWTVA);
            priceWithoutTvaTextField.setText(patternWTVA);

            cost = formatter.totalPriceTVA((int) quantitySpinner.getValue(), product.getPrice(), 21);
            String pattern = String.format(Locale.US,"%.2f", cost);
            productPriceTextField.setText(String.valueOf(product.getPrice()));
            totalPriceTextField.setText(pattern);
        }
    }
    private class CategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int indexCategory = categoriesComboBox.getSelectedIndex();
            if (indexCategory == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir une catégorie");
                productComboBox.setEnabled(false);
            } else {
                int indexBakery = bakeriesComboBox.getSelectedIndex();
                Category category = categories.get(indexCategory - 1);
                Bakery bakery = bakeries.get(indexBakery - 1);
                products = formatter.getProductsByCategoryAndBakery(category.getWording(), bakery.getName());
                DefaultComboBoxModel<String> productModel = new DefaultComboBoxModel<>();
                productModel.addElement(" <Produit>");
                for (Product product : products) {
                    productModel.addElement(product.getWording());
                }
                productComboBox.setModel(productModel);
                productComboBox.setEnabled(true);
            }
        }
    }
    private class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minValue, maxValue, index;
            double cost, costWTVA;
            index = productComboBox.getSelectedIndex();
            if(index == 0){
                JOptionPane.showMessageDialog(null, "Veuillez choisir un produit");
            }else {
                Product product = products.get(index - 1);
                maxValue = product.getMaxNumber();
                minValue = product.getMinNumber();
                quantitySpinner.setModel(new SpinnerNumberModel(minValue, minValue, maxValue, 1));
                quantitySpinner.setEditor(new JSpinner.NumberEditor(quantitySpinner, "#"));

                costWTVA = formatter.totalPriceWTVA((int) quantitySpinner.getValue(), product.getPrice());
                String patternWTVA = String.format(Locale.US, "%.2f", costWTVA);
                priceWithoutTvaTextField.setText(patternWTVA);

                productPriceTextField.setText(String.valueOf(product.getPrice()));
                cost = formatter.totalPriceTVA((int) quantitySpinner.getValue(), product.getPrice(), 21);
                String pattern = String.format(Locale.US, "%.2f", cost);
                totalPriceTextField.setText(pattern);
                quantitySpinner.setEnabled(true);
                quantitySpinner.revalidate();
                quantitySpinner.repaint();
            }
        }
    }
    private class CustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (customerComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir un utilisateur");
            } else {
                int index = customerComboBox.getSelectedIndex();
                Customer customer = customers.get(index - 1);
                meansOfPayments = formatter.getMeansOfPaymentsByCustomer(customer.getRegistrationNumber());

                DefaultComboBoxModel<String> meansOfPaymentModel = new DefaultComboBoxModel<>();
                meansOfPaymentModel.addElement("--Moyen de paiement--");
                for (MeansOfPayment meansOfPayment : meansOfPayments) {
                    meansOfPaymentModel.addElement(meansOfPayment.getWording());
                }
                meansOfPaymentComboBox.setModel(meansOfPaymentModel);
                meansOfPaymentComboBox.setEnabled(true);
            }
        }
    }
    public Customer getSelectedCustomer(){
        int index = customerComboBox.getSelectedIndex();
        return customers.get(index - 1 );
    }
    public Product getSelectedProduct(){
        int index = productComboBox.getSelectedIndex();
        return products.get(index - 1);
    }
    public MeansOfPayment getSelectedMeansOfPayment(){
        int index = meansOfPaymentComboBox.getSelectedIndex();
        return meansOfPayments.get(index - 1);
    }
    public int getQuantity(){
        return (int) quantitySpinner.getValue();
    }
    public Date getDate(){
        return (Date) dateChoiceSpinner.getValue();
    }
    public int getIndexOfCustomer(){
        return customerComboBox.getSelectedIndex();
    }
    public int getIndexOfProduct(){
        return productComboBox.getSelectedIndex();
    }
    public int getIndexOfPayment(){
        return meansOfPaymentComboBox.getSelectedIndex();
    }
    public Boolean isProductEnabled(){
        return productComboBox.isEnabled();
    }
    public Boolean isPaymentEnabled(){
        return meansOfPaymentComboBox.isEnabled();
    }
    public double getTotalPrice(){
        return Double.parseDouble(totalPriceTextField.getText());
    }
}