import java.sql.*;
import javax.swing.JFrame;
import java.awt.event.*;  
import javax.swing.*;    
import java.awt.*;

public class orderCompleteGUI extends JFrame 
{

    //creates order data
    checkoutInfo orderData;

    //hides frame
    public void hideForm()
    {
        this.dispose();
    }

    //changes back to homeGUI
    private class newOrderButtonListener implements ActionListener
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            homeGUI Home = new homeGUI(orderData);
            hideForm();
        }
    }

    public orderCompleteGUI() 
    {
        orderData = new checkoutInfo();
        //setting up frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Order Complete");
        this.getContentPane().setBackground(new Color(80,0,0));

        //creates frame label
        Font  orderCompleteFont  = new Font("Sans Serif", Font.PLAIN,  40);
        JLabel orderCompleteLabel = new JLabel("Order Complete!");
        orderCompleteLabel.setBounds(650, 50, 300, 100);
        orderCompleteLabel.setForeground(Color.WHITE);
        orderCompleteLabel.setFont(orderCompleteFont);
        this.add(orderCompleteLabel);

        //font for order complete button
        Font orderCompletePageButtonFont  = new Font("Sans Serif", Font.PLAIN,  40);

        //creates new order button
        JButton newOrderButton = new JButton("Start New Order");
        newOrderButton.setBounds(535,200,500,200);
        newOrderButton.setBackground(Color.WHITE);
        newOrderButton.setForeground(new Color(80,0,0));
        newOrderButton.setFont(orderCompletePageButtonFont);
        //runs action listener on click
        newOrderButton.addActionListener(new newOrderButtonListener());
        this.add(newOrderButton);
    }
}