package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePanelListener implements ActionListener {
    private JPanel panel;
    private Container mainContainer;
    public ChangePanelListener(JPanel panel,Container mainContainer){
        this.panel = panel;
        this.mainContainer = mainContainer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mainContainer.removeAll();

        switch (panel.getClass().toString()){
            case "class viewPackage.WorkerFormPanel" : panel = new WorkerFormPanel(mainContainer);
                break;
            case "class viewPackage.WelcomePanel" : panel = new WelcomePanel();
                break;
            case "class viewPackage.DeleteWorkerPanel" : panel = new DeleteWorkerPanel(mainContainer);
                break;
            case "class viewPackage.OrderPanel" : panel = new OrderPanel(mainContainer);
                break;
            case "class viewPackage.OrderResearchPanel" : panel = new OrderResearchPanel(mainContainer);
                break;
            case "class viewPackage.ProductOrderResearch" : panel = new ProductOrderResearch(mainContainer);
                break;
            case "class viewPackage.WorkerResearch" : panel = new WorkerResearch(mainContainer);
                break;
            case "class viewPackage.UpdateWorkerPanel": panel = new UpdateWorkerPanel(mainContainer);
                break;
            case "class viewPackage.WorkersListPanel": panel = new WorkersListPanel(mainContainer);
                break;
            case "class viewPackage.QuestionPanel": panel = new QuestionPanel();
                break;
        }

        mainContainer.add(panel, BorderLayout.CENTER);
        mainContainer.revalidate();
        mainContainer.repaint();
    }
}
