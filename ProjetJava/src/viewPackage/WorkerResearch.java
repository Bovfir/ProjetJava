package viewPackage;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import modelPackage.*;

public class WorkerResearch extends JPanel {
    private Formatter formatter;
    private ArrayList<Integer> postalCodes;
    private JList<String> localitiesList;
    private JTable result;

    public WorkerResearch(Container mainContainer) {

            formatter = new Formatter();
            postalCodes = formatter.getAllPostalCodes();

            this.setLayout(new BorderLayout());

            JPanel workerResearchPanel = new JPanel();
            workerResearchPanel.setLayout(new BorderLayout());
            workerResearchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            DefaultListModel<String> model = new DefaultListModel<>();
            for(Integer postalCode : postalCodes){
                model.addElement(""+postalCode);
            }
            localitiesList = new JList(model);
            localitiesList.setVisibleRowCount(3);
            localitiesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            localitiesList.setLayoutOrientation(JList.VERTICAL);
            localitiesList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    if(e.getClickCount() == 2) {
                        int index = localitiesList.locationToIndex(e.getPoint());
                        if (index >= 0) {
                            ActionListener actionListener = new ValidationButton();
                            actionListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
                        }
                    }
                }
            });
            JScrollPane scrollBar = new JScrollPane(localitiesList);

            JLabel postalCodesLabel = new JLabel("<html><body><U><strong>Codes Postaux :</U></strong></body></html>");
            postalCodesLabel.setHorizontalAlignment(SwingConstants.CENTER);
            postalCodesLabel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

            workerResearchPanel.add(postalCodesLabel,BorderLayout.NORTH);
            workerResearchPanel.add(scrollBar, BorderLayout.CENTER);

            JButton validationButton = new JButton("Valider");
            validationButton.addActionListener(new ValidationButton());
            workerResearchPanel.add(validationButton,BorderLayout.SOUTH);
            this.add(workerResearchPanel, BorderLayout.WEST);


            result = new JTable();
            result.getTableHeader().setReorderingAllowed(false);
            JScrollPane scrollPaneTable = new JScrollPane(result);
            this.add(scrollPaneTable);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            JButton backButton = new JButton("Retour");
            backButton.addActionListener(new ChangePanelListener(new WelcomePanel(),mainContainer));
            buttonsPanel.add(backButton);

            this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private class ValidationButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomTableModel tableModel;
            ArrayList<WorkersByLocality> workersByLocalities;
            if (localitiesList.getSelectedIndices().length > 0) {
                workersByLocalities = formatter.getWorkersByLocality(getSelectedLocalities());
                if (workersByLocalities.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aucun boulanger trouvé !", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    tableModel = new CustomTableModel(workersByLocalities);
                    result.setModel(tableModel);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selectionner un ID !", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public ArrayList<Integer> getSelectedLocalities(){
        List<Integer> selectedLocalities = new ArrayList<>();
        int[] selectedIndices = localitiesList.getSelectedIndices();
        for (int index : selectedIndices) {
            selectedLocalities.add(postalCodes.get(index));
        }
        return new ArrayList<Integer>(selectedLocalities);
    }
    private class CustomTableModel extends AbstractTableModel{
        private String [] columnNames = {"Nom","Prénom","Boulangerie","Localité","Code Postal"};
        private ArrayList<WorkersByLocality> workersByLocalities;

        public CustomTableModel(ArrayList<WorkersByLocality> workersByLocalities){
            this.workersByLocalities = workersByLocalities;
        }
        @Override
        public int getRowCount(){
            return workersByLocalities.size();
        }
        @Override
        public int getColumnCount(){
            return columnNames.length;
        }
        @Override
        public Object getValueAt (int row, int col){
            WorkersByLocality worker = workersByLocalities.get(row);
            return switch (col) {
                case 0 -> worker.getFirstName();
                case 1 -> worker.getLastName();
                case 2 -> worker.getBakery();
                case 3 -> worker.getLocality();
                case 4 -> worker.getPostalCode();
                default -> null;
            };
        }
        @Override
        public String getColumnName(int col){
            return columnNames[col];
        }
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return switch (columnIndex) {
                case 0, 1, 2, 3 -> String.class;
                case 4 -> Integer.class;
                default -> Object.class;
            };
        }
    }
}
