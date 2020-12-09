package pl.jbobbinprinter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class YarnWeightSQL {

    public static void createYarnsWeightTable(Connection conn){

        Statement stat;
        try{
            stat = conn.createStatement();
            String tableSQL = "CREATE TABLE IF NOT EXISTS yarns_weights"
                    + "(ID_YARN_WEIGHT INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL,"
                    + "YARN_WEIGHT CHAR(10)          NOT NULL,"
                    + "YARN_WEIGHT_ARCHIVED INT)";
            stat.executeUpdate(tableSQL);
            stat.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void addYarnWeightToSQL(Connection conn, YarnWeight yarnWeight){
        Statement stat;
        try{
            stat = conn.createStatement();
            String addYarnWeightString = "INSERT INTO yarns_weights (YARN_WEIGHT, YARN_WEIGHT_ARCHIVED) "
                    + "VALUES ("
                    + "'" + yarnWeight.getWeight() + "',"
                    + 0 + ")";
            stat.executeUpdate(addYarnWeightString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getYarnWeightFromSQL(Connection conn, int archived, JComboBox<YarnWeight> jCB){

        Statement stat;

        try{
            stat = conn.createStatement();
            String getYarnWeightString = "SELECT * FROM yarns_weights WHERE YARN_WEIGHT_ARCHIVED = " + archived + " ORDER BY YARN_WEIGHT ASC";

            ResultSet resultSet = stat.executeQuery(getYarnWeightString);

            while (resultSet.next()){

                jCB.addItem(new YarnWeight(resultSet.getInt("ID_YARN_WEIGHT"), resultSet.getString("YARN_WEIGHT"),
                        resultSet.getInt("YARN_WEIGHT_ARCHIVED")));

            }
            resultSet.close();
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void deleteYarnWeightFromSQL(Connection conn, int key){
        Statement stat;
        try{
            stat = conn.createStatement();
            String deleteYarnWeightString = "DELETE FROM yarns_weights WHERE ID_YARN_WEIGHT = " + key;

            stat.executeUpdate(deleteYarnWeightString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
