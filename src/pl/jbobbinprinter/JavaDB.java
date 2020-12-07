package pl.jbobbinprinter;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class JavaDB {

    public static Connection connectDB(String database){
        Connection conn;

        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + database + ".db");
            System.out.println("Connection OK!");
        }catch (Exception e){
            System.err.println("Database connection error: \n" + e.getMessage());
            return null;
        }
        return conn;
    }









}
