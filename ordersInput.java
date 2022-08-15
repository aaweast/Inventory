import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class ordersInput {
    public static void main(String[] args) {

        Connection conn = null;
        String teamNumber = "37";
        String sectionNumber = "907";
        String dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        String userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
        String userPassword = "password";

        //Connecting to the database
        try 
        {
            conn = DriverManager.getConnection(dbConnectionString,userName, userPassword);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");

        String file = "FourWeekSales - FirstWeekSales.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            while((line = br.readLine()) != null) 
            {
                String[] values = line.split(",");

                if (values.length > 4 && values[3].matches("Total"))
                {

                    try
                    {
                        //create a statement object
                        Statement stmt = conn.createStatement();
                        
                        String sqlStatement = "INSERT INTO orders_for_day (day, total) VALUES ('"+values[0]+"', " + Float.valueOf((values[4]))+");";
                
                        int result = stmt.executeUpdate(sqlStatement);
                        
                    } catch (Exception e){
                        e.printStackTrace();
                        System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }

                }
                else if (values.length == 3) 
                {
                    try
                    {
                        //create a statement object
                        Statement stmt = conn.createStatement();
                        
                        String sqlStatement = "INSERT INTO order_information (day, menu_id, quantity) VALUES ('"+values[0]+"', " + Integer.valueOf((values[1]))+", " + Integer.valueOf((values[2]))+");";
                    
                        int result = stmt.executeUpdate(sqlStatement);
                        
                    } 
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }
                }      
            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }


    }
}