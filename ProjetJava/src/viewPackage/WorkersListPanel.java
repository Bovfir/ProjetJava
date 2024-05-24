package viewPackage;

import modelPackage.Worker;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class WorkersListPanel extends JPanel {
    private WorkerTableModel model;
    private Formatter formatter;
    public WorkersListPanel(Container mainContainer) {
            formatter = new Formatter();

            setLayout(new BorderLayout());

            JLabel title = new JLabel("<html><body><h2><U>Liste des boulangers :</U></h2></body></html>");
            title.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(title, BorderLayout.NORTH);

            model = new WorkerTableModel();
            JTable result = new JTable(model);
            result.getTableHeader().setReorderingAllowed(false);
            result.setDefaultRenderer(String.class, new CenteredStringCellRenderer());

            JScrollPane scrollPane = new JScrollPane(result);
            this.add(scrollPane, BorderLayout.CENTER);
            loadWorkersData();

            JPanel buttonsListPanel = new JPanel();
            buttonsListPanel.setLayout(new FlowLayout());
            JButton back = new JButton("Retour");
            back.addActionListener(new ChangePanelListener(new WelcomePanel(),mainContainer));
            buttonsListPanel.add(back);
            this.add(buttonsListPanel,BorderLayout.SOUTH);
    }
    public void loadWorkersData(){
        model.setWorkersData(formatter.getAllWorkers());
    }

    private class WorkerTableModel extends AbstractTableModel {
        private ArrayList<Worker> workers;
        private final String[] columnNames = {"RegistrationNumber", "Prénom", "Nom", "Date de Naissance", "Email", "Gsm", "Adresse", "Locality", "DroitAdmin", "ManagerID", "Bakery"};

        public WorkerTableModel() {
            workers = new ArrayList<>();
        }

        public void setWorkersData(ArrayList<Worker> workers) {
            this.workers = workers;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return workers.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Worker worker = workers.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> worker.getRegistrationNumber();
                case 1 -> worker.getFirstName();
                case 2 -> worker.getLastName();
                case 3 -> worker.getBirthday();
                case 4 -> worker.getEmail();
                case 5 -> worker.getPhoneNumber();
                case 6 -> worker.getAddress();
                case 7 -> worker.getLocality();
                case 8 -> worker.getAdmin();
                case 9 -> worker.getManagerId();
                case 10 -> worker.getBakery();
                default -> null;
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return switch (columnIndex) {
                case 0, 7, 9, 10 -> Integer.class;
                case 1, 2, 4, 5, 6 -> String.class;
                case 3 -> LocalDate.class;
                case 8 -> Boolean.class;
                default -> Object.class;
            };
        }
    }
    public class CenteredStringCellRenderer extends DefaultTableCellRenderer {
        public CenteredStringCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
}
