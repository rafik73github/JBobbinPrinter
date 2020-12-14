package pl.jbobbinprinter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YarnSQL {

    public static void createYarnsTable(Connection conn){

        Statement stat;
        try{
            stat = conn.createStatement();
            String tableSQL = "CREATE TABLE IF NOT EXISTS yarns"
                    + "(ID_YARN INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL,"
                    + "YARN_NAME CHAR(50)            NOT NULL,"
                    + "YARN_TYPE CHAR(50)            NOT NULL,"
                    + "YARN_WEIGHT CHAR(10)          NOT NULL,"
                    + "YARN_ARCHIVED INT)";
            stat.executeUpdate(tableSQL);
            stat.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void addYarnToSQL(Connection conn, Yarn yarn){
        Statement stat;
        try{
            stat = conn.createStatement();
            String addYarnString = "INSERT INTO yarns (YARN_NAME, YARN_TYPE, YARN_WEIGHT, YARN_ARCHIVED) "
                    + "VALUES ("
                    + "'" + yarn.getYarnName() + "',"
                    + "'" + yarn.getYarnType() + "',"
                    + "'" + yarn.getYarnWeight() + "',"
                    + 0 + ")";
            stat.executeUpdate(addYarnString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

       public static void getYarnFromSQLToCombo(Connection conn, int archived, JComboBox<Yarn> jCB){
        Statement stat;

        try{
            stat = conn.createStatement();
            String getYarnString = "SELECT * FROM yarns WHERE YARN_ARCHIVED = " + archived + " ORDER BY YARN_NAME ASC";

            ResultSet resultSet = stat.executeQuery(getYarnString);

            while (resultSet.next()){

                jCB.addItem(new Yarn(resultSet.getInt("ID_YARN"), resultSet.getString("YARN_NAME"),
                        resultSet.getString("YARN_TYPE"), resultSet.getString("YARN_WEIGHT"), resultSet.getInt("YARN_ARCHIVED")));

            }
            resultSet.close();
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void updateYarnFromSQL(Connection conn, int key, Yarn yarn, int archived){
        Statement stat;
        try{
            stat = conn.createStatement();
            String updateYarnString = "UPDATE yarns "
                    + "SET "
                    + "YARN_NAME = '" + yarn.getYarnName() + "',"
                    + "YARN_TYPE = '" + yarn.getYarnType() + "',"
                    + "YARN_WEIGHT = '" + yarn.getYarnWeight() + "',"
                    + "YARN_ARCHIVED = " + archived +
                    " WHERE ID_YARN = " + key;

            stat.executeUpdate(updateYarnString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteYarnFromSQL(Connection conn, int key){
        Statement stat;
        try{
            stat = conn.createStatement();
            String deleteYarnString = "DELETE FROM yarns WHERE ID_YARN = " + key;

            stat.executeUpdate(deleteYarnString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
