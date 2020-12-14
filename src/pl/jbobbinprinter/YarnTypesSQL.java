package pl.jbobbinprinter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YarnTypesSQL {

    public static void createYarnsTypeTable(Connection conn){

        Statement stat;
        try{
            stat = conn.createStatement();
            String tableSQL = "CREATE TABLE IF NOT EXISTS yarns_types"
                    + "(ID_YARN_TYPE INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL,"
                    + "YARN_TYPE CHAR(50)          NOT NULL,"
                    + "YARN_TYPE_ARCHIVED INT)";
            stat.executeUpdate(tableSQL);
            stat.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void addYarnTypeToSQL(Connection conn, YarnTypes yarnTypes){
        Statement stat;
        try{
            stat = conn.createStatement();
            String addYarnTypeString = "INSERT INTO yarns_types (YARN_TYPE, YARN_TYPE_ARCHIVED) "
                    + "VALUES ("
                    + "'" + yarnTypes.getType() + "',"
                    + 0 + ")";
            stat.executeUpdate(addYarnTypeString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getYarnTypeFromSQLToCombo(Connection conn, int archived, JComboBox<YarnTypes> jCB){

        Statement stat;

        try{
            stat = conn.createStatement();
            String getYarnTypeString = "SELECT * FROM yarns_types WHERE YARN_TYPE_ARCHIVED = " + archived + " ORDER BY YARN_TYPE ASC";

            ResultSet resultSet = stat.executeQuery(getYarnTypeString);

            while (resultSet.next()){

                jCB.addItem(new YarnTypes(resultSet.getInt("ID_YARN_TYPE"), resultSet.getString("YARN_TYPE"),
                        resultSet.getInt("YARN_TYPE_ARCHIVED")));

            }
            resultSet.close();
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void updateYarnTypeFromSQL(Connection conn, int key, YarnTypes yarnType, int archived){
        Statement stat;
        try{
            stat = conn.createStatement();
            String updateYarnTypeString = "UPDATE yarns_types "
                    + "SET "
                    + "YARN_TYPE = '" + yarnType.getType() + "',"
                    + "YARN_TYPE_ARCHIVED = " + archived +
                    " WHERE ID_YARN_TYPE = " + key;

            stat.executeUpdate(updateYarnTypeString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteYarnTypeFromSQL(Connection conn, int key){
        Statement stat;
        try{
            stat = conn.createStatement();
            String deleteYarnTypeString = "DELETE FROM yarns_types WHERE ID_YARN_TYPE = " + key;

            stat.executeUpdate(deleteYarnTypeString);
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
