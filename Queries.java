import java.sql.*;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException; 
import java.io.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
CSCE 315
9-27-2021 Lab Code we are using
 */
public class Queries 
{

    // Commands to run this script
    // This will compile all java files in this directory
    // javac *.java
    // This command tells the file where to find the postgres jar which it needs to
    // execute postgres commands, then executes the code
    // Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
    // Mac/Linux: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL

    // MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE

    

    //For queries that return object results
    public static ResultSet ExecuteQuery(Connection conn, String command)
    {
        try 
        {
            // create a statement object
            Statement stmt = conn.createStatement();

            // Running a query
            String sqlStatement = command;

            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            return result;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
    
    //For queries that return integers
    public static int ExecuteUpdate(Connection conn, String command)
    {
        try 
        {
            // create a statement object
            Statement stmt = conn.createStatement();

            // Running a query
            String sqlStatement = command;

            // send statement to DBMS
            int result = stmt.executeUpdate(sqlStatement);

            System.out.println(result);

            return result;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }

    // public static int[] GetSKUList(Connection conn, int menuID)
    // {
    //     ResultSet result = ExecuteQuery(conn, "SELECT * FROM parts_of_menu WHERE menu_id='" + Integer.toString(menuID) + "';");
    //     result.getString("sku");

    // }

    public static Boolean PopulateDatabaseWithOrder(Connection conn, String filename)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename)); 
            br.readLine();              //Throw away title line
            String headersString = br.readLine();              //Save column headers
            String[] headers = headersString.split(",");

            // System.out.println(headersString);

            String line = "";
            String delimiter = ",";
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                // System.out.println(line);
                String type = "";
                String name = "";
                String sku = "";
                String soldBy = "";
                String storedBy = "";
                float quantity = 0.0f;

                String[] row = line.split(delimiter);    // use comma as separator  
                if (row.length < 5) {continue;}    //Skip if not enough data
                if (row.length == 0) {break;}
                if (row[0] == "") {break;}
                for (int i = 0; i < headers.length; i++)
                {
                    // System.out.println(headers[i]);
                    if(headers[i].matches("Type"))
                    {
                        type = row[i];
                    }
                    else if(headers[i].matches("Description"))
                    {
                        name = row[i];
                    }
                    else if(headers[i].matches("SKU"))
                    {
                        sku = row[i];
                    }
                    else if(headers[i].matches("sold by"))
                    {
                        soldBy = row[i];
                    }
                    else if(headers[i].matches("stored by"))
                    {
                        storedBy = row[i];
                    }
                    else if(headers[i].matches("invenquant"))
                    {
                        if(row[i] == "") {break;}
                        quantity = Float.valueOf(row[i]);
                    }
                }
                ExecuteUpdate(conn, "INSERT INTO inventory (type, name, sku, sold_by, storage_type, quantity) VALUES ('"
                 + type + "', '" + name + "', '" + sku + "', '" + soldBy + "', '" + storedBy + "', " + quantity + ");");
                // System.out.println(type + ", " + name + ", " + sku + ", " + soldBy + ", " + storedBy + ", " + quantity + ", ");
                
            }  
        }
        catch (IOException e)   
        {  
            e.printStackTrace();  
            return false;
        }  
        return true;
    }

    public static Boolean PopulateItemTable(Connection conn, String filename)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename)); 
            br.readLine();              //Throw away title line
            String headersString = br.readLine();              //Save column headers
            String[] headers = headersString.split(",");

            // System.out.println(headersString);

            String line = "";
            String delimiter = ",";
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                // System.out.println(line);
                String sku = "";
                Float price = 0.0f;
                String storageLocation = "";
                String expirationDate = "";
                String name = "";
                int number = 0;

                String[] row = line.split(delimiter);    // use comma as separator  
                if (row.length < 5) {continue;}    //Skip if not enough data
                if (row.length == 0) {break;}
                if (row[0] == "") {break;}
                for (int i = 0; i < headers.length; i++)
                {
                    // System.out.println(headers[i]);
                    if(headers[i].matches("SKU"))
                    {
                        sku = row[i];
                    }
                    else if(headers[i].matches("price"))
                    {
                        price = Float.valueOf(row[i]);
                    }
                    else if(headers[i].matches("storage location"))
                    {
                        storageLocation = row[i];
                    }
                    else if(headers[i].matches("expiration date"))
                    {
                        expirationDate = row[i];
                    }
                    else if(headers[i].matches("Description"))
                    {
                        name = row[i];
                    }
                    else if (headers[i].matches("Quantity"))
                    {
                        number = Integer.parseInt(row[i]);
                    }
                }
                for(int i = 0; i < number; i++)
                {
                    ExecuteUpdate(conn, "INSERT INTO item (sku, price, storage_location, expiration_date, name) VALUES ('"
                    + sku + "', " + price + ", '" + storageLocation + "', '" + expirationDate + "', '" + name + "');");
                }
                // System.out.println(type + ", " + name + ", " + sku + ", " + soldBy + ", " + storedBy + ", " + quantity + ", ");
                
            }  
        }
        catch (IOException e)   
        {  
            e.printStackTrace();  
            return false;
        }  
        return true;
    }

    public static Boolean PopulateSupplyOrderDetails(Connection conn, String filename)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename)); 
            String[] firstLine = br.readLine().split(",");              //Get title line
            String date = firstLine[6];
            String headersString = br.readLine();              //Save column headers
            String[] headers = headersString.split(",");

            // System.out.println(headersString);

            String line = "";
            String delimiter = ",";
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                // System.out.println(line);
                String type = "";
                String description = "DUMMY";
                String sku = "";
                int orderQuantity = 0;
                int delivered = 0;
                String soldBy = "";
                String deliveredBy = "";
                int quantityMult = 0;
                Float price = 0.0f;
                Float extended = 0.0f;
                String category = "";
                int invoiceLine = 0;
                String detailedDesc = "";

                String[] row = line.split(delimiter);    // use comma as separator  
                if (row.length < 5) {continue;}    //Skip if not enough data
                if (row.length == 0) {break;}
                if (row[0] == "") {break;}
                for (int i = 0; i < headers.length; i++)
                {
                    // System.out.println(headers[i]);
                    if(headers[i].matches("Type"))
                    {
                        type = row[i];
                    }
                    else if(headers[i].matches("Description"))
                    {
                        description = row[i];
                    }
                    else if(headers[i].matches("SKU"))
                    {
                        sku = row[i];
                    }
                    else if(headers[i].matches("Quantity"))
                    {
                        orderQuantity = Integer.parseInt(row[i]);
                    }
                    else if(headers[i].matches("delivered"))
                    {
                        delivered = Integer.parseInt(row[i]);
                    }
                    else if(headers[i].matches("sold by"))
                    {
                        soldBy = row[i];
                    }
                    else if(headers[i].matches("delivered by"))
                    {
                        deliveredBy = row[i];
                    }
                    else if(headers[i].matches("quantity Multiplyer"))
                    {
                        quantityMult = Integer.parseInt(row[i]);
                    }
                    else if(headers[i].matches("price"))
                    {
                        price = Float.valueOf(row[i]);
                    }
                    else if(headers[i].matches("Extended"))
                    {
                        extended = Float.valueOf(row[i]);
                    }
                    else if(headers[i].matches("category"))
                    {
                        category = row[i];
                    }
                    else if(headers[i].matches("Invoice line"))
                    {
                        invoiceLine = Integer.parseInt(row[i]);
                    }
                    else if(headers[i].matches("Detailed description"))
                    {
                        detailedDesc = row[i];
                    }
                }
                ExecuteUpdate(conn, "INSERT INTO supply_order_details (type, description, sku, order_quantity, delivered, sold_by, delivered_by, quantity_mult, price, extended, category, invoice_line, detailed_desc, date) VALUES ('"
                + type + "', '" + description + "', '" + sku + "', " + orderQuantity + ", " + delivered + ", '" + soldBy + "', '" + deliveredBy + "', " + quantityMult + ", " + price + ", " + extended + ", '" + category + "', " + invoiceLine + ", '" + detailedDesc + "', '" + date + "');");
                
                // System.out.println(type + ", " + name + ", " + sku + ", " + soldBy + ", " + storedBy + ", " + quantity + ", ");
                
            }  
        }
        catch (IOException e)   
        {  
            e.printStackTrace();  
            return false;
        }  
        return true;
    }

    public static Boolean LoadMenuInfo(Connection conn, String file)
    {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {

            int counter = 0;

            while((line = br.readLine()) != null) 
            {
                String[] values = line.split(",");

                if (counter > 0)
                {

                    try
                    {
                        //create a statement object
                        Statement stmt = conn.createStatement();
                        
                        String sqlStatement = "INSERT INTO parts_of_menu (menu_id, sku, quantity, quantity_type) VALUES (" + Integer.valueOf(values[0]) + ", '" + (values[1]) + "', " +
                            Float.valueOf(values[2]) + ", '" + values[3] + "');";
                
                        int result = stmt.executeUpdate(sqlStatement);
                        
                    } catch (Exception e){
                        e.printStackTrace();
                        System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }

                }
                
                counter++;

            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }

        return true;
    }

    public static Boolean RunQueries(Connection conn, String file) 
    {

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {

            int counter = 0;

            while((line = br.readLine()) != null) 
            {
                String[] values = line.split("\n");

                if (counter > 0)
                {

                    try
                    {
                        //create a statement object
                        Statement stmt = conn.createStatement();
                        
                        String sqlStatement = values[0];

                        System.out.println(sqlStatement);
                
                        ResultSet result = stmt.executeQuery(sqlStatement);

                        printTable(result);
                        
                    } catch (Exception e){
                        e.printStackTrace();
                        System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }

                }
                
                counter++;

            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;

    }

    public static void printTable(ResultSet result) throws SQLException
    {

        ResultSetMetaData resultSetModified = result.getMetaData();
        int columnsNumber = resultSetModified.getColumnCount();
    
        for (int i = 1; i <= columnsNumber; i++) 
        {
            if (i > 1) 
                System.out.print(" | ");

            System.out.print(resultSetModified.getColumnName(i));
        }

        System.out.println("");
    
        while (result.next()) 
        {
            for (int i = 1; i <= columnsNumber; i++) 
            {
                if (i > 1) 
                    System.out.print(" | ");

                System.out.print(result.getString(i));
            }

            System.out.println("");
        }

        System.out.println();

    }

    public static void main(String args[]) 
    {

        // Building the connection with credentials
        Connection conn = null;
        String teamNumber = "37";
        String sectionNumber = "907";
        String dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        String userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
        String userPassword = "password";
        String filename = "";

        // Connecting to the database
        try 
        {
            conn = DriverManager.getConnection(dbConnectionString, userName, userPassword);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");

        // loadMenuInfo(conn, "Menu Information - Sheet1.csv");

        //-------------------------------------------------------------------------------------//
        // Connection Open
        //-------------------------------------------------------------------------------------//

        // PopulateDatabaseWithOrder(conn, "FirstDayOrder.csv");
        // PopulateItemTable(conn, "FirstDayOrder.csv");
        // LoadMenuInfo(conn, "Menu Information - Sheet1.csv");


        // PopulateSupplyOrderDetails(conn, "FirstDayOrder.csv");   
        RunQueries(conn, "runnableQueries.txt");     

        //-------------------------------------------------------------------------------------//
        // Closing Connection
        //-------------------------------------------------------------------------------------//

        // closing the connection
        try 
        {
            conn.close();
            System.out.println("Connection Closed.");
        } 
        catch (Exception e) 
        {
            System.out.println("Connection NOT Closed.");
        } // end try catch
    }// end main
}// end Class
