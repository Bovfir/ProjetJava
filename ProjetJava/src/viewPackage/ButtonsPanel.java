package viewPackage;

import modelPackage.*;
import exceptionPackage.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ButtonsPanel extends JPanel {
    private Container mainContainer;
    private JPanel panel;
    private OrderPanel orderPanel;
    private FormPanel formPanel;
    private Border defaultBorder;
    private boolean isUpdate;
    private Formatter formatter;
    private Integer registrationNumber;
    private String oldEmail;
    private OrderResearchPanel orderResearchPanel;
    public ButtonsPanel(Container mainContainer,JPanel panel) {
        this.panel = panel;
        this.mainContainer = mainContainer;
        setLayout(new FlowLayout());
        isUpdate = false;
        formatter = new Formatter();

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(new ChangePanelListener(new WelcomePanel(),mainContainer));

        JButton validationButton = new JButton("Validation");
        validationButton.addActionListener(new ValidationForm());

        JButton resetButton = new JButton("Réinitialisation");
        resetButton.addActionListener(new resetActionForm());

        this.add(backButton);
        this.add(validationButton);
        this.add(resetButton);
    }
    private class resetActionForm implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ActionListener actionListener = null;
            switch (panel.getClass().toString()){
                case "class viewPackage.FormPanel":
                    formPanel = (FormPanel) panel;
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR,-18);
                    Date currentDate = calendar.getTime();

                    defaultBorder = formPanel.getDates().getBorder();
                    resetComponents(formPanel);

                    formPanel.getDates().setValue(currentDate);
                    break;
                case "class viewPackage.OrderPanel":
                    actionListener = new ChangePanelListener(new OrderPanel(mainContainer),mainContainer);
                    break;
                case "class viewPackage.OrderResearchPanel":
                    actionListener = new ChangePanelListener(new OrderResearchPanel(mainContainer),mainContainer);
                    break;

            }
            if(actionListener != null){
                actionListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
            }
        }
    }
    private void resetComponents(Container container) {
        int comboBoxIndex = 0;
        for (Component component : container.getComponents()) {
            switch (component.getClass().getSimpleName()){
                case "JTextField":
                    JTextField textField = (JTextField) component;
                    textField.setText("");
                    textField.setBorder(defaultBorder);
                    break;
                case "JComboBox":
                    JComboBox<?> comboBox = (JComboBox<?>) component;
                    comboBox.setSelectedIndex(0);
                    if(comboBoxIndex == 2){
                        component.setEnabled(false);
                    }
                    comboBoxIndex++;
                    break;
                case "JCheckBox":
                    JCheckBox checkBox = (JCheckBox) component;
                    checkBox.setSelected(false);
                    break;
                case "JRadioButton":
                    JRadioButton radioButton = (JRadioButton) component;
                    if(radioButton.getText().equals("Utilisateur")){
                        radioButton.setSelected(true);
                    }
                    break;
                default:
                    if(!(component instanceof JSpinner) && component instanceof Container){
                        resetComponents((Container) component);
                    }
                    break;
            }
        }
    }
    private class ValidationForm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (panel.getClass().toString()) {
                case "class viewPackage.OrderPanel":
                    orderPanel = (OrderPanel) panel;
                    validationOrder();
                    break;
                case "class viewPackage.FormPanel":
                    formPanel = (FormPanel) panel;
                    defaultBorder = formPanel.getDates().getBorder();
                    validationForm();
                    break;
                case "class viewPackage.OrderResearchPanel":
                    orderResearchPanel = (OrderResearchPanel) panel;
                    validationOrderResearch();
                    break;
            }
        }
        private void validationForm() {
            try {
                String firstName = formPanel.getFirstName().getText();
                String lastName = formPanel.getLastName().getText();
                String email = formPanel.getEmail().getText();
                LocalDate birthday = ((Date) formPanel.getDates().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String phoneNumberText = formPanel.getPhoneNumber().getText();
                Integer phoneNumber = null;
                String address = formPanel.getAddress().getText().isEmpty() ? null : formPanel.getAddress().getText();

                Integer locality_id = formPanel.getSelectedLocality().getId();
                Integer bakery_id = formPanel.getSelectedBakery().getId();
                Integer manager_id = null;
                boolean isAdmin = formPanel.getAdmin().isSelected();

                formPanel.getFirstName().setBorder(defaultBorder);
                formPanel.getLastName().setBorder(defaultBorder);
                formPanel.getEmail().setBorder(defaultBorder);
                formPanel.getDates().setBorder(defaultBorder);
                formPanel.getPhoneNumber().setBorder(defaultBorder);

                StringBuilder errorsMessage = new StringBuilder();

                if (firstName.isEmpty() || containsNumbers(firstName) || firstName.contains(" ")) {
                    String message = "- Veuillez insérer un prénom correct !\n         * Minimun une lettre\n         * Sans aucun chiffre\n         * Sans aucun Espace\n";
                    errorsMessage.append(message);
                    formPanel.getFirstName().setBorder(new LineBorder(Color.RED, 1));
                }

                if (lastName.isEmpty() || containsNumbers(lastName)) {
                    String message = "- Veuillez insérer un nom correct !\n         * Minimun une lettre\n         * Sans aucun chiffre\n";
                    errorsMessage.append(message);
                    formPanel.getLastName().setBorder(new LineBorder(Color.RED, 1));
                }

                if (!email.contains("@")) {
                    String message = "- Veuillez insérer une adresse mail correct !\n         * Doit contenir '@'\n";
                    errorsMessage.append(message);
                    formPanel.getEmail().setBorder(new LineBorder(Color.RED, 1));
                } else {
                    if (!validationEmail(email)) {
                        String message = "- Veuillez insérer une adresse e-mail correcte !\n         * Doit contenir un '.' après le '@'\n";
                        errorsMessage.append(message);
                        formPanel.getEmail().setBorder(new LineBorder(Color.RED, 1));
                    } else {
                        existEmail(email, errorsMessage);
                    }
                }

                if (!phoneNumberText.isEmpty()) {
                    if (phoneNumberText.length() < 9 || containsLetters(phoneNumberText)|| phoneNumberText.length() > 9) {
                        String message = "- Veuillez insérer un numero de GSM correct !\n         * Doit contenir 9 chiffres\n         * Sans aucune lettres\n";
                        errorsMessage.append(message);
                        formPanel.getPhoneNumber().setBorder(new LineBorder(Color.RED, 1));
                    } else {
                        if(phoneNumberText.charAt(0) == '0'){
                            String message = "- Veuillez insérer un numero de GSM correct !\n         * Doit contenir 9 chiffres (Ne commence pas par 0)\n         * Sans aucune lettres\n";
                            errorsMessage.append(message);
                            formPanel.getPhoneNumber().setBorder(new LineBorder(Color.RED, 1));
                        }
                        else {
                            phoneNumber = Integer.parseInt(phoneNumberText);
                        }
                    }
                }

                if(formPanel.getManagerComboBox().isEnabled() && formPanel.getManagerComboBox().getItemCount() > 0){
                    manager_id = formPanel.getSelectedWorkerManager().getRegistrationNumber();
                }

                if (errorsMessage.isEmpty()) {
                    Worker worker = new Worker(registrationNumber, lastName, firstName, email, phoneNumber, birthday, address, locality_id, isAdmin, manager_id, bakery_id);
                    ActionListener actionListener = new ChangePanelListener(new WelcomePanel(),mainContainer);
                    if(isUpdate){
                        formatter.updateWorker(worker);
                        JOptionPane.showMessageDialog(null,"Mise à jour réussi avec succès !","Succes",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        formatter.addWorker(worker);
                        JOptionPane.showMessageDialog(null,"Ajout réussi avec succès !","Succes",JOptionPane.INFORMATION_MESSAGE);
                    }
                    actionListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
                } else {
                    String message = "Impossible de valider le formulaire pour les raisons suivantes : \n";
                    JOptionPane.showMessageDialog(null, message + errorsMessage, "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SettorException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
        private void validationOrder() {
                int indexOfCustomer = orderPanel.getIndexOfCustomer();
                int indexOfProduct = orderPanel.getIndexOfProduct();
                int indexOfPayment = orderPanel.getIndexOfPayment();
                int quantity = orderPanel.getQuantity();
                double totalPrice = orderPanel.getTotalPrice();
                boolean productIsEnabled = orderPanel.isProductEnabled();
                boolean paymentIsEnabled = orderPanel.isPaymentEnabled();

                if (indexOfCustomer == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner un utilisateur", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (indexOfProduct == 0 || !productIsEnabled) {
                        JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (indexOfPayment == 0 || !paymentIsEnabled) {
                            JOptionPane.showMessageDialog(null, "Veuillez selectionner un moyen de paiement", "Error Message", JOptionPane.ERROR_MESSAGE);
                        } else {

                            Customer customer = orderPanel.getSelectedCustomer();
                            Product product = orderPanel.getSelectedProduct();
                            MeansOfPayment payment = orderPanel.getSelectedMeansOfPayment();
                            TypeOfPayment type = formatter.getTypeOfPaymentById(payment.getTypeOfPayment());

                            if (type.getWording().equals("Porte-feuille") && payment.getAmount() < totalPrice) {
                                JOptionPane.showMessageDialog(null, "Montant du porte feuille trop faible!\n Montant: " + payment.getAmount() + "\n Cout total:  " + totalPrice, "Error Message", JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (type.getWording().equals("Porte-feuille")) {
                                    formatter.modifyAmount(payment.getAmount() - totalPrice, payment.getId());
                                }

                                LocalDate localDate = orderPanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                Integer customerRegistrationNumber = customer.getRegistrationNumber();
                                Integer number = formatter.getMaxNumber(customerRegistrationNumber) + 1;

                                Order order = new Order(customer.getRegistrationNumber(), payment.getId(), localDate, number);
                                formatter.addNewOrder(order);
                                Integer orderId = formatter.getOrderId(number, customerRegistrationNumber);

                                Line line = new Line(quantity, orderId, product.getProductCode(), totalPrice);
                                formatter.addLine(line);

                                JOptionPane.showMessageDialog(null, "Commande ajoutée avec succès", "Succes", JOptionPane.INFORMATION_MESSAGE);
                                ActionListener actionListener = new ChangePanelListener(new WelcomePanel(), mainContainer);
                                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                            }

                        }
                    }
                }
        }
        private void validationOrderResearch() {
            JTable result = orderResearchPanel.getResult();
            if (orderResearchPanel.getIndexOfSelectedCustomer() == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner un client", "Error Message", JOptionPane.ERROR_MESSAGE);
                result.setModel(new DefaultTableModel());
            } else {
                Customer customer = orderResearchPanel.getSelectedCustomer();
                LocalDate orderDateMin = orderResearchPanel.getOrderDateMin();
                LocalDate orderDateMax = orderResearchPanel.getOrderDateMax();
                ArrayList<OrderResearch> orderResearches = formatter.getOrderResearch(customer.getRegistrationNumber(), orderDateMin, orderDateMax);
                if (orderDateMin.isAfter(orderDateMax)) {
                    JOptionPane.showMessageDialog(null, "La première date doit être inférieure ou égale à la deuxième date", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (orderResearches.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aucune commande trouvée", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        OrderResearchTableModel model = new OrderResearchTableModel(orderResearches);
                        result.setModel(model);
                    }
                }
            }
        }

        public boolean containsNumbers(String value) {
            for (char c : value.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
            return false;
        }
        public boolean containsLetters(String value) {
            for (char c : value.toCharArray()) {
                if (Character.isLetter(c)) {
                    return true;
                }
            }
            return false;
        }
        public boolean validationEmail(String email) {
            int atIndex = -1;
            boolean valid = false;
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    atIndex = i;
                } else if (email.charAt(i) == '.' && atIndex != -1) {
                    valid = true;
                    break;
                }
            }
            return valid;
        }
        public void existEmail(String email, StringBuilder errorsMessage) {
            ArrayList<String> emails = formatter.getAllEmails();
            for (String emailVerif : emails) {
                // oldEmail car si dans l'update le gars ne change pas d'email, il faut pas avoir d'erreur
                if (email.equals(emailVerif) && !email.equals(oldEmail)) {
                    String message = "- Addresse Email déjà existante\n";
                    errorsMessage.append(message);
                    formPanel.getEmail().setBorder(new LineBorder(Color.RED, 1));
                    break;
                }
            }
        }
    }
    public void setUpdate(boolean update) {
        isUpdate = update;
    }
    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }
}
