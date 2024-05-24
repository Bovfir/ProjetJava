package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("<html><h1><U>Bienvenue dans l'application !</U></h1><p style='text-align: center;'><img src='" + getClass().getResource("/imagesPackage/bakery.png") + "' width='100' height='100'></p></html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setVerticalAlignment(SwingConstants.TOP);
        this.add(welcomeLabel, BorderLayout.CENTER);

        JPanel advertisementPanel = new JPanel();
        advertisementPanel.setLayout(new BorderLayout());
        JLabel advertisingTextLabel = new JLabel("");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesPackage/information.png")));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

        advertisingTextLabel.setIcon(new ImageIcon(newImage));
        advertisingTextLabel.setForeground(Color.DARK_GRAY);
        advertisementPanel.add(advertisingTextLabel);
        this.add(advertisementPanel,BorderLayout.SOUTH);

        TextTread textTread = new TextTread(advertisingTextLabel,advertisementPanel);
        textTread.start();
    }
}
