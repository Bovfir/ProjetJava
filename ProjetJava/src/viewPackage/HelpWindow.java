package viewPackage;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JFrame {
    public HelpWindow(){
        super("Aide");
        setSize(400,200);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagesPackage/help.png")));

        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new FlowLayout());
        JLabel line1 = new JLabel("Pour gérer la boulangerie :");
        JLabel line2 = new JLabel("RDV dans le menu Application");
        JLabel line3 = new JLabel("Pour les Recherches : ");
        JLabel line4 = new JLabel("RDV dans le menu Recherche");
        helpPanel.add(line1);
        helpPanel.add(line2);
        helpPanel.add(line3);
        helpPanel.add(line4);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(helpPanel,BorderLayout.CENTER);

        JButton backButton = new JButton("Retour à la fenêtre principale");
        backButton.addActionListener(event -> dispose());
        mainContainer.add(backButton,BorderLayout.SOUTH);

        setVisible(true);
    }
}
