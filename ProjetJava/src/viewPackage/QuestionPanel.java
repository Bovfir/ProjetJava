package viewPackage;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    public QuestionPanel(){
        setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel("Si vous avez des question , rdv sur notre site www.pocketBakery.com");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(questionLabel,BorderLayout.CENTER);

        setVisible(true);
    }
}
