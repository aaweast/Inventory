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

public class homeGUI extends JFrame 
{

    //creates orderData to store order information
    checkoutInfo orderData;

    //hides frame
    public void hideForm()
    {
        this.dispose();
    }

    //changes to mealsGUI
    private class mealsButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            mealsGUI Meals = new mealsGUI(orderData);
            hideForm();
        }
    }

    //changes to aLaCarteGUI
    private class aLaCarteButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            aLaCarteGUI ALaCarte = new aLaCarteGUI(orderData);
            hideForm();
        }
    }

    //changes to drinksGUI
    private class drinksListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            drinksGUI Drinks = new drinksGUI(orderData);
            hideForm();
        }
    }

    //changes to checkoutGUI
    private class checkoutButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            checkoutGUI Home = new checkoutGUI(orderData);
            hideForm();
        }
    }

    homeGUI(checkoutInfo orderData) 
    {

        //copies orderData
        this.orderData = orderData;

        //styles pane and adds label
        this.getContentPane().setBackground(new Color(80,0,0));
        Font  homeLabelFont  = new Font("Sans Serif", Font.PLAIN,  80);
        JLabel homeLabel = new JLabel("Layne's");
        homeLabel.setBounds(650, 50, 300, 100);
        homeLabel.setForeground(Color.WHITE);
        homeLabel.setFont(homeLabelFont);
        this.add(homeLabel);

        //creates checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(1250,50,200,100);
        checkoutButton.setBackground(Color.WHITE);
        checkoutButton.setForeground(new Color(80,0,0));
        Font checkoutButtonFont  = new Font("Sans Serif", Font.PLAIN,  25);
        checkoutButton.setFont(checkoutButtonFont);
        //runs action listener on click
        checkoutButton.addActionListener(new checkoutButtonListener());

        //creates font for home page
        Font homePageButtonFont  = new Font("Sans Serif", Font.PLAIN,  35);
        
        //creates meals button
        JButton mealsButton = new JButton("Meals");
        mealsButton.setBounds(50,250,400,300);
        mealsButton.setBackground(Color.WHITE);
        mealsButton.setForeground(new Color(80,0,0));
        mealsButton.setFont(homePageButtonFont);
        //runs action listener on click
        mealsButton.addActionListener(new mealsButtonListener());

        //creates aLaCarte button
        JButton aLaCarteButton = new JButton("A La Carte");
        aLaCarteButton.setBounds(575,250,400,300);
        aLaCarteButton.setBackground(Color.WHITE);
        aLaCarteButton.setForeground(new Color(80,0,0));
        aLaCarteButton.setFont(homePageButtonFont);
        //runs action listener on click
        aLaCarteButton.addActionListener(new aLaCarteButtonListener());

        //creates drinks button
        JButton drinksButton = new JButton("Drinks");
        drinksButton.setBounds(1075,250,400,300);
        drinksButton.setBackground(Color.WHITE);
        drinksButton.setForeground(new Color(80,0,0));
        drinksButton.setFont(homePageButtonFont);
        //runs action listener on click
        drinksButton.addActionListener(new drinksListener());

        //adds buttons to frame
        this.add(checkoutButton);
        this.add(mealsButton);
        this.add(aLaCarteButton);
        this.add(drinksButton);

        //sets frame to fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //exits on close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting null layout for frame
        this.setLayout(null);
        //setting frame visible and creating title
        this.setVisible(true);
        this.setTitle("Home");
    }
}