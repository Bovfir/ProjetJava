package viewPackage;

import javax.swing.*;
import java.awt.*;

public class WorkerFormPanel extends JPanel {
    private FormPanel formPanel;
    private ButtonsPanel buttonsPanel;

    public WorkerFormPanel(Container mainContainer){
        this.setLayout(new BorderLayout());

        formPanel = new FormPanel();
        buttonsPanel = new ButtonsPanel(mainContainer,formPanel);

        this.add(formPanel,BorderLayout.CENTER);
        this.add(buttonsPanel,BorderLayout.SOUTH);

        setVisible(true);
    }
    public FormPanel getFormPanel() {
        return formPanel;
    }

    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
}
