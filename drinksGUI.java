import java.sql.*;
import javax.swing.JFrame;
import java.awt.event.*;  
import javax.swing.*;    
import java.awt.*;

public class drinksGUI extends JFrame 
{

    checkoutInfo orderData;

    //hides frame
    public void hideForm()
    {
        this.dispose();
    }

    //goes back to home page
    private class backButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            homeGUI Home = new homeGUI(orderData);
            hideForm();
        }
    }

    //goes to checkout page
    private class checkoutButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e)
        {
            checkoutGUI Home = new checkoutGUI(orderData);
            hideForm();
        }
    }

    public drinksGUI(checkoutInfo orderData) 
    {
        this.orderData = orderData;
        //setting up frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Drinks Screen");
        this.getContentPane().setBackground(new Color(80,0,0));

        //creates frame title label
        Font  drinksLabelFont  = new Font("Sans Serif", Font.PLAIN,  80);
        JLabel drinksLabel = new JLabel("Drinks");
        drinksLabel.setBounds(650, 50, 300, 100);
        drinksLabel.setForeground(Color.WHITE);
        drinksLabel.setFont(drinksLabelFont);
        this.add(drinksLabel);

        //font for meal buttons
        Font drinksPageButtonFont  = new Font("Sans Serif", Font.PLAIN,  20);

        //creates checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(1250,50,200,100);
        checkoutButton.setBackground(Color.WHITE);
        checkoutButton.setForeground(new Color(80,0,0));
        Font checkoutButtonFont  = new Font("Sans Serif", Font.PLAIN,  25);
        checkoutButton.setFont(checkoutButtonFont);
        checkoutButton.addActionListener(new checkoutButtonListener());
        this.add(checkoutButton);

        //creates back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100,50,200,100);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(80,0,0));
        Font backButtonFont  = new Font("Sans Serif", Font.PLAIN,  25);
        backButton.setFont(backButtonFont);
        backButton.addActionListener(new backButtonListener());
        this.add(backButton);

        //creates fountain drink button
        JButton fountainButton = new JButton("Fountain Drink");
        fountainButton.setBounds(610,200,300,75);
        fountainButton.setBackground(Color.WHITE);
        fountainButton.setForeground(new Color(80,0,0));
        fountainButton.setFont(drinksPageButtonFont);
        this.add(fountainButton);
        //message to show when button is clicked
        fountainButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Fountain Drink");
                JOptionPane.showMessageDialog(null, "Fountain Drink Added!");
            }
        });

        //creates bottle drink button
        JButton bottleButton = new JButton("Bottle Drink");
        bottleButton.setBounds(610,350,300,75);
        bottleButton.setBackground(Color.WHITE);
        bottleButton.setForeground(new Color(80,0,0));
        bottleButton.setFont(drinksPageButtonFont);
        this.add(bottleButton);
        //message to show when button is clicked
        bottleButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Bottle Drink");
                JOptionPane.showMessageDialog(null, "Bottle Drink Added!");
            }
        });

        //creates gallon of tea button
        JButton gallonTeaButton = new JButton("Gallon of Tea");
        gallonTeaButton.setBounds(610,500,300,75);
        gallonTeaButton.setBackground(Color.WHITE);
        gallonTeaButton.setForeground(new Color(80,0,0));
        gallonTeaButton.setFont(drinksPageButtonFont);
        this.add(gallonTeaButton);
        //message to show when button is clicked
        gallonTeaButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Gallon of Tea");
                JOptionPane.showMessageDialog(null, "Gallon of Tea Added!");
            }
        });

    }

}