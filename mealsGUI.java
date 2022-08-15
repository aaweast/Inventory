import java.sql.*;
import javax.swing.JFrame;
import java.awt.event.*;  
import javax.swing.*;    
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;
import javax.swing.JOptionPane;

public class mealsGUI extends JFrame 
{


    //creates orderData
    checkoutInfo orderData;

    //hides frame
    public void hideForm()
    {
        this.dispose();
    }

    //goes back to homeGUI
    private class backButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            homeGUI Home = new homeGUI(orderData);
            hideForm();
        }
    }

    //changes to checkout page
    private class checkoutButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            checkoutGUI Home = new checkoutGUI(orderData);
            hideForm();
        }
    }

    public mealsGUI(checkoutInfo orderData) 
    {

        this.orderData = orderData;

        //setting up frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Meals Screen");
        this.getContentPane().setBackground(new Color(80,0,0));

        //creates frame title label
        Font  mealsLabelFont  = new Font("Sans Serif", Font.PLAIN,  80);
        JLabel mealsLabel = new JLabel("Meals");
        mealsLabel.setBounds(650, 50, 300, 100);
        mealsLabel.setForeground(Color.WHITE);
        mealsLabel.setFont(mealsLabelFont);
        this.add(mealsLabel);

        //font for meal buttons
        Font mealsPageButtonFont  = new Font("Sans Serif", Font.PLAIN,  20);

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

        //creates five finger button
        JButton fiveFingerButton = new JButton("Five Finger Original");
        fiveFingerButton.setBounds(250,200,300,75);
        fiveFingerButton.setBackground(Color.WHITE);
        fiveFingerButton.setForeground(new Color(80,0,0));
        fiveFingerButton.setFont(mealsPageButtonFont);
        this.add(fiveFingerButton);
        //message to show when button is clicked
        fiveFingerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Five Finger Original");
                JOptionPane.showMessageDialog(null, "Five Finger Original Added!");
            }
        });

        // //creates Impossible Chicken Tender button
        // JButton impossibleChicken = new JButton("Impossible Chicken Tenders");
        // impossibleChicken.setBounds(600,200,300,75);
        // impossibleChicken.setBackground(Color.WHITE);
        // impossibleChicken.setForeground(new Color(80,0,0));
        // impossibleChicken.setFont(mealsPageButtonFont);
        // this.add(impossibleChicken);
        // //message to show when button is clicked
        // impossibleChicken.addActionListener(new ActionListener()
        // {
        //     public void actionPerformed(ActionEvent e)
        //     {
        //         orderData.addItem("Impossible Chicken Tenders");
        //         JOptionPane.showMessageDialog(null, "Impossible Chicken Tenders Added!");
        //     }
        // });        

        //creates four finger button
        JButton fourFingerButton = new JButton("Four Finger Meal");
        fourFingerButton.setBounds(250,350,300,75);
        fourFingerButton.setBackground(Color.WHITE);
        fourFingerButton.setForeground(new Color(80,0,0));
        fourFingerButton.setFont(mealsPageButtonFont);
        this.add(fourFingerButton);
        //message to show when button is clicked
        fourFingerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Four Finger Meal");
                JOptionPane.showMessageDialog(null, "Four Finger Meal Added!");
            }
        });

        //creates three finger button
        JButton threeFingerButton = new JButton("Three Finger Meal");
        threeFingerButton.setBounds(250,500,300,75);
        threeFingerButton.setBackground(Color.WHITE);
        threeFingerButton.setForeground(new Color(80,0,0));
        threeFingerButton.setFont(mealsPageButtonFont);
        this.add(threeFingerButton);
        //message to show when button is clicked
        threeFingerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Three Finger Meal");
                JOptionPane.showMessageDialog(null, "Three Finger Meal Added!");
            }
        });


        //creates kid's meal button
        JButton kidsMealButton = new JButton("Kid's Meal");
        kidsMealButton.setBounds(250,650,300,75);
        kidsMealButton.setBackground(Color.WHITE);
        kidsMealButton.setForeground(new Color(80,0,0));
        kidsMealButton.setFont(mealsPageButtonFont);
        this.add(kidsMealButton);
        //message to show when button is clicked
        kidsMealButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Kid's Meal");
                JOptionPane.showMessageDialog(null, "Kid's Meal Added!");
            }
        });

        //creates club sandwich meal button
        JButton clubSandButton = new JButton("Club Sandwich Meal");
        clubSandButton.setBounds(950,200,300,75);
        clubSandButton.setBackground(Color.WHITE);
        clubSandButton.setForeground(new Color(80,0,0));
        clubSandButton.setFont(mealsPageButtonFont);
        this.add(clubSandButton);
        //message to show when button is clicked
        clubSandButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Club Sandwich Meal");
                JOptionPane.showMessageDialog(null, "Club Sandwich Meal Added!");
            }
        });

        //creates sandwich meal button
        JButton sandButton = new JButton("Sandwich Meal Combo");
        sandButton.setBounds(950,350,300,75);
        sandButton.setBackground(Color.WHITE);
        sandButton.setForeground(new Color(80,0,0));
        sandButton.setFont(mealsPageButtonFont);
        this.add(sandButton);
        //message to show when button is clicked
        sandButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Sandwich Meal Combo");
                JOptionPane.showMessageDialog(null, "Sandwich Meal Combo Added!");
            }
        });

        //creates grilled cheese button
        JButton grilledCheeseButton = new JButton("Grilled Cheese Meal Combo");
        grilledCheeseButton.setBounds(950,500,300,75);
        grilledCheeseButton.setBackground(Color.WHITE);
        grilledCheeseButton.setForeground(new Color(80,0,0));
        grilledCheeseButton.setFont(mealsPageButtonFont);
        this.add(grilledCheeseButton);
        //message to show when button is clicked
        grilledCheeseButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Grilled Cheese Meal Combo");
                JOptionPane.showMessageDialog(null, "Grilled Cheese Meal Combo Added!");
            }
        });

        //creates family pack button
        JButton familyPackButton = new JButton("Family Pack");
        familyPackButton.setBounds(950,650,300,75);
        familyPackButton.setBackground(Color.WHITE);
        familyPackButton.setForeground(new Color(80,0,0));
        familyPackButton.setFont(mealsPageButtonFont);
        this.add(familyPackButton);
        //message to show when button is clicked
        familyPackButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Family Pack");
                JOptionPane.showMessageDialog(null, "Family Pack Added!");
            }
        });
    }
}
