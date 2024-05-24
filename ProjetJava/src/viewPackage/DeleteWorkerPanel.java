package viewPackage;

import modelPackage.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteWorkerPanel extends JPanel {
    private Formatter formatter;
    private Container mainContainer;
    private JTextArea workerDescription;
    private JList<String> workersList;
    private ArrayList<Worker> workers;

    public DeleteWorkerPanel(Container mainContainer) {
        formatter = new Formatter();
        workers = formatter.getAllWorkers();
        this.mainContainer = mainContainer;
        this.setLayout(new GridLayout(1, 2, 5, 5));

        // Left panel
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(3, 1, 5, 5));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel listLabel = new JLabel("<html><U>Liste des Boulangers :</U></html>");
        listLabel.setVerticalAlignment(SwingConstants.CENTER);
        listLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        // Workers list
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Worker worker : workers) {
            model.addElement(worker.getLastName() + " " + worker.getFirstName() + " (Id : " + worker.getRegistrationNumber() + ")");
        }
        workersList = new JList<>(model);
        workersList.setSelectedIndex(0);
        workersList.setVisibleRowCount(5);
        workersList.setLayoutOrientation(JList.VERTICAL);
        workersList.addListSelectionListener(new workerListListener());
        JScrollPane scrollBar = new JScrollPane(workersList);


        // Buttons panel
        JPanel buttonsDeleteWorkerPanel = new JPanel();
        buttonsDeleteWorkerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 60));

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(new ChangePanelListener(new WelcomePanel(), mainContainer));

        JButton validationButton = new JButton("Validation");
        validationButton.addActionListener(new ValidationListener());

        buttonsDeleteWorkerPanel.add(backButton);
        buttonsDeleteWorkerPanel.add(validationButton);

        panelLeft.add(listLabel);
        panelLeft.add(scrollBar);
        panelLeft.add(buttonsDeleteWorkerPanel);
        this.add(panelLeft);

        // Right panel
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(3, 1, 5, 5));
        panelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel infosLabel = new JLabel("<html><U>Informations du boulanger</U</html");
        infosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infosLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        workerDescription = new JTextArea();
        workerDescription.setEditable(false);
        workerDescription.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        if (!workers.isEmpty()) {
            changeDescription(getSelectedWorker().getRegistrationNumber());
        }

        panelRight.add(infosLabel);
        panelRight.add(new JScrollPane(workerDescription));
        this.add(panelRight);

        // Separation between the two panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panelLeft, panelRight);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        this.add(splitPane);
    }

    private void changeDescription(int selectedIndex) {
        Worker worker = null;

        for (Worker Searchworker : workers) {
            if (Searchworker.getRegistrationNumber() == selectedIndex) {
                worker = Searchworker;
            }
        }
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

    private class workerListListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            changeDescription(getSelectedWorker().getRegistrationNumber());
        }
    }
    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!workers.isEmpty()) {
                int idWorker = getSelectedWorker().getRegistrationNumber();
                int choice = JOptionPane.showConfirmDialog(null, "Attention, vous êtes sur le point de supprimer le trailleur " + idWorker + " !\n         Voulez-vous continuer ?", "Warning", JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    formatter.deleteWorker(idWorker);
                    JOptionPane.showMessageDialog(null, "Suppression avec succès ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                    ActionListener actionListener = new ChangePanelListener(new WelcomePanel(), mainContainer);
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

                } else {
                    JOptionPane.showMessageDialog(null, "Suppression annulée", "Information", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public Worker getSelectedWorker() {
        int selectedIndex = workersList.getSelectedIndex();
        return workers.get(selectedIndex);
    }
}