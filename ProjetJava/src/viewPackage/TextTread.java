package viewPackage;

import javax.swing.*;
import java.util.Random;

public class TextTread extends Thread {
    private JLabel advertisingTextLabel;
    private JPanel parentPanel;
    private String[] advertisements;
    private Random random;

    public TextTread(JLabel advertisingTextLabel, JPanel parentPanel) {
        this.advertisingTextLabel = advertisingTextLabel;
        this.parentPanel = parentPanel;
        this.advertisements = new String[] {
                "Pains frais et délices à Namur - PointChaud vous attend !",
                "LePainDoré - l'essence de la boulangerie sur l'Avenue des Roses !",
                "LaBaguetteMagique à Vedrin - pour des saveurs magiques !",
                "Découvrez LeFournil à Bouge - la boulangerie de choix !",
                "AuPainCroustillant à Belgrade - pour des pains croustillants !",
                "LeBonPain à Jambes - où la boulangerie devient un art !",
                "AuCroustillant à Wépion - l'endroit idéal pour les amateurs de pain !",
                "Des créations artisanales chez LaBaguetteMagique à Suarlée !",
                "LePainDoré à Vedrin - pour des pains frais et savoureux !",
                "LeFournil à Bouge - tradition et qualité réunies !"
        };
        this.random = new Random();
    }

    public void run() {
        int randomNumber;
        while (true) {
            while (parentPanel.getWidth() == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            randomNumber = random.nextInt(advertisements.length);
            advertisingTextLabel.setText(advertisements[randomNumber]);


            int x = parentPanel.getWidth();
            int y = (parentPanel.getHeight() - advertisingTextLabel.getHeight()) / 2; // Centrer le texte verticalement


            while (x > - advertisingTextLabel.getPreferredSize().width) {
                x -= 1;
                advertisingTextLabel.setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
