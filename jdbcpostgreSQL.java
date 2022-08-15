import java.rmi.server.ExportException;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
    
      // Prints column names
      for (int i = 1; i <= columnsNumber; i++) 
      {
          if (i > 1) 
              tableString += " | ";
    
          tableString += resultSetModified.getColumnName(i);
      }
    
      tableString += "\n";
    
      // Prints all the rows
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
      // Run command
      Statement stmt = conn.createStatement();
      result = stmt.executeUpdate(command);

      // Check if command ran
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

    // Run Query and return string
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

  public static void UpdateOrders(String menuName, String quantityInput)
  {
    float quantity = Float.valueOf(quantityInput);

    try
    {
      //dbSetup hides my username and password
      dbSetup my = new dbSetup();
      //Building the connection
      Connection conn = null;
      conn = DriverManager.getConnection(
        "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315907_37db",
          my.user, my.pswd);

      Statement stmt = conn.createStatement();

      String day = GetDateTime();
  
      // Initalize variables
      int size = 0;
      String quantityString = "0";
      double orderTotalForDay = 0;
      double priceOfMenuItem = 0.0;
      int menu_id = 0;

      String command = "SELECT menu_id FROM menu_key WHERE name = '" + menuName + "';";
      ResultSet result = stmt.executeQuery(command);

      result.next();

      menu_id = Integer.valueOf(result.getString("menu_id"));
  
      //create an SQL statement
      command = "SELECT quantity FROM order_information WHERE menu_id = " + menu_id + " AND day = '" + day + "';";
      //send statement to DBMS
      result = stmt.executeQuery(command);
  
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
        stmt.executeUpdate(command);
      }
  
      // Determine if we need to add a row for the orders_for_day
      command = "SELECT total FROM orders_for_day WHERE day = '" + day + "';";
      result = stmt.executeQuery(command);
  
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
        stmt.executeUpdate(command);
      }
  
      // Add the data to the database
      command = "UPDATE order_information SET quantity = " + (Integer.valueOf(quantityString) + quantity) + " WHERE menu_id = " + menu_id + " AND day = '" + day + "';";
      stmt.executeUpdate(command);
      
      // Extract the current order total
      command = "SELECT total FROM orders_for_day WHERE day = '" + day + "';";
      result = stmt.executeQuery(command);
      
      result.next();
      orderTotalForDay = Double.valueOf(result.getString("total"));
  
      // Extract the price for the item ordered
      command = "SELECT price FROM menu_key WHERE menu_id = " + menu_id + ";";
      result = stmt.executeQuery(command);
  
      result.next();
      priceOfMenuItem = Double.valueOf(result.getString("price"));
  
      // Increase the total for the day
      orderTotalForDay += (quantity * priceOfMenuItem);
  
      command = "UPDATE orders_for_day SET total = " + orderTotalForDay + " WHERE day = '" + day + "';";
      stmt.executeUpdate(command);

      conn.close();

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
      String command = "SELECT * FROM order_information WHERE day = '" + date + "';";
      ResultSet partsOfMenu;
      String menuId;
      int quantityOfMenuId;
      String sku;
      Float quantityForPartOfMenu;
      Float subtractFromInventory;
  
      ResultSet result = executeQuery(command);
  
      // Iterate through all the orders for each menu_id
      while (result.next()) 
      {
        // Extract the menu_id
        menuId = result.getString("menu_id");
        quantityOfMenuId = Integer.valueOf(result.getString("quantity"));
        command = "SELECT * FROM parts_of_menu WHERE menu_id = " + menuId + ";";
  
        // Grab all the parts of that menu_id
        partsOfMenu = executeQuery(command);
  
        // Iterate through the parts of the menu_id
        while (partsOfMenu.next())
        {
  
          sku = partsOfMenu.getString("sku");
          quantityForPartOfMenu = Float.valueOf(partsOfMenu.getString("quantity"));
  
          subtractFromInventory = quantityForPartOfMenu * quantityOfMenuId;
  
          // Subtracts the quantities from the inventory
          command = "UPDATE inventory SET quantity = (quantity - " + subtractFromInventory + ") WHERE sku = '" + sku + "';";
          executeUpdate(command);
  
        }
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.exit(-1);
    }

  }

  public static void main(String args[]) 
  {
    UpdateOrders("texas toast","5");
    //UpdateInventoryWithOrders("2022-02-27");
  }//end main
}//end Class
