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


public class manInvGUI extends JFrame
{
    public manInvGUI() 
    {
        // Home page button
        JButton invButton = new JButton("Home");
        invButton.setBounds(350,350,300,100);
        // invButton.setBackground(Color.);

        invButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                manHomeGUI home = new manHomeGUI();
                hideForm();
                
                // JOptionPane.showMessageDialog(null, "I was clicked and handled anonymously!");
            } });

        // order trends page button
        JButton orderButton = new JButton("Order Trends");
        orderButton.setBounds(1050,350,300,100);
        orderButton.addActionListener(new buttonListener());

        // view inventory from database button
        JButton viewInvButton = new JButton("View Inventory");
        viewInvButton.setBounds(525,500,200,50);

        viewInvButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String tableString = jdbcpostgreSQL.ViewInventory();
                //JOptionPane.showMessageDialog(null,tableString);
                inventoryList list = new inventoryList(tableString);
                hideForm();
            }
        });

        // uodate inventory in database button
        JButton updateInvButton = new JButton("Update Inventory");
        updateInvButton.setBounds(975,500,200,50);
        updateInvButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String updateSKU = JOptionPane.showInputDialog(returnComp(),
                    "SKU", null);
                if (updateSKU.equalsIgnoreCase("stop")) return;

                String updateQuant = JOptionPane.showInputDialog(returnComp(),
                    "New Quantity", null);
                if (updateQuant.equalsIgnoreCase("stop")) return;
                
                jdbcpostgreSQL.UpdateInventoryAmount(updateQuant, updateSKU);
                
            }
        });

        // uodate inventory in database button
        JButton updateFillButton = new JButton("Update Fill");
        updateFillButton.setBounds(750,725,200,50);
        updateFillButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String updateSKU = JOptionPane.showInputDialog(returnComp(),
                    "SKU", null);
                if (updateSKU.equalsIgnoreCase("stop")) return;

                String updateQuant = JOptionPane.showInputDialog(returnComp(),
                    "New Fill", null);
                if (updateQuant.equalsIgnoreCase("stop")) return;
                
                jdbcpostgreSQL.UpdateFillAmount(updateQuant, updateSKU);
                
            }
        });

        // update inventory from day in database button
        JButton updateInvDayButton = new JButton("Update Inventory From Day");
        updateInvDayButton.setBounds(750,500,200,50);
        updateInvDayButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String date = JOptionPane.showInputDialog(returnComp(),
                    "Enter Date", null);
                if (date.equalsIgnoreCase("stop")) return;
                
                jdbcpostgreSQL.UpdateInventoryWithOrders(date);
                
            }
        });

        // update inventory from day in database button
        JButton restockButton = new JButton("View Restock Needs");
        restockButton.setBounds(640,575,200,50);
        restockButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String tableString = jdbcpostgreSQL.restock();
                //JOptionPane.showMessageDialog(null,tableString);
                restockList list = new restockList(tableString);
                hideForm();
                // String date = JOptionPane.showInputDialog(returnComp(),
                //     "Enter Date", null);
                // if (date.equalsIgnoreCase("stop")) return;
                
                // jdbcpostgreSQL.UpdateInventoryWithOrders();
                
            }
        });

        // inventory usage
        JButton inventoryUsageButton = new JButton("Inventory Usage");
        inventoryUsageButton.setBounds(865,575,200,50);
        inventoryUsageButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String date = JOptionPane.showInputDialog(returnComp(),
                    "Start Date", null);
                if (date.equalsIgnoreCase("stop")) return;

                String date2 = JOptionPane.showInputDialog(returnComp(),
                    "End Date", null);
                if (date2.equalsIgnoreCase("stop")) return;
                String tableString = jdbcpostgreSQL.Usage(date,date2);
                //JOptionPane.showMessageDialog(null,tableString);
                inventoryUsageList list = new inventoryUsageList(tableString);
                hideForm();
                // String date = JOptionPane.showInputDialog(returnComp(),
                //     "Enter Date", null);
                // if (date.equalsIgnoreCase("stop")) return;
                
                // jdbcpostgreSQL.UpdateInventoryWithOrders();
                
            }
        });

        // inventory usage
        JButton inventoryPopButton = new JButton("Inventory Popularity");
        inventoryPopButton.setBounds(750,650,200,50);
        inventoryPopButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String date = JOptionPane.showInputDialog(returnComp(),
                    "Start Date", null);
                if (date.equalsIgnoreCase("stop")) return;

                String date2 = JOptionPane.showInputDialog(returnComp(),
                    "End Date", null);
                if (date2.equalsIgnoreCase("stop")) return;
                String tableString = jdbcpostgreSQL.TimesOrdered(date,date2);
                //JOptionPane.showMessageDialog(null,tableString);
                inventoryPopList list = new inventoryPopList(tableString);
                hideForm();
                // String date = JOptionPane.showInputDialog(returnComp(),
                //     "Enter Date", null);
                // if (date.equalsIgnoreCase("stop")) return;
                
                // jdbcpostgreSQL.UpdateInventoryWithOrders();
                
            }
        });
         // update inventory from day in database button
         JButton restockOrdersButton = new JButton("Restock Orders");
         restockOrdersButton.setBounds(750,800,200,50);
         restockOrdersButton.addActionListener(new ActionListener() 
         {
             public void actionPerformed(ActionEvent e)
             {
                 jdbcpostgreSQL.restockOrders();
                 //JOptionPane.showMessageDialog(null,tableString);
                 
                //  restockList list = new restockList(tableString);
                //  hideForm();
                 // String date = JOptionPane.showInputDialog(returnComp(),
                 //     "Enter Date", null);
                 // if (date.equalsIgnoreCase("stop")) return;
                 
                 // jdbcpostgreSQL.UpdateInventoryWithOrders();
                 
             }
         });

        // make title
        Font f1 = new Font("Sans Serif", Font.PLAIN, 80);
        JLabel label = new JLabel("My label");
        label.setBounds(675, 0, 1000, 300);
        label.setForeground(Color.WHITE);
        label.setFont(f1);
        label.setText("Inventory");
        
        orderButton.addActionListener(new buttonListener());
        // add buttons to frame
        this.add(invButton);
        this.add(orderButton);
        this.add(viewInvButton);
        this.add(updateInvButton);
        this.add(updateInvDayButton);
        this.add(restockButton);
        this.add(inventoryUsageButton);
        this.add(inventoryPopButton);
        this.add(updateFillButton);
        this.add(restockOrdersButton);
        // this.add(viewItemsButton);
        // this.add(updateItemsButton);
        this.add(label);
        // this.add(invview);


        // set frame and background
        this.getContentPane().setBackground(new Color(80,0,0));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Inventory Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    // delete old frame
    public void hideForm()
    {
        this.dispose();
    }

    // return this component
    public Component returnComp()
    {
        return this;
    }

    // buttion action
    private class buttonListener implements ActionListener 
    {
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            manOrderTrendGUI Order = new manOrderTrendGUI();
            hideForm();
        }
        
    }


}