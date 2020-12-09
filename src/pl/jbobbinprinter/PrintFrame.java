package pl.jbobbinprinter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PrintFrame extends JFrame {


    String inputYarnName;
    String inputYarnType;
    String inputYarnWeight;


    public PrintFrame(){




        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DRUKOWANIE");
        setSize(600,700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLayout(null);

        JPanel printPanel = new JPanel();
        printPanel.setOpaque(true);
        printPanel.setSize(595,695);
        printPanel.setBackground(new Color(50, 50, 50));
        printPanel.setLayout(null);
        add(printPanel);

        JTextField yarnNameInput = new JTextField();
        yarnNameInput.setBounds(20,20,150,30);
        printPanel.add(yarnNameInput);


        JTextField yarnTypeInput = new JTextField();
        yarnTypeInput.setBounds(180,20,150,30);
        printPanel.add(yarnTypeInput);


        JTextField yarnWeightInput = new JTextField();
        yarnWeightInput.setBounds(340,20,150,30);
        printPanel.add(yarnWeightInput);


        JButton addYarnButton = new JButton("DODAJ DO LISTY");
        addYarnButton.setBounds(20,60,265,60);
        printPanel.add(addYarnButton);

        JButton deleteYarnButton = new JButton("USUŃ PRZĘDZE");
        deleteYarnButton.setBounds(295,60,265,60);
        printPanel.add(deleteYarnButton);

        JComboBox<Yarn> combo = new JComboBox<>();
        combo.setBounds(20,130,265,30);
        printPanel.add(combo);


        YarnSQL.getYarnFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),0, combo);

        if(combo.getSelectedItem() != null) {
            yarnNameInput.setText(((Yarn) combo.getSelectedItem()).getYarnName());
            yarnTypeInput.setText(((Yarn) combo.getSelectedItem()).getYarnType());
            yarnWeightInput.setText(((Yarn) combo.getSelectedItem()).getYarnWeight());
        }

        JButton backToMainMenuButton = new JButton("MENU GŁÓWNE");
        backToMainMenuButton.setBounds(20,570,265,60);
        printPanel.add(backToMainMenuButton);

        JButton exitProgramFromPrintButton = new JButton("ZAKOŃCZ");
        exitProgramFromPrintButton.setBounds(295,570,265,60);
        printPanel.add(exitProgramFromPrintButton);


        combo.addActionListener(e -> {
if(combo.getSelectedItem() != null) {
    yarnNameInput.setText(((Yarn) combo.getSelectedItem()).getYarnName());
    yarnTypeInput.setText(((Yarn) combo.getSelectedItem()).getYarnType());
    yarnWeightInput.setText(((Yarn) combo.getSelectedItem()).getYarnWeight());
}
        });

        addYarnButton.addActionListener(e -> {
            inputYarnName = yarnNameInput.getText();
            inputYarnType = yarnTypeInput.getText();
            inputYarnWeight = yarnWeightInput.getText();
            YarnSQL.addYarnToSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),new Yarn(inputYarnName,inputYarnType,inputYarnWeight));
            combo.removeAllItems();
            YarnSQL.getYarnFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),0, combo);
        });

        deleteYarnButton.addActionListener(e ->{
            inputYarnName = yarnNameInput.getText();
            inputYarnType = yarnTypeInput.getText();
            inputYarnWeight = yarnWeightInput.getText();
           int getKeyFromCombo = ((Yarn) Objects.requireNonNull(combo.getSelectedItem())).getYarnKey();

           YarnSQL.updateYarnFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), getKeyFromCombo,new Yarn(inputYarnName,inputYarnType,inputYarnWeight), 0 );
          // YarnSQL.deleteYarnFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), getKeyFromCombo);

            combo.removeAllItems();
            YarnSQL.getYarnFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),0, combo);


        });

        backToMainMenuButton.addActionListener(e -> {

            setVisible(false);
            new MainMenuFrame().setVisible(true);


        });

        exitProgramFromPrintButton.addActionListener(e -> System.exit(0));



    }
}
