package pl.jbobbinprinter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {

        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JBobbinPrinter");
        setSize(300,350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
        setLayout(null);

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setOpaque(true);
        mainMenuPanel.setSize(295,345);
        mainMenuPanel.setBackground(new Color(50, 50, 50));
        mainMenuPanel.setLayout(null);
        add(mainMenuPanel);

        JButton openPrintPanelButton = new JButton("DRUKUJ ETYKIETY");
        openPrintPanelButton.setBounds(10,30,265,60);
        mainMenuPanel.add(openPrintPanelButton);

        JButton openPropPanelButton = new JButton("USTAWIENIA");
        openPropPanelButton.setBounds(10,100,265,60);
        mainMenuPanel.add(openPropPanelButton);

        JButton exitProgramFromMainMenuButton = new JButton("ZAKOŃCZ");
        exitProgramFromMainMenuButton.setBounds(10,220,265,60);
        mainMenuPanel.add(exitProgramFromMainMenuButton);

        openPrintPanelButton.addActionListener(e -> {

            JavaDB.createYarnsTable(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")));
            setVisible(false);
            new PrintFrame().setVisible(true);

        });

        openPropPanelButton.addActionListener(e -> {

            JavaDB.createYarnsTable(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")));
            setVisible(false);
            new PrefsFrame().setVisible(true);


        });

        exitProgramFromMainMenuButton.addActionListener(e -> System.exit(0));




    }

}
