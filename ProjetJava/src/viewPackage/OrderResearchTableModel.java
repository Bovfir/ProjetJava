package viewPackage;

import modelPackage.OrderResearch;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderResearchTableModel extends AbstractTableModel {
    private ArrayList<OrderResearch> orderResearches;
    private String[] columnNames = {"Numéro de Commande", "Date de commande", "Statut", "Moyen de paiement"};

    public OrderResearchTableModel(ArrayList<OrderResearch> orderResearches){
        this.orderResearches = orderResearches;
    }
    @Override
    public int getRowCount() {
        return orderResearches.size();
    }

    @Override
    public int getColumnCount() {
        return  columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderResearch orderResearch = orderResearches.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> orderResearch.getOrderNumber();
            case 1 -> orderResearch.getOrderDate();
            case 2 -> orderResearch.getStatus();
            case 3 -> orderResearch.getPaymentWording();
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
            case 0 -> Integer.class;
            case 1 -> LocalDate.class;
            case 2, 3 -> String.class;
            default -> Object.class;
        };
    }
}