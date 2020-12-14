package pl.jbobbinprinter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;

public class YarnTypesTab extends JPanel {

    public YarnTypesTab(){

        setLayout(null);
        setBackground(new Color(50,50,50));

        JLabel addTypeLabel = new JLabel();
        addTypeLabel.setBounds(20,20,265,30);
        addTypeLabel.setForeground(new Color(245,245,245));
        addTypeLabel.setText("WPISZ TYP PRZĘDZY DO DODANIA:");
        add(addTypeLabel);

        JTextField yarnTypeInput = new JTextField();
        yarnTypeInput.setBounds(20,50,265,30);
        add(yarnTypeInput);

        JButton addTypeButton = new JButton("DODAJ TYP");
        addTypeButton.setBounds(20,90,265,60);
        add(addTypeButton);

        JLabel listOfTypeLabel = new JLabel();
        listOfTypeLabel.setBounds(20,170,265,30);
        listOfTypeLabel.setForeground(new Color(245,245,245));
        listOfTypeLabel.setText("LISTA TYPÓW PRZĘDZY:");
        add(listOfTypeLabel);

        JList<YarnTypes> yarnTypeList = new JList<>();
        yarnTypeList.setBounds(20,200,265,100);
        yarnTypeList.setSelectedIndex(0);

        YarnTypesSQL.getYarnTypeFromSQLToList(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),0,yarnTypeList);

        add(yarnTypeList);

        JLabel editTypeLabel = new JLabel();
        editTypeLabel.setBounds(20,320,265,30);
        editTypeLabel.setForeground(new Color(245,245,245));
        editTypeLabel.setText("TYP DO EDYCJI WYBRANY Z LISTY:");
        add(editTypeLabel);

        JTextField editTypeInput = new JTextField();
        editTypeInput.setBounds(20,350,265,30);
        add(editTypeInput);

        JButton editTypeButton = new JButton("ZAPISZ ZMIANY");
        editTypeButton.setBounds(20,390,265,60);
        add(editTypeButton);

        addTypeButton.addActionListener(e -> {
            String addTypeText = yarnTypeInput.getText().trim().toUpperCase(Locale.ROOT);

            if(addTypeText.equals("")){
                JOptionPane.showMessageDialog(this, "Nic nie wpisano !");
            }else {

                if(YarnTypesSQL.checkIfYarnTypeExist(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")),addTypeText)){
                    JOptionPane.showMessageDialog(this, "Taki typ już istnieje !");
                    yarnTypeInput.setText("");
                }else{
                    YarnTypesSQL.addYarnTypeToSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), new YarnTypes(addTypeText));
                    yarnTypeInput.setText("");
                    YarnTypesSQL.getYarnTypeFromSQLToList(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), 0, yarnTypeList);
                }
            }
        });

        yarnTypeList.addListSelectionListener(e -> {
            YarnTypes yarnTypeListObjectToEdit = yarnTypeList.getSelectedValue();
            if(yarnTypeListObjectToEdit != null) {
                editTypeInput.setText(yarnTypeListObjectToEdit.getType());
            }
        });

        editTypeButton.addActionListener(e -> {
            if(editTypeInput.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Nie wybrano typu z listy !");
            }else {
                YarnTypes yarnTypeListObjectToSave = yarnTypeList.getSelectedValue();
                int yarnTypeId = yarnTypeListObjectToSave.getTypeId();
                String yarnType = editTypeInput.getText().trim().toUpperCase(Locale.ROOT);
                int yarnTypeArchived = 0;
                YarnTypesSQL.updateYarnTypeFromSQL(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), new YarnTypes(yarnTypeId, yarnType, yarnTypeArchived));
                editTypeInput.setText("");
                YarnTypesSQL.getYarnTypeFromSQLToList(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")), 0, yarnTypeList);
            }
        });


    }

    }

