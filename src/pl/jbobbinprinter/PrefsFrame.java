package pl.jbobbinprinter;

import javax.swing.*;
import java.awt.*;

public class PrefsFrame extends JFrame {

    public PrefsFrame(){

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("USTAWIENIA");
        setSize(600,700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLayout(null);

        JPanel prefsPanel = new JPanel();
        prefsPanel.setOpaque(true);
        prefsPanel.setSize(595,695);
        prefsPanel.setBackground(new Color(50, 50, 50));
        prefsPanel.setLayout(null);
        add(prefsPanel);

        JButton backToMainMenuButton = new JButton("MENU GŁÓWNE");
        backToMainMenuButton.setBounds(20,570,265,60);
        prefsPanel.add(backToMainMenuButton);

        JButton exitProgramFromPrefsButton = new JButton("ZAKOŃCZ");
        exitProgramFromPrefsButton.setBounds(295,570,265,60);
        prefsPanel.add(exitProgramFromPrefsButton);



        backToMainMenuButton.addActionListener(e -> {

            setVisible(false);
            new MainMenuFrame().setVisible(true);


        });

        exitProgramFromPrefsButton.addActionListener(e -> System.exit(0));

    }
}
