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

    public static void getYarnTypeFromSQLToList(Connection conn, int archived, JList<YarnTypes> jT){

        Statement stat;
        DefaultListModel<YarnTypes> dLM = new DefaultListModel<>();

        try{
            stat = conn.createStatement();
            String getYarnTypeString = "SELECT * FROM yarns_types WHERE YARN_TYPE_ARCHIVED = " + archived + " ORDER BY YARN_TYPE ASC";

            ResultSet resultSet = stat.executeQuery(getYarnTypeString);

            while (resultSet.next()){

                dLM.addElement(new YarnTypes(resultSet.getInt("ID_YARN_TYPE"), resultSet.getString("YARN_TYPE"),
                        resultSet.getInt("YARN_TYPE_ARCHIVED")));

            }

            jT.setModel(dLM);
            resultSet.close();
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void updateYarnTypeFromSQL(Connection conn, YarnTypes yarnType){
        Statement stat;
        try{
            stat = conn.createStatement();
            String updateYarnTypeString = "UPDATE yarns_types "
                    + "SET "
                    + "YARN_TYPE = '" + yarnType.getType() + "',"
                    + "YARN_TYPE_ARCHIVED = " + yarnType.getTypeArchived() +
                    " WHERE ID_YARN_TYPE = " + yarnType.getTypeId();

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

    public static boolean checkIfYarnTypeExist(Connection conn, String yarnTypeToCheck){
        int counter = 0;
        boolean result = false;
        Statement stat;
        try{
            stat = conn.createStatement();
            String checkYarnTypeString = "SELECT * FROM yarns_types WHERE YARN_TYPE = '" + yarnTypeToCheck + "'";

            ResultSet resultSet = stat.executeQuery(checkYarnTypeString);

            while (resultSet.next()){
                counter++;
            }
            if(counter > 0){result = true;}

            resultSet.close();
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

}
