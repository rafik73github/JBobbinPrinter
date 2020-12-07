package pl.jbobbinprinter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

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

    public static void addYarnToSQL(Connection conn, String table, Yarn yarn){
        Statement stat;
        try{
            stat = conn.createStatement();
            String addYarnString = "INSERT INTO " + table + "(YARN_NAME, YARN_TYPE, YARN_WEIGHT, YARN_ARCHIVED) "
                    + "VALUES ("
                    + "'" + yarn.getYarnName() + "',"
                    + "'" + yarn.getYarnType() + "',"
                    + "'" + yarn.getYarnWeight() + "',"
                    + 0 + ")";
            stat.executeUpdate(addYarnString);
            stat.close();
            conn.close();
            System.out.println("Command: " + addYarnString + " executed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Yarn> getYarnFromSQL(Connection conn, String table, int archived){
        Map<Integer, Yarn> result = new TreeMap<>();
        int counter = 0;
        Statement stat;

        try{
            stat = conn.createStatement();
            String getYarnString = "SELECT * FROM " + table + " WHERE YARN_ARCHIVED = " + archived + " ORDER BY YARN_NAME ASC";

            ResultSet resultSet = stat.executeQuery(getYarnString);

            while (resultSet.next()){
                result.put(counter,new Yarn(resultSet.getInt("ID_YARN"), resultSet.getString("YARN_NAME"),
                        resultSet.getString("YARN_TYPE"), resultSet.getString("YARN_WEIGHT"), resultSet.getInt("YARN_ARCHIVED")));
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
