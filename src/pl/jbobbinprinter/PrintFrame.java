package pl.jbobbinprinter;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
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

        JButton getYarnButton = new JButton("POKAŻ PRZĘDZE");
        getYarnButton.setBounds(295,60,265,60);
        printPanel.add(getYarnButton);

        JButton backToMainMenuButton = new JButton("MENU GŁÓWNE");
        backToMainMenuButton.setBounds(20,570,265,60);
        printPanel.add(backToMainMenuButton);

        JButton exitProgramFromPrintButton = new JButton("ZAKOŃCZ");
        exitProgramFromPrintButton.setBounds(295,570,265,60);
        printPanel.add(exitProgramFromPrintButton);



        addYarnButton.addActionListener(e -> {
            inputYarnName = yarnNameInput.getText();
            inputYarnType = yarnTypeInput.getText();
            inputYarnWeight = yarnWeightInput.getText();
            JavaDB.addYarnToSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),"yarns",new Yarn(inputYarnName,inputYarnType,inputYarnWeight));
        });

        getYarnButton.addActionListener(e ->{
            Map<Integer, Yarn> showList = JavaDB.getYarnFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),"yarns", 0);

            if(showList != null) {
                for (int i : showList.keySet()) {
                    System.out.println("key: " + showList.get(i).getYarnKey() + " | name: " + showList.get(i).getYarnName() +
                            " | type: " + showList.get(i).getYarnType() + " | weight: " + showList.get(i).getYarnWeight() +
                    " | archived: " + showList.get(i).getYarnArchived());
                }
            }
        });

        backToMainMenuButton.addActionListener(e -> {

            setVisible(false);
            new MainMenuFrame().setVisible(true);


        });

        exitProgramFromPrintButton.addActionListener(e -> System.exit(0));



    }
}
