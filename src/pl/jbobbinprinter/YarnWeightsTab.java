package pl.jbobbinprinter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;

public class YarnWeightsTab extends JPanel {

    public YarnWeightsTab(){

        setLayout(null);
        setBackground(new Color(50,50,50));

        JLabel addWeightLabel = new JLabel();
        addWeightLabel.setBounds(20,20,265,30);
        addWeightLabel.setForeground(new Color(245,245,245));
        addWeightLabel.setText("WPISZ GRUBOŚĆ PRZĘDZY DO DODANIA:");
        add(addWeightLabel);

        JTextField yarnWeightInput = new JTextField();
        yarnWeightInput.setBounds(20,50,265,30);
        add(yarnWeightInput);

        JButton addWeightButton = new JButton("DODAJ GRUBOŚĆ");
        addWeightButton.setBounds(20,90,265,60);
        add(addWeightButton);

        JLabel listOfWeightLabel = new JLabel();
        listOfWeightLabel.setBounds(20,170,265,30);
        listOfWeightLabel.setForeground(new Color(245,245,245));
        listOfWeightLabel.setText("LISTA GRUBOŚCI PRZĘDZY:");
        add(listOfWeightLabel);

        JList<YarnWeight> yarnWeightList = new JList<>();
        yarnWeightList.setBounds(20,200,265,100);
        yarnWeightList.setSelectedIndex(0);

        YarnWeightSQL.getYarnWeightFromSQLToList(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),0,yarnWeightList);

        add(yarnWeightList);

        JLabel editWeightLabel = new JLabel();
        editWeightLabel.setBounds(20,320,265,30);
        editWeightLabel.setForeground(new Color(245,245,245));
        editWeightLabel.setText("GRUBOŚĆ DO EDYCJI WYBRANA Z LISTY:");
        add(editWeightLabel);

        JTextField editWeightInput = new JTextField();
        editWeightInput.setBounds(20,350,265,30);
        add(editWeightInput);

        JButton editWeightButton = new JButton("ZAPISZ ZMIANY");
        editWeightButton.setBounds(20,390,265,60);
        add(editWeightButton);


        addWeightButton.addActionListener(e -> {
            String addWeightText = yarnWeightInput.getText().trim().toUpperCase(Locale.ROOT);

            if(addWeightText.equals("")){
                JOptionPane.showMessageDialog(this, "Nic nie wpisano !");
            }else {

                if(YarnWeightSQL.checkIfYarnWeightExist(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),addWeightText)){
                    JOptionPane.showMessageDialog(this, "Taka grubość już istnieje !");
                    yarnWeightInput.setText("");
                }else{
                      YarnWeightSQL.addYarnWeightToSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), new YarnWeight(addWeightText));
                     yarnWeightInput.setText("");
                     YarnWeightSQL.getYarnWeightFromSQLToList(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), 0, yarnWeightList);
                }
            }
        });

        yarnWeightList.addListSelectionListener(e -> {
            YarnWeight yarnWeightListObjectToEdit = yarnWeightList.getSelectedValue();
            if(yarnWeightListObjectToEdit != null) {
                editWeightInput.setText(yarnWeightListObjectToEdit.getWeight());
            }
        });

        editWeightButton.addActionListener(e -> {
            if(editWeightInput.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Nie wybrano grubości z listy !");
            }else {
                YarnWeight yarnWeightListObjectToSave = yarnWeightList.getSelectedValue();
                int yarnWeightId = yarnWeightListObjectToSave.getWeightId();
                String yarnWeight = editWeightInput.getText().trim().toUpperCase(Locale.ROOT);
                int yarnWeightArchived = 0;
                YarnWeightSQL.updateYarnWeightFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), new YarnWeight(yarnWeightId, yarnWeight, yarnWeightArchived));
                editWeightInput.setText("");
                YarnWeightSQL.getYarnWeightFromSQLToList(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), 0, yarnWeightList);
            }
        });


    }

}
