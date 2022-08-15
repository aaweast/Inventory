import java.rmi.server.ExportException;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.HashMap;

/*
CSCE 315
9-25-2019 Original
2/7/2020 Update for AWS
 */
public class jdbcpostgreSQL 
{

  public static String GetDateTime() 
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    return dateFormat.format(date);
  }

  public static String ConvertTableToString(ResultSet result)
  {

    String tableString = "";

    try
    {
      // Now store the entire table in a single String
      ResultSetMetaData resultSetModified = result.getMetaData();
      int columnsNumber = resultSetModified.getColumnCount();
    
      /* for (int i = 1; i <= columnsNumber; i++) 
      {
          if (i > 1) 
              tableString += " | ";
    
          tableString += resultSetModified.getColumnName(i);
      }
    
      tableString += "\n"; */
    
      while (result.next()) 
      {
          for (int i = 1; i <= columnsNumber; i++) 
          {
              if (i > 1) 
                  tableString += " | ";
    
              tableString += result.getString(i);
          }
    
          tableString += "\n";
      }
    
      tableString += "\n";

    }
    catch (Exception e)
    {
      System.out.println("Error accessing Database\n" + e);
    }

    return tableString;
  }

  public static ResultSet executeQuery(String command)
  {
    //dbSetup hides my username and password
    dbSetup my = new dbSetup();
    //Building the connection
    Connection conn = null;
    try 
    {
       conn = DriverManager.getConnection(
         "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315907_37db",
          my.user, my.pswd);
    } 
    catch (Exception e) 
    {
       e.printStackTrace();
       System.err.println(e.getClass().getName()+": "+e.getMessage());
       System.exit(0);
    }//end try catch

    ResultSet result = null;

    try
    {
      Statement stmt = conn.createStatement();
      result = stmt.executeQuery(command);

      conn.close();

    }
    catch (Exception e)
    {
      System.out.println("Error accessing Database\n" + e);
      System.exit(-1);
    }
    
    return result;
  }

  public static void executeUpdate(String command)
  {
    //dbSetup hides my username and password
    dbSetup my = new dbSetup();
    //Building the connection
    Connection conn = null;
    try 
    {
       conn = DriverManager.getConnection(
         "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315907_37db",
          my.user, my.pswd);
    } 
    catch (Exception e) 
    {
       e.printStackTrace();
       System.err.println(e.getClass().getName()+": "+e.getMessage());
       System.exit(0);
    }//end try catch

    int result = 0;

    try
    {
      // Runs command
      Statement stmt = conn.createStatement();
      result = stmt.executeUpdate(command);

      // Checks to see if update was successful
      if (result == 0)
      {
        System.out.println("Update to database failed");
        System.exit(-1);
      }

      conn.close();

    }
    catch (Exception e)
    {
      System.out.println("Error accessing Database\n" + e);
      System.exit(-1);
    }
  }

  public static String ViewTable(String table, String orderBy) 
  {
    String tableString = "";
    String command = "SELECT * FROM " + table + " ORDER BY " + orderBy + ";";

    // Runs command and returns the string
    ResultSet result = executeQuery(command);
    tableString = ConvertTableToString(result);
    return tableString;

  }

  public static String ViewMenu()
  {
    return ViewTable("menu_key","menu_id");
  }

  public static String ViewPartsOfMenu()
  {
    return ViewTable("parts_of_menu","menu_id");
  }

  public static String ViewInventory()
  {
    return ViewTable("inventory","sku");
  }

  public static String restock()
  {
    String tableString = "";
    String command = "SELECT sku, name, quantity, (min_quant - quantity) AS needed, storage_type FROM inventory WHERE min_quant > quantity;";
    ResultSet result = executeQuery(command);
    tableString = ConvertTableToString(result);
    return tableString;

  }

  public static void restockOrders()
  {
    // String tableString = "";
    String command = "UPDATE inventory SET quantity = min_quant WHERE min_quant > quantity;";
    // String command2 = "SELECT sku, name, quantity, min_quant, storage_type FROM inventory WHERE min_quant > quantity;";
    executeUpdate(command);
    // ResultSet result = executeQuery(command);
    // tableString = ConvertTableToString(result);
    // return tableString;

  }

  public static void InsertIntoTable(String table, String values)
  {
    String command = "INSERT INTO " + table + " VALUES (" + values + ");";
    executeUpdate(command);

  }

  public static void AddMenuItem(String menu_id, String name, String description, String newPrice)
  {

    String values = menu_id + ", '" + name + "', '" + description + "', " + newPrice;

    InsertIntoTable("menu_key",values);
  }

  public static void AddPartsOfMenu(String menu_id, String quantity, String sku, String quantityType)
  {
    String values = menu_id + ", " + quantity + ", '" + sku + "', '" + quantityType + "'";

    InsertIntoTable("parts_of_menu", values);
  }

  public static void UpdateMenuPrice(String newPrice, String menu_id)
  {
    String command = "UPDATE menu_key SET price = " + Float.valueOf(newPrice) + " WHERE menu_id = " + Integer.valueOf(menu_id) + ";";
    executeUpdate(command);    
  }

  public static void UpdatePartsOfMenu(String quantity, String menu_id, String sku)
  {
    String command = "UPDATE parts_of_menu SET quantity = " + quantity + " WHERE menu_id = " + menu_id + " AND sku = '" + sku + "';";
    executeUpdate(command);
  }

  public static void UpdateInventoryAmount(String quantity, String sku)
  {
    String command = "UPDATE inventory SET quantity = " + quantity + " WHERE sku = '" + sku + "';";
    executeUpdate(command);
  }

  public static void UpdateFillAmount(String quantity, String sku)
  {
    String command = "UPDATE inventory SET min_quant = " + quantity + " WHERE sku = '" + sku + "';";
    executeUpdate(command);
  }

  public static void UpdateOrders(String menuID, String quantityInput)
  {
    int menu_id = Integer.valueOf(menuID);
    float quantity = Float.valueOf(quantityInput);

    try
    {
      String day = GetDateTime();
  
      // Initalize variables
      int size = 0;
      String quantityString = "0";
      double orderTotalForDay = 0;
      double priceOfMenuItem = 0.0;
  
      //create an SQL statement
      String command = "SELECT quantity FROM order_information WHERE menu_id = " + menu_id + " AND day = '" + day + "';";
      //send statement to DBMS
      ResultSet result = executeQuery(command);
  
      // Determine if the menu_id quantity is already in the database for the day
      while (result.next()) 
      {
        quantityString = result.getString("quantity");
        size++;
      }
  
      // If the menu_id is not in database for the day, add the row
      if (size == 0)
      {
        command = "INSERT INTO order_information VALUES (" + menu_id + ", 0, '" + day + "');";
        executeUpdate(command);
      }
  
      // Determine if we need to add a row for the orders_for_day
      command = "SELECT total FROM orders_for_day WHERE day = '" + day + "';";
      result = executeQuery(command);
  
      size = 0;
      while (result.next()) 
      {
        orderTotalForDay = Double.valueOf(result.getString("total"));
        size++;
      }
  
      // If the orders_for_day is not in database for the day, add the row
      if (size == 0)
      {
        command = "INSERT INTO orders_for_day VALUES ('" + day + "', 0.0);";
        executeUpdate(command);
      }
  
      // Add the data to the database
      command = "UPDATE order_information SET quantity = " + (Integer.valueOf(quantityString) + quantity) + " WHERE menu_id = " + menu_id + " AND day = '" + day + "';";
      executeUpdate(command);
      
      // Extract the current order total
      command = "SELECT total FROM orders_for_day WHERE day = '" + day + "';";
      result = executeQuery(command);
      
      result.next();
      orderTotalForDay = Double.valueOf(result.getString("total"));
  
      // Extract the price for the item ordered
      command = "SELECT price FROM menu_key WHERE menu_id = " + menu_id + ";";
      result = executeQuery(command);
  
      result.next();
      priceOfMenuItem = Double.valueOf(result.getString("price"));
  
      // Increase the total for the day
      orderTotalForDay += (quantity * priceOfMenuItem);
  
      command = "UPDATE orders_for_day SET total = " + orderTotalForDay + " WHERE day = '" + day + "';";
      executeUpdate(command);
    }
    catch (Exception e)
    {
      System.out.print(e);
      System.exit(-1);
    }
  }

  public static void UpdateInventoryWithOrders(String date)
  {
    try
    {
      //dbSetup hides my username and password
      dbSetup my = new dbSetup();
      //Building the connection
      Connection conn = null;
      try 
      {
        conn = DriverManager.getConnection(
          "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315907_37db",
            my.user, my.pswd);
      } 
      catch (Exception e) 
      {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
      }//end try catch

      Statement stmt1 = conn.createStatement();
      Statement stmt2 = conn.createStatement();
      Statement stmt3 = conn.createStatement();

      String command = "SELECT * FROM order_information WHERE day = '" + date + "';";
      ResultSet partsOfMenu;
      String menuId;
      int quantityOfMenuId;
      String sku;
      Float quantityForPartOfMenu;
      Float subtractFromInventory;
  
      ResultSet resultSet = stmt1.executeQuery(command);
  
      // Iterate through all the orders for each menu_id
      while (resultSet.next()) 
      {
        // Extract the menu_id
        menuId = resultSet.getString("menu_id");
        quantityOfMenuId = Integer.valueOf(resultSet.getString("quantity"));
        command = "SELECT * FROM parts_of_menu WHERE menu_id = " + menuId + ";";
  
        // Grab all the parts of that menu_id
        partsOfMenu = stmt2.executeQuery(command);
  
        // Iterate through the parts of the menu_id
        while (partsOfMenu.next())
        {
  
          sku = partsOfMenu.getString("sku");
          quantityForPartOfMenu = Float.valueOf(partsOfMenu.getString("quantity"));
  
          subtractFromInventory = quantityForPartOfMenu * quantityOfMenuId;
  
          // Subtracts the quantities from the inventory
          command = "UPDATE inventory SET quantity = (quantity - " + subtractFromInventory + ") WHERE sku = '" + sku + "';";
          stmt3.executeUpdate(command);
  
        }
      }

      conn.close();

    }
    catch (Exception e)
    {
      System.out.println(e);
    }

  }

  public static String OrderPopularity(String startDate, String endDate)
  {
      String command = "SELECT menu_id, SUM(quantity) AS total_quantity FROM order_information WHERE day <= '" + endDate + 
                       "' AND day >= '" + startDate + "' GROUP BY menu_id ORDER BY total_quantity DESC;";

      return ConvertTableToString(executeQuery(command));

  }

  public static String orderTrends(String startDate1, String endDate1, String startDate2, String endDate2)
  {
      String command1 = "SELECT menu_id, SUM(quantity) AS total_item_quantity FROM order_information WHERE day <= '" + endDate1 + 
                       "' AND day >= '" + startDate1 + "' GROUP BY menu_id ORDER BY menu_id;";

      String command2 = "SELECT menu_id, SUM(quantity) AS total_item_quantity FROM order_information WHERE day <= '" + endDate2 + 
                       "' AND day >= '" + startDate2 + "' GROUP BY menu_id ORDER BY menu_id;";
     
      String command3 = "SELECT price FROM menu_key ORDER BY menu_id;";

      String command4 = "SELECT SUM(total) AS total_revenue FROM orders_for_day WHERE day <= '" + endDate1 + 
                       "' AND day >= '" + startDate1 + "';";

      String command5 = "SELECT SUM(total) AS total_revenue FROM orders_for_day WHERE day <= '" + endDate2 + 
                       "' AND day >= '" + startDate2 + "';";
                  
         
      try 
      {
        ResultSet firstPeriodItemTotals = executeQuery(command1);
        ResultSet secondPeriodItemTotals = executeQuery(command2);
        ResultSet menuPrices = executeQuery(command3);
        ResultSet firstPeriodRevenue = executeQuery(command4);
        ResultSet secondPeriodRevenue = executeQuery(command5);  

        firstPeriodRevenue.next();
        Double firstRevenue = Double.valueOf(firstPeriodRevenue.getString("total_revenue"));   

        secondPeriodRevenue.next();
        Double secondRevenue = Double.valueOf(secondPeriodRevenue.getString("total_revenue")); 

        String command6 = "UPDATE menu_key SET trend_percentage = 0.0";
        executeUpdate(command6);

        while (menuPrices.next())
        {
          firstPeriodItemTotals.next();
          secondPeriodItemTotals.next();

          Double itemQuantityFirst = firstPeriodItemTotals.getDouble("total_item_quantity");

          Double itemQuantitySecond = secondPeriodItemTotals.getDouble("total_item_quantity");

          Double itemPrice = menuPrices.getDouble("price");

          Double itemTotalRevenueFirst = itemQuantityFirst * itemPrice;

          Double itemTotalRevenueSecond = itemQuantitySecond * itemPrice;

          Double itemPercentageOfTotalFirst = (itemTotalRevenueFirst / firstRevenue) * 100;

          Double itemPercentageOfTotalSecond = (itemTotalRevenueSecond / secondRevenue) * 100;

          Double trendPercentage = itemPercentageOfTotalSecond - itemPercentageOfTotalFirst;

          String command7 = "UPDATE menu_key SET trend_percentage = " + trendPercentage +" WHERE menu_id = " + firstPeriodItemTotals.getInt("menu_id");

          executeUpdate(command7);

        }

      }

      catch(Exception e)
      {
        System.out.println(e);
      }

      String command8 = "SELECT menu_id, trend_percentage FROM menu_key ORDER BY trend_percentage DESC;";

      return ConvertTableToString(executeQuery(command8));

  }

  public static void InventoryUsage(String startDate, String endDate)
  {
    try
    {
      //dbSetup hides my username and password
      dbSetup my = new dbSetup();
      //Building the connection
      Connection conn = null;
      try 
      {
        conn = DriverManager.getConnection(
          "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315907_37db",
            my.user, my.pswd);
      } 
      catch (Exception e) 
      {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
      }//end try catch

      Statement stmt1 = conn.createStatement();
      Statement stmt2 = conn.createStatement();
      Statement stmt3 = conn.createStatement();

      String command = "SELECT menu_id, SUM(quantity) AS total_quantity FROM order_information WHERE day <= '" + endDate + 
                       "' AND day >= '" + startDate + "' GROUP BY menu_id ORDER BY total_quantity DESC;";

      ResultSet partsOfMenu;
      String menuId;
      int quantityOfMenuId;
      String sku;
      Float quantityForPartOfMenu;
      Float itemUsageForID;
  
      ResultSet resultSet = stmt1.executeQuery(command);

      command = "UPDATE inventory SET usage = 0.0";
      executeUpdate(command);
      command = "UPDATE inventory SET times_ordered = 0.0";
      executeUpdate(command);
  
      // Iterate through all the orders for each menu_id
      while (resultSet.next()) 
      {
        // Extract the menu_id
        menuId = resultSet.getString("menu_id");
        quantityOfMenuId = Integer.valueOf(resultSet.getString("total_quantity"));
        command = "SELECT * FROM parts_of_menu WHERE menu_id = " + menuId + ";";
  
        // Grab all the parts of that menu_id
        partsOfMenu = stmt2.executeQuery(command);
  
        // Iterate through the parts of the menu_id
        while (partsOfMenu.next())
        {
  
          sku = partsOfMenu.getString("sku");
          quantityForPartOfMenu = Float.valueOf(partsOfMenu.getString("quantity"));
  
          itemUsageForID = quantityForPartOfMenu * quantityOfMenuId;
          command = "UPDATE inventory SET times_ordered = (" + quantityOfMenuId + "+ times_ordered) WHERE sku = '" + sku + "';";
          stmt3.executeUpdate(command);

          command = "UPDATE inventory SET usage = (" + itemUsageForID + "+ usage) WHERE sku = '" + sku + "';";
          stmt3.executeUpdate(command);
        }
      }

      conn.close();

    }
    catch (Exception e)
    {
      System.out.println(e);
    }

  }

  public static String TimesOrdered(String startDate, String endDate)
  {
    InventoryUsage(startDate,endDate);
    String command = "SELECT sku,name,times_ordered FROM inventory WHERE times_ordered > 0;";
    return ConvertTableToString(executeQuery(command));
  }

  public static String Usage(String startDate, String endDate)
  {
    InventoryUsage(startDate,endDate);
    String command = "SELECT sku,name,usage FROM inventory WHERE usage > 0;";
    return ConvertTableToString(executeQuery(command));
  }

  public static void main(String args[]) 
  {
    //System.out.println(orderTrends("2022-01-03", "2022-01-03", "2022-01-04", "2022-01-04"));
    //System.out.println(TimesOrdered("2022-03-04","2022-03-04"));
    //System.out.println(OrderPopularity("2022-01-05","2022-01-12"));
    //UpdateOrders("515","5");
    //UpdateInventoryWithOrders("2022-02-27");
  }//end main
}//end Class
