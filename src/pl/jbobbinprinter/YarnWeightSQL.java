package pl.jbobbinprinter;

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
            System.out.println("Command: " + addYarnWeightString + " executed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, YarnWeight> getYarnWeightFromSQL(Connection conn, int archived){
        Map<Integer, YarnWeight> result = new TreeMap<>();
        int counter = 0;
        Statement stat;

        try{
            stat = conn.createStatement();
            String getYarnWeightString = "SELECT * FROM yarns_weights WHERE YARN_WEIGHT_ARCHIVED = " + archived + " ORDER BY YARN_WEIGHT ASC";

            ResultSet resultSet = stat.executeQuery(getYarnWeightString);

            while (resultSet.next()){
                result.put(counter,new YarnWeight(resultSet.getInt("ID_YARN_WEIGHT"), resultSet.getString("YARN_WEIGHT"),
                        resultSet.getInt("YARN_WEIGHT_ARCHIVED")));
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
