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

public class manOrderTrendGUI extends JFrame
{
    public manOrderTrendGUI() 
    {
        // Home page button
        JButton invButton = new JButton("Home");
        invButton.setBounds(350,350,300,100);
        // invButton.setBackground(Color.);

        invButton.addActionListener(new buttonListener());

        JButton orderButton = new JButton("Inventory");
        orderButton.setBounds(1050,350,300,100);

        orderButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                manInvGUI Inv = new manInvGUI();
                hideForm();
                
                // JOptionPane.showMessageDialog(null, "I was clicked and handled anonymously!");
            } 
        });

        // view menu items query button
        JButton viewOrderPopularity = new JButton("View Orders Popularity");
        viewOrderPopularity.setBounds(750,500,200,50);

        viewOrderPopularity.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String startDate = JOptionPane.showInputDialog(returnComp(),
                    "Start Date", null);
                if (startDate.equalsIgnoreCase("stop")) return;

                String endDate = JOptionPane.showInputDialog(returnComp(),
                    "End Date", null);
                if (endDate.equalsIgnoreCase("stop")) return;

                manOrderPopularity orderPopularity = new manOrderPopularity(jdbcpostgreSQL.OrderPopularity(startDate, endDate));
                hideForm();
                
            }
        });

        // view order trends query button
        JButton viewOrderTrends = new JButton("View Orders Trends");
        viewOrderTrends.setBounds(750,600,200,50);

        viewOrderTrends.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String startDate1 = JOptionPane.showInputDialog(returnComp(),
                    "Start Date 1", null);
                if (startDate1.equalsIgnoreCase("stop")) return;

                String endDate1 = JOptionPane.showInputDialog(returnComp(),
                    "End Date 1", null);
                if (endDate1.equalsIgnoreCase("stop")) return;

                String startDate2 = JOptionPane.showInputDialog(returnComp(),
                    "Start Date 2", null);
                if (startDate2.equalsIgnoreCase("stop")) return;

                String endDate2 = JOptionPane.showInputDialog(returnComp(),
                    "End Date 2", null);
                if (endDate2.equalsIgnoreCase("stop")) return;

                manViewOrderTrends viewOrderTrends = new manViewOrderTrends(jdbcpostgreSQL.orderTrends(startDate1, endDate1, startDate2, endDate2));

                hideForm();
                
            }
        });

        // title
        Font f1 = new Font("Sans Serif", Font.PLAIN, 80);
        JLabel label = new JLabel("My label");
        label.setBounds(600, 0, 1000, 300);
        label.setForeground(Color.WHITE);
        label.setFont(f1);
        
        label.setText("Order Trends");

        // JButton orderButton = new JButton("Order Trends");

        // add buttons to frame
        this.add(invButton);
        this.add(orderButton);
        this.add(viewOrderPopularity);
        this.add(viewOrderTrends);
        this.add(label);


        this.getContentPane().setBackground(new Color(80,0,0));

        // set frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Order Trends Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    // dispose old frame
    public void hideForm()
    {
        this.dispose();
    }

    // return current frame
    public Component returnComp(){
        return this;
    }

    private class buttonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            manHomeGUI home = new manHomeGUI();
            hideForm();
        }
        
    }


}