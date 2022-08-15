
import java.sql.*;
import javax.swing.JFrame;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class manHomeGUI extends JFrame
{

   
    // home page constructor
    public manHomeGUI() 
    {
        // inventory page button
        JButton invButton = new JButton("Inventory");
        invButton.setBounds(350,350,300,100);
        invButton.addActionListener(new ActionListener() 
        {
           

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                manInvGUI Inv = new manInvGUI();
                hideForm();
                
            } });


        // Order trends button
        JButton orderButton = new JButton("Order Trends");
        orderButton.setBounds(1050,350,300,100);
        orderButton.addActionListener(new buttonListener());

        
        // view menu query button
        JButton viewMenuButton = new JButton("View Menu");
        viewMenuButton.setBounds(525,500,200,50);

        viewMenuButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String tableString = jdbcpostgreSQL.ViewMenu();
                //JOptionPane.showMessageDialog(null,tableString);
                manMenuGUI menuGUI = new manMenuGUI(tableString);
                hideForm();
            }
        });
        
        // view menu items query button
        JButton viewMenuItemsButton = new JButton("View Menu Items");
        viewMenuItemsButton.setBounds(750,500,200,50);

        viewMenuItemsButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String tableString = jdbcpostgreSQL.ViewPartsOfMenu();
                //JOptionPane.showMessageDialog(null,tableString);
                manMenuItemsGUI menuItemsGUI = new manMenuItemsGUI(tableString);
                hideForm();
            }
        });

        // add menu item query button
        JButton addMenuItemsButton = new JButton("Add Menu Items");
        addMenuItemsButton.setBounds(975,500,200,50);
        addMenuItemsButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String addMenu_id = JOptionPane.showInputDialog(returnComp(),
                    "Menu_id", null);
                if (addMenu_id.equalsIgnoreCase("stop")) return;

                String addMenuName = JOptionPane.showInputDialog(returnComp(),
                    "Name", null);
                if (addMenuName.equalsIgnoreCase("stop")) return;

                String addMenuDesc = JOptionPane.showInputDialog(returnComp(),
                    "Description", null);
                if (addMenuDesc.equalsIgnoreCase("stop")) return;

                String addMenuPrice = JOptionPane.showInputDialog(returnComp(),
                    "Price", null);
                if (addMenuPrice.equalsIgnoreCase("stop")) return;

                jdbcpostgreSQL.AddMenuItem(addMenu_id, addMenuName, addMenuDesc, addMenuPrice);
            }
        });

        // add part of menu to data base button
        JButton addPartMenuItemsButton = new JButton("Add Part of Menu");
        addPartMenuItemsButton.setBounds(865,575,200,50);
        addPartMenuItemsButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String menu_id = JOptionPane.showInputDialog(returnComp(),
                    "menu_id", null);
                if (menu_id.equalsIgnoreCase("stop")) return;

                String quantity = JOptionPane.showInputDialog(returnComp(),
                    "Quantity", null);
                if (quantity.equalsIgnoreCase("stop")) return;

                String sku = JOptionPane.showInputDialog(returnComp(),
                    "SKU", null);
                if (sku.equalsIgnoreCase("stop")) return;

                String quantityType = JOptionPane.showInputDialog(returnComp(),
                    "Quantity Type", null);
                if (quantityType.equalsIgnoreCase("stop")) return;

                jdbcpostgreSQL.AddPartsOfMenu(menu_id, quantity, sku, quantityType);
            }
        });

        // update menu price in database button
        JButton updateMenuPriceButton = new JButton("Update Menu Price");
        updateMenuPriceButton.setBounds(640,575,200,50);
        updateMenuPriceButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String menu_id = JOptionPane.showInputDialog(returnComp(),
                    "menu_id", null);
                if (menu_id.equalsIgnoreCase("stop")) return;
                    
                String newPrice = JOptionPane.showInputDialog(returnComp(),
                    "New Price", null);
                if (newPrice.equalsIgnoreCase("stop")) return;

                jdbcpostgreSQL.UpdateMenuPrice(newPrice, menu_id);
            }
        });

        // update parts of menu button in database
        JButton updatePartMenuButton = new JButton("Update Part of Menu");
        updatePartMenuButton.setBounds(750,650,200,50);
        updatePartMenuButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                String menu_id = JOptionPane.showInputDialog(returnComp(),
                    "menu_id", null);
                if (menu_id.equalsIgnoreCase("stop")) return;
                    
                String sku = JOptionPane.showInputDialog(returnComp(),
                    "SKU", null);
                if (sku.equalsIgnoreCase("stop")) return;

                String quant = JOptionPane.showInputDialog(returnComp(),
                    "New Quantity", null);
                if (quant.equalsIgnoreCase("stop")) return;

                jdbcpostgreSQL.UpdatePartsOfMenu(quant, menu_id, sku);
            }
        });

       // TITLE
        Font f1 = new Font("Sans Serif", Font.PLAIN, 80);

        JLabel label = new JLabel("My label");
        label.setBounds(600, 0, 1000, 300);
        label.setForeground(Color.WHITE);
        label.setFont(f1);
        label.setText("Manager View");

        JTextField textField = new JTextField();
        textField.setText("This is a text");
        textField.setColumns(20);

        // add buttons and title to frame
        this.add(textField);
        this.add(invButton);
        this.add(orderButton);
        this.add(viewMenuButton);
        this.add(viewMenuItemsButton);
        this.add(addMenuItemsButton);
        this.add(addPartMenuItemsButton);
        this.add(updateMenuPriceButton);
        this.add(updatePartMenuButton);
        this.add(label);


        // set title and background color and dimensions of frame
        this.getContentPane().setBackground(new Color(80,0,0));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Manager Home Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }
    
    // hide frame
    public void hideForm(){
        this.dispose();
    }

    // return current frame
    public Component returnComp(){
        return this;
    }

    // add panel to frame
    public void addPanel(JPanel p){
        this.add(p);
    }

    // button action 
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