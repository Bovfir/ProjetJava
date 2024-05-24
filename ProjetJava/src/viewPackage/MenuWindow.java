package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MenuWindow extends JFrame {
    public MenuWindow() {
        super("Pocket Bakery");
        setSize(670,390);
        setLocationRelativeTo(null);

        Formatter formatter = new Formatter();

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagesPackage/bakery.png")));
        WelcomePanel welcomePanel = new WelcomePanel();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                formatter.closeConnection();
                System.exit(0);
            }
        });

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(welcomePanel,BorderLayout.CENTER);

        JMenu applicationMenu = new JMenu("Système");
        applicationMenu.setMnemonic('S');

        JMenu workerMenu = new JMenu("Boulanger");
        workerMenu.setMnemonic('B');

        JMenu helpMenu = new JMenu("Aide");
        helpMenu.setMnemonic('A');

        JMenu order = new JMenu("Commande");
        order.setMnemonic('C');

        JMenu research = new JMenu("Recherche");
        research.setMnemonic('R');

        menuBar.add(applicationMenu);
        menuBar.add(workerMenu);
        menuBar.add(order);
        menuBar.add(research);
        menuBar.add(helpMenu);

        // Création des Icons
        String[] imagePaths = {"/imagesPackage/acceuil.png", "/imagesPackage/exit.png", "/imagesPackage/plus.png", "/imagesPackage/supprimer.png", "/imagesPackage/update.png", "/imagesPackage/voir.png", "/imagesPackage/support.png", "/imagesPackage/question.png", "/imagesPackage/panier.png", "/imagesPackage/recherche.png"};
        ImageIcon[] icons = new ImageIcon[imagePaths.length];
        Image[] images = new Image[imagePaths.length];
        Image[] newImages = new Image[imagePaths.length];

        for (int i = 0; i < imagePaths.length; i++) {
            icons[i] = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePaths[i])));
            images[i] = icons[i].getImage();
            newImages[i] = images[i].getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        }

        JMenuItem welcome = new JMenuItem("Acceuil");
        welcome.setIcon(new ImageIcon(newImages[0]));
        welcome.addActionListener(new ChangePanelListener(new WelcomePanel(),mainContainer));
        welcome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Quitter");
        exit.setIcon(new ImageIcon(newImages[1]));
        exit.addActionListener(event -> {System.exit(0);formatter.closeConnection();});
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

        JMenuItem newWorker = new JMenuItem("Nouvelle inscription");
        newWorker.setIcon(new ImageIcon(newImages[2]));
        ChangePanelListener workerFormListener = new ChangePanelListener(new WorkerFormPanel(mainContainer),mainContainer);
        newWorker.addActionListener(workerFormListener);
        newWorker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

        JMenuItem deleteWorker = new JMenuItem("Supprimer");
        deleteWorker.setIcon(new ImageIcon(newImages[3]));
        ChangePanelListener deleteWorkerListener = new ChangePanelListener(new DeleteWorkerPanel(mainContainer),mainContainer);
        deleteWorker.addActionListener(deleteWorkerListener);
        deleteWorker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));

        JMenuItem updateWorker = new JMenuItem("Mettre à jour");
        updateWorker.setIcon(new ImageIcon(newImages[4]));
        ChangePanelListener updateWorkerListener = new ChangePanelListener(new UpdateWorkerPanel(mainContainer),mainContainer);
        updateWorker.addActionListener(updateWorkerListener);
        updateWorker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));

        JMenuItem readWorker = new JMenuItem("Voir la liste");
        readWorker.setIcon(new ImageIcon(newImages[5]));
        ChangePanelListener workersListListener = new ChangePanelListener(new WorkersListPanel(mainContainer),mainContainer);
        readWorker.addActionListener(workersListListener);
        readWorker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));

        JMenuItem info = new JMenuItem("Information");
        info.setIcon(new ImageIcon(newImages[6]));
        info.addActionListener(event -> new HelpWindow());
        info.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));

        JMenuItem question = new JMenuItem("Questions");
        question.setIcon(new ImageIcon(newImages[7]));
        question.addActionListener(new ChangePanelListener(new QuestionPanel(),mainContainer));
        question.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        JMenuItem newOrder = new JMenuItem("Nouvelle commande");
        newOrder.setIcon(new ImageIcon(newImages[8]));
        newOrder.addActionListener(new ChangePanelListener(new OrderPanel(mainContainer),mainContainer));

        JMenuItem orderResearch = new JMenuItem("Commandes");
        orderResearch.setIcon(new ImageIcon(newImages[9]));
        orderResearch.addActionListener(new ChangePanelListener(new OrderResearchPanel(mainContainer),mainContainer));

        JMenuItem orderLineResearch = new JMenuItem("Produits commandés");
        orderLineResearch.setIcon(new ImageIcon(newImages[9]));
        orderLineResearch.addActionListener(new ChangePanelListener(new ProductOrderResearch(mainContainer),mainContainer));

        JMenuItem workerByLocalityResearch = new JMenuItem("Travailleurs par localité");
        workerByLocalityResearch.setIcon(new ImageIcon(newImages[9]));
        workerByLocalityResearch.addActionListener(new ChangePanelListener(new WorkerResearch(mainContainer),mainContainer));

        applicationMenu.add(welcome);
        applicationMenu.addSeparator();
        applicationMenu.add(exit);

        helpMenu.add(info);
        helpMenu.addSeparator();
        helpMenu.add(question);

        workerMenu.add(newWorker);
        workerMenu.addSeparator();
        workerMenu.add(updateWorker);
        workerMenu.addSeparator();
        workerMenu.add(deleteWorker);
        workerMenu.addSeparator();
        workerMenu.add(readWorker);

        order.add(newOrder);

        research.add(orderResearch);
        research.addSeparator();
        research.add(orderLineResearch);
        research.addSeparator();
        research.add(workerByLocalityResearch);

        setVisible(true);
    }
}
