package viewPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import modelPackage.Category;
import modelPackage.LineResearch;


public class ProductOrderResearch extends JPanel {
    private JTable result;
    private Formatter formatter;
    private JComboBox category;

    public ProductOrderResearch(Container mainContainer) {
        this.setLayout(new BorderLayout());
        formatter = new Formatter();

        // Choice panel
        JPanel productOrderFormPanel = new JPanel();
        productOrderFormPanel.setLayout(new GridLayout(1, 4, 5, 5));
        ArrayList<Category> categories = formatter.getAllCategories();

        productOrderFormPanel.add(new JLabel(""));

        JLabel categoryLabel = new JLabel("Catégorie : ");
        categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        productOrderFormPanel.add(categoryLabel);

        DefaultComboBoxModel<String> categoryModel = new DefaultComboBoxModel<>();
        categoryModel.addElement("Selectionner une catégorie");
        for (Category category : categories) {
            categoryModel.addElement(category.getWording());
        }
        category = new JComboBox<>(categoryModel);
        category.addActionListener(new CategoryListener());
        productOrderFormPanel.add(category);
        productOrderFormPanel.add(new JLabel(""));

        // result table panel
        result = new JTable();
        result.setEnabled(false);
        result.setAutoCreateRowSorter(true);
        result.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(result);

        // button panel
        JPanel backPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(new ChangePanelListener(new WelcomePanel(), mainContainer));
        backPanel.add(backButton);

        // adding elements
        this.add(productOrderFormPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(backPanel, BorderLayout.SOUTH);
    }
    private class CategoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<LineResearch> lines = formatter.getLineByCategory((String) category.getSelectedItem());
            if (lines.isEmpty()) {
                result.setModel(new DefaultTableModel());
                JOptionPane.showMessageDialog(null, "Aucune commande pour cette catégorie", "Information Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ProductOrderTableModel model = new ProductOrderTableModel(lines);
                result.setModel(model);
            }
        }
    }
    private class ProductOrderTableModel extends AbstractTableModel{
        private String[] columnNames = {"Date de Commande","Quantité","Produit","Boulangerie"};
        private ArrayList<LineResearch> lines;

        public ProductOrderTableModel(ArrayList<LineResearch> lines){
            this.lines = lines;
        }

        @Override
        public int getRowCount() {
            return lines.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            LineResearch line = lines.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> line.getOrderDate();
                case 1 -> line.getQuantity();
                case 2 -> line.getWording();
                case 3 -> line.getBakery();
                default -> null;
            };
        }

        public String getColumnName(int columnIndex){
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return switch (columnIndex) {
                case 0 -> LocalDate.class;
                case 1 -> Integer.class;
                case 2, 3 -> String.class;
                default -> Object.class;
            };
        }
    }
}