import java.sql.*;
import javax.swing.JFrame;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class checkoutInfo 
{

    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

    public void addItem(String item)
    {
        if (item == "Five Finger Original")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("5 finger original");
            newItem.add("6.50");
            table.add(newItem);
        }
        else if (item == "Four Finger Meal")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("4 finger original");
            newItem.add("5.50");
            table.add(newItem);

        }
        else if (item == "Three Finger Meal")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("three finger original");
            newItem.add("4.50");
            table.add(newItem);
            
        }
        else if (item == "Kid's Meal")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("kids meal");
            newItem.add("2.50");
            table.add(newItem);
        }
        else if (item == "Club Sandwich Meal")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Club Sandwich meal");
            newItem.add("5.75");
            table.add(newItem);            
        }
        else if (item == "Sandwich Meal Combo")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Sandwich meal combo");
            newItem.add("5.75");
            table.add(newItem);
        }
        else if (item == "Grilled Cheese Meal Combo")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Grill cheese meal combo");
            newItem.add("4.50");
            table.add(newItem);            
        }
        else if (item == "Family Pack")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("family pack");
            newItem.add("32.00");
            table.add(newItem);            
        }
        else if (item == "Chicken Finger (Single)")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Chicken finger");
            newItem.add("1.50");
            table.add(newItem);            
        }
        else if (item == "Sandwich Only")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("sandwich only");
            newItem.add("3.75");
            table.add(newItem);            
        }
        else if (item == "Club Sandwich Only")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Club Sandwich only");
            newItem.add("4.75");
            table.add(newItem);            
        }
        else if (item == "Grilled Cheese Sandwich Only")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Grill cheese sandwich only");
            newItem.add("3.50");
            table.add(newItem);
        }
        else if (item == "Layne's Sauce")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Laynes sauce");
            newItem.add(".10");
            table.add(newItem);           
        }
        else if (item == "Texas Toast")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("texas toast");
            newItem.add(".50");
            table.add(newItem);
        }
        else if (item == "Potato Salad")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("potato Salad");
            newItem.add("1.50");
            table.add(newItem);            
        }
        else if (item == "Crinkle Cut Fries")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Crinkle cut fries");
            newItem.add("1.75");
            table.add(newItem);            
        }
        else if (item == "Fountain Drink")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Fountain Drink");
            newItem.add("1.25");
            table.add(newItem);            
        }
        else if (item == "Bottle Drink")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Bottle drink");
            newItem.add("2.00");
            table.add(newItem);            
        }
        else if (item == "Gallon of Tea")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("gallon of tea");
            newItem.add("5.00");
            table.add(newItem);            
        }
        else if (item == "Impossible Chicken Tenders")
        {
            ArrayList<String> newItem = new ArrayList<String>();
            newItem.add("Impossible Chicken Tenders");
            newItem.add("6.50");
            table.add(newItem);            
        }
    }
}