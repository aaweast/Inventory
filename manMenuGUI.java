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


public class  manMenuGUI extends JFrame
{

    // dispose old frame
    public void hideForm(){
        this.dispose();
    }

    // action listener for button
    private class backButtonListener implements ActionListener 
    {
                
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            manHomeGUI homeGUI = new manHomeGUI();
            hideForm();
        }
    }

    String tableString;

    // make table to display in gui
    public manMenuGUI(String tableString) 
    {

        this.tableString = tableString;
        
        //NEED TO CONVERT tableString TO test[][]

        String[] rows = tableString.split("\n");
        ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();

        for (String elem : rows) 
        {
            String[] entry = elem.split(" \\| ");
            ArrayList<String> t1 = new ArrayList<String>(Arrays.asList(entry));
            test.add(t1);
        }

        String[][] toView = test.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

        String[] columns = {"menu_id", "name", "description", "price"};

        // set frame 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Menu");
        this.getContentPane().setBackground(new Color(80,0,0));

        JTable menuTable = new JTable(toView,columns);  

        menuTable.setBackground(Color.WHITE);
        menuTable.setForeground(new Color(80,0,0));
        Font menuTableFont  = new Font("Sans Serif", Font.PLAIN,  20);
        menuTable.setFont(menuTableFont);
        menuTable.setRowHeight(25);
        JScrollPane sp= new JScrollPane(menuTable); 

        // set scroll bar
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() 
        {
            @Override
            protected void configureScrollBarColors() 
            {
            this.thumbColor = (new Color(80,0,0));
            }
        });

        JTableHeader tableHeader = menuTable.getTableHeader();
        tableHeader.setBackground(Color.WHITE);
        tableHeader.setForeground(new Color(80,0,0));
        Font menuTableHeaderFont  = new Font("Sans Serif", Font.PLAIN,  25);
        tableHeader.setFont(menuTableHeaderFont);

        sp.setForeground(new Color(80,0,0));  
        sp.setBounds(100,150,1325,515);   
        this.add(sp); 


        // set button
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