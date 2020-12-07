package pl.jbobbinprinter;

import java.awt.*;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(MainMenuFrame::new);

        //JavaDB.connectDB("yarns");
       // JavaDB.createYarnsTable(Objects.requireNonNull(JavaDB.connectDB("yarnsDB")));




    }
}
