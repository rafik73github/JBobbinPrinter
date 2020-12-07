package pl.jbobbinprinter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

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
            System.out.println("Command: " + addYarnTypeString + " executed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, YarnTypes> getYarnTypeFromSQL(Connection conn, int archived){
        Map<Integer, YarnTypes> result = new TreeMap<>();
        int counter = 0;
        Statement stat;

        try{
            stat = conn.createStatement();
            String getYarnTypeString = "SELECT * FROM yarns_types WHERE YARN_TYPE_ARCHIVED = " + archived + " ORDER BY YARN_TYPE ASC";

            ResultSet resultSet = stat.executeQuery(getYarnTypeString);

            while (resultSet.next()){
                result.put(counter,new YarnTypes(resultSet.getInt("ID_YARN_TYPE"), resultSet.getString("YARN_TYPE"),
                        resultSet.getInt("YARN_TYPE_ARCHIVED")));
                counter++;
            }
            resultSet.close();
            stat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

}
