package viewPackage;

import modelPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class UpdateWorkerPanel extends JPanel {
    private Container mainContainer;
    private Formatter formatter;
    private JTextArea workerDescription;
    private JComboBox<String> workerJComboBox;
    private ArrayList<Worker> workers;

    public UpdateWorkerPanel(Container mainContainer){
            setLayout(new GridLayout(1, 2, 5, 5));
            this.mainContainer = mainContainer;
            formatter = new Formatter();

            // Panel de Gauche
            JPanel panelLeft = new JPanel();
            panelLeft.setLayout(new GridLayout(3, 1, 5, 5));
            panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel titleLabel = new JLabel("<html><U>Mise à jour de Boulanger : </U></html>");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setVerticalAlignment(SwingConstants.TOP);

            JLabel workerLabel = new JLabel("Boulanger : ");
            workerLabel.setHorizontalAlignment(SwingConstants.CENTER);

            workers = formatter.getAllWorkers();
            DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
            for(Worker worker : workers){
                model1.addElement(worker.getLastName() + " " + worker.getFirstName() + " (Id : " + worker.getRegistrationNumber() + ")");
            }
            workerJComboBox = new JComboBox<>(model1);
            workerJComboBox.setMaximumRowCount(6);
            workerJComboBox.addActionListener(new ComboBoxListener());

            // Panel pour la comboBox
            JPanel workersPanel = new JPanel();
            workersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            workersPanel.add(workerLabel);
            workersPanel.add(workerJComboBox);

            // Panel des bouttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            JButton backButton, validationButton;

            backButton = new JButton("Retour");
            backButton.addActionListener(new ChangePanelListener(new WelcomePanel(), mainContainer));
            backButton.setVerticalAlignment(SwingConstants.BOTTOM);
            backButton.setVerticalAlignment(SwingConstants.CENTER);

            validationButton = new JButton("Valider");
            validationButton.setVerticalAlignment(SwingConstants.BOTTOM);
            validationButton.setVerticalAlignment(SwingConstants.CENTER);
            validationButton.addActionListener(new ValidationButtonListener());

            buttonPanel.add(backButton);
            buttonPanel.add(validationButton);

            // Ajout des élements dans le panel de gauche
            panelLeft.add(titleLabel);
            panelLeft.add(workersPanel);
            panelLeft.add(buttonPanel,BorderLayout.SOUTH);
            this.add(panelLeft);

            // Panel de droite
            JPanel panelRight = new JPanel();
            panelRight.setLayout(new GridLayout(3, 1, 5, 5));
            panelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            workerDescription = new JTextArea();
            workerDescription.setEditable(false);
            workerDescription.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
            if(!workers.isEmpty()) {
                changeDescription(getSelectedWorker().getRegistrationNumber());
            }

            JLabel infosLabel = new JLabel("<html><U>Informations du boulanger</U</html");
            infosLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infosLabel.setVerticalAlignment(SwingConstants.BOTTOM);

            // Ajout des éléments dans le panel de droite
            panelRight.add(infosLabel);
            panelRight.add(new JScrollPane(workerDescription));
            this.add(panelRight);

            // Séparation des deux panels
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panelLeft, panelRight);
            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerLocation(270);
            this.add(splitPane);

            setVisible(true);
    }

    private class ComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            changeDescription(getSelectedWorker().getRegistrationNumber());
        }
    }

    private void changeDescription(int selectedIndex) {
        Worker worker = researchWorker(selectedIndex);
        if(worker != null) {
            workerDescription.setText(
                    "Prénom : " + worker.getFirstName() + "\n" +
                            "Nom : " + worker.getLastName() + "\n" +
                            "Date de Naissance : " + worker.getBirthday() + "\n" +
                            "Email : " + worker.getEmail() + "\n" +
                            "GSM : " + (worker.getPhoneNumber() == null ? "" : worker.getPhoneNumber()) + "\n" +
                            "Adresse : " + (worker.getAddress() == null ? "" : worker.getAddress()) + "\n" +
                            "Locality : " + worker.getLocality() + "\n" +
                            "Manager ID : " + (worker.getManagerId() == null ? "" : worker.getManagerId()) + "\n" +
                            "Droit Admin : " + worker.getAdmin() + "\n" +
                            "Boulangerie : " + worker.getBakery()
            );
            workerDescription.setCaretPosition(0);
        }
    }

    public Worker researchWorker(int index){
        Worker worker = null;
        for (Worker Searchworker : workers) {
            if (Searchworker.getRegistrationNumber() == index) {
                worker = Searchworker;
            }
        }
        return worker;
    }

    private class ValidationButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!workers.isEmpty()) {
                ArrayList<Locality> localities = formatter.getAllLocalities();
                ArrayList<Bakery> bakeries = formatter.getAllBakeries();

                int selectedIndex = getSelectedWorker().getRegistrationNumber();
                Worker worker = researchWorker(selectedIndex);

                if (worker != null) {
                    LocalDate localDate = worker.getBirthday();
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    WorkerFormPanel workerFormPanel = new WorkerFormPanel(mainContainer);
                    FormPanel formPanel = workerFormPanel.getFormPanel();
                    ButtonsPanel buttonsPanel = workerFormPanel.getButtonsPanel();

                    buttonsPanel.setUpdate(true);

                    formPanel.getFirstName().setText(worker.getFirstName());
                    formPanel.getLastName().setText(worker.getLastName());
                    formPanel.getDates().setValue(date);
                    formPanel.getEmail().setText(worker.getEmail());
                    formPanel.getPhoneNumber().setText((worker.getPhoneNumber() == null) ?"" : worker.getPhoneNumber().toString());
                    formPanel.getAddress().setText(worker.getAddress());

                    buttonsPanel.setRegistrationNumber(selectedIndex);
                    buttonsPanel.setOldEmail(worker.getEmail());


                    if (worker.getManagerId() != null) {
                        formPanel.getSubordinate().setSelected(true);
                        formPanel.getManagerComboBox().setEnabled(true);
                    }
                    if (worker.getAdmin()) {
                        formPanel.getAdmin().setSelected(true);
                    } else {
                        formPanel.getUser().setSelected(true);
                    }

                    researchValue(localities, worker, formPanel);
                    researchValue(bakeries, worker, formPanel);
                    researchValue(workers, worker, formPanel);

                    int choix = JOptionPane.showConfirmDialog(null, "Vous êtes sur le point de modifier le travailleur :\n         " + getSelectedWorker().getLastName() + " " + getSelectedWorker().getFirstName() + " (Id : " + getSelectedWorker().getRegistrationNumber() + ")" + "\n         Voulez-vous continuer ?", "Warning", JOptionPane.WARNING_MESSAGE);
                    if (choix == JOptionPane.YES_OPTION) {
                        mainContainer.removeAll();
                        mainContainer.add(workerFormPanel, BorderLayout.CENTER);
                        mainContainer.revalidate();
                        mainContainer.repaint();
                    }
                }
            }
        }
    }
    private void researchValue(ArrayList<?> values, Worker worker,FormPanel formPanel){
        for(Object value : values){
            switch (value.getClass().toString()){
                case "class modelPackage.Locality":
                    if (((Locality) value).getId().equals(worker.getLocality())) {
                        String text = ((Locality) value).getWording() + " ("+((Locality) value).getPostalCode() + ")";
                        formPanel.getLocalityComboBox().setSelectedItem(text);
                    }
                    break;
                case "class modelPackage.Bakery":
                    if (((Bakery) value).getId().equals(worker.getBakery())) {
                        String text = ((Bakery) value).getName() + " (Id : " + ((Bakery) value).getId()+ ")";
                        formPanel.getBakeryComboBox().setSelectedItem(text);
                    }
                    break;
                case "class modelPackage.Worker":
                    if (((Worker) value).getRegistrationNumber().equals(worker.getManagerId())) {
                        String text = ((Worker) value).getFirstName() + " " + ((Worker) value).getLastName() + " (Id : " + ((Worker) value).getRegistrationNumber() + ")";
                        formPanel.getManagerComboBox().setSelectedItem(text);
                    }
                    break;
                default:
            }
        }
    }

    public Worker getSelectedWorker() {
        int selectedIndex = workerJComboBox.getSelectedIndex();
        return workers.get(selectedIndex);
    }
}
