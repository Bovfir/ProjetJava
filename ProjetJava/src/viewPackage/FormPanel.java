package viewPackage;

import modelPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FormPanel extends JPanel {
    private Formatter formatter;
    private JTextField firstName, lastName, email,address,phoneNumber;
    private JSpinner dates;
    private JCheckBox subordinate;
    private JComboBox managerComboBox,localityComboBox,bakeryComboBox;
    private JRadioButton admin, user;
    private ArrayList<Locality> localities;
    private ArrayList<Bakery> bakeries;
    private ArrayList<Worker> workersManager;

    public FormPanel(){
            formatter = new Formatter();
            this.setLayout(new GridLayout(11, 2, 5, 5));

            JLabel firstNameLabel = new JLabel("Prénom * : ");
            firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            firstNameLabel.setToolTipText("Champ Obligatoire");
            this.add(firstNameLabel);
            firstName = new JTextField(10);
            firstName.setToolTipText("Entrez votre prénom");
            this.add(firstName);

            JLabel lastNameLabel = new JLabel("Nom * : ");
            lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            lastNameLabel.setToolTipText("Champ Obligatoire");
            this.add(lastNameLabel);
            lastName = new JTextField(10);
            lastName.setToolTipText("Entrez votre nom");
            this.add(lastName);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -18);
            Date maxDate = calendar.getTime();

            calendar.set(1900, Calendar.JANUARY, 1);
            Date minDate = calendar.getTime();

            JLabel birthdayLabel = new JLabel("Date de naissance * : ");
            birthdayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            birthdayLabel.setToolTipText("Champ Obligatoire");
            this.add(birthdayLabel);

            dates = new JSpinner(new SpinnerDateModel(maxDate, minDate, maxDate, Calendar.DAY_OF_MONTH));
            dates.setEditor(new JSpinner.DateEditor(dates, "dd-MM-yyyy"));
            this.add(dates);

            JLabel emailLabel = new JLabel("Adresse Email * : ");
            emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            emailLabel.setToolTipText("Champ Obligatoire");
            this.add(emailLabel);
            email = new JTextField(10);
            email.setToolTipText("Entrez votre adresse mail");
            this.add(email);

            JLabel phoneNumberLabel = new JLabel("Numéro de téléphone : ");
            phoneNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(phoneNumberLabel);
            JPanel phoneNumberPanel = new JPanel();
            phoneNumberPanel.setLayout(new BorderLayout());
            String[] countriesValues = {
                    "<html><body><img src='" + getClass().getResource("/imagesPackage/belgique.png") + "' width='15' height='15'> +32 (BEL)</body></html>",
                    "<html><body><img src='" + getClass().getResource("/imagesPackage/france.png") + "' width='15' height='15'> +33 (FRA)</body></html>",
                    "<html><body><img src='" + getClass().getResource("/imagesPackage/PaysBas.png") + "' width='15' height='15'> +31 (PB)</body></html>",
                    "<html><body><img src='" + getClass().getResource("/imagesPackage/allemagne.png") + "' width='15' height='15'> +49 (ALL)</body></html>",
                    "<html><body><img src='" + getClass().getResource("/imagesPackage/luxembourg.png") + "' width='15' height='15'> +352 (LUX)</body></html>",
            };
            JComboBox<String> countriesComboBox = new JComboBox<>(countriesValues);
            phoneNumberPanel.add(countriesComboBox,BorderLayout.WEST);
            phoneNumber = new JTextField(10);
            phoneNumber.setToolTipText("Entrez votre numéro de téléphone");
            phoneNumberPanel.add(phoneNumber,BorderLayout.CENTER);
            this.add(phoneNumberPanel);

            JLabel addressLabel = new JLabel("Adresse : ");
            addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(addressLabel);
            address = new JTextField(10);
            address.setToolTipText("Entrez votre adresse");
            this.add(address);

            JLabel localityLabel = new JLabel("Localité * : ");
            localityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            localityLabel.setToolTipText("Champ Obligatoire");
            this.add(localityLabel);

            localities = formatter.getAllLocalities();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (Locality locality : localities) {
                model.addElement(locality.getWording() + " (" + locality.getPostalCode() + ")");
            }
            localityComboBox = new JComboBox<>(model);
            this.add(localityComboBox);

            JLabel bakeryLabel = new JLabel("Boulangerie : ");
            bakeryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            bakeryLabel.setToolTipText("Champ Obligatoire");
            this.add(bakeryLabel);

            bakeries = formatter.getAllBakeries();
            DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
            for(Bakery bakery : bakeries){
                model1.addElement(bakery.getName() + " (Id : " + bakery.getId() + ")");
            }
            bakeryComboBox = new JComboBox<>(model1);
            bakeryComboBox.setMaximumRowCount(4);
            bakeryComboBox.addActionListener(new BakeryListener());
            this.add(bakeryComboBox);

            JLabel functionLabel = new JLabel("Fonction : ");
            functionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(functionLabel);

            subordinate = new JCheckBox("Subordonné");
            subordinate.addItemListener(event -> managerComboBox.setEnabled(!managerComboBox.isEnabled()));
            subordinate.setToolTipText("Cochez si vous êtes un subordonné");
            this.add(subordinate);

            JLabel idManagerLabel = new JLabel("Id du Manager : ");
            idManagerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(idManagerLabel);

            workersManager = formatter.getAllWorkersByBakeries(getSelectedBakery().getId());
            DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>();
            for(Worker worker : workersManager ){
                model3.addElement(worker.getFirstName() + " " + worker.getLastName() + " (Id : " + worker.getRegistrationNumber() + ")");
            }
            managerComboBox = new JComboBox<>(model3);
            managerComboBox.setMaximumRowCount(3);
            managerComboBox.setEnabled(false);
            managerComboBox.setToolTipText("Cliquez sur votre manager");
            this.add(managerComboBox);

            admin = new JRadioButton("Administrateur");
            admin.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(admin);

            user = new JRadioButton("Utilisateur");
            user.setSelected(true);
            this.add(user);

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(admin);
            buttonGroup.add(user);
    }
    private class BakeryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int bak = getSelectedBakery().getId();
            workersManager = formatter.getAllWorkersByBakeries(bak);
            DefaultComboBoxModel<String> model1 = (DefaultComboBoxModel<String>) managerComboBox.getModel();
            model1.removeAllElements();
            for (Worker worker : workersManager) {
                model1.addElement(worker.getFirstName() + " " + worker.getLastName() + " (Id : " + worker.getRegistrationNumber() + ")");
            }
        }
    }

    public Locality getSelectedLocality() {
        int selectedIndex = localityComboBox.getSelectedIndex();
        return localities.get(selectedIndex);
    }
    public Bakery getSelectedBakery(){
        int selectedIndex = bakeryComboBox.getSelectedIndex();
        return bakeries.get(selectedIndex);
    }
    public Worker getSelectedWorkerManager(){
        int selectedIndex = managerComboBox.getSelectedIndex();
        return workersManager.get(selectedIndex);
    }

    public JSpinner getDates() {
        return dates;
    }
    public JTextField getFirstName() {
        return firstName;
    }
    public JTextField getLastName() {
        return lastName;
    }
    public JTextField getEmail() {
        return email;
    }
    public JTextField getAddress() {
        return address;
    }
    public JTextField getPhoneNumber() {
        return phoneNumber;
    }
    public JCheckBox getSubordinate() {
        return subordinate;
    }
    public JComboBox getManagerComboBox() {
        return managerComboBox;
    }
    public JComboBox getLocalityComboBox() {
        return localityComboBox;
    }
    public JComboBox getBakeryComboBox() {
        return bakeryComboBox;
    }
    public JRadioButton getAdmin() {
        return admin;
    }
    public JRadioButton getUser() {
        return user;
    }
}

