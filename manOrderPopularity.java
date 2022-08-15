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

import java.awt.event.*;   
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.ArrayList;
import java.util.Arrays;

public class manOrderPopularity extends JFrame 
{

    String tableString;

     // dispose old frame
    public void hideForm()
    {
        this.dispose();
    }

    // make button listener
    private class backButtonListener implements ActionListener 
    {
                
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            manInvGUI invGUI = new manInvGUI();
            hideForm();
        }
    }

    public manOrderPopularity(String tableString) 
    {

        this.tableString = tableString;

        String[] rows = tableString.split("\n");
        ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();

        for (String elem : rows) 
        {
            String[] entry = elem.split(" \\| ");
            ArrayList<String> t1 = new ArrayList<String>(Arrays.asList(entry));
            test.add(t1);
        }

        String[][] toView = test.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

        String[] columns = {"Menu ID","Quantity"};

        // set frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Order Popularity");
        this.getContentPane().setBackground(new Color(80,0,0));

        JTable inventoryTable = new JTable(toView,columns);  
        // set background
        inventoryTable.setBackground(Color.WHITE);
        inventoryTable.setForeground(new Color(80,0,0));
        Font inventoryTableFont  = new Font("Sans Serif", Font.PLAIN,  15);
        inventoryTable.setFont(inventoryTableFont);
        inventoryTable.setRowHeight(25);
        JScrollPane sp= new JScrollPane(inventoryTable); 

        // make scroll bar
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() 
        {
            protected void configureScrollBarColors() 
            {
            this.thumbColor = (new Color(80,0,0));
            }
        });

        JTableHeader tableHeader = inventoryTable.getTableHeader();
        tableHeader.setBackground(Color.WHITE);
        tableHeader.setForeground(new Color(80,0,0));
        Font inventoryTableHeaderFont  = new Font("Sans Serif", Font.PLAIN,  13);
        tableHeader.setFont(inventoryTableHeaderFont);

        sp.setForeground(new Color(80,0,0));  
        sp.setBounds(100,150,1325,550);   
        this.add(sp); 


        // make back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100,50,100,50);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(80,0,0));
        Font backButtonFont  = new Font("Sans Serif", Font.PLAIN,  25);
        backButton.setFont(backButtonFont);
        backButton.addActionListener(new backButtonListener());
        this.add(backButton);
    }
}
