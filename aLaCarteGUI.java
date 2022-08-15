import java.sql.*;
import javax.swing.JFrame;
import java.awt.event.*;  
import javax.swing.*;    
import java.awt.*;

public class aLaCarteGUI extends JFrame 
{

    checkoutInfo orderData;

    public void hideForm()
    {
        this.dispose();
    }

    private class backButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            homeGUI Home = new homeGUI(orderData);
            hideForm();
        }
    }

    private class checkoutButtonListener implements ActionListener 
    {
            
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            checkoutGUI Home = new checkoutGUI(orderData);
            hideForm();
        }
    }

    public aLaCarteGUI(checkoutInfo orderData) 
    {
        this.orderData = orderData;
        //setting up frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("A La Carte Screen");
        this.getContentPane().setBackground(new Color(80,0,0));

        //creates frame title label
        Font  aLaCarteLabelFont  = new Font("Sans Serif", Font.PLAIN,  60);
        JLabel aLaCarteLabel = new JLabel("A La Carte");
        aLaCarteLabel.setBounds(650, 50, 300, 100);
        aLaCarteLabel.setForeground(Color.WHITE);
        aLaCarteLabel.setFont(aLaCarteLabelFont);
        this.add(aLaCarteLabel);

        //font for meal buttons
        Font aLaCartePageButtonFont  = new Font("Sans Serif", Font.PLAIN,  20);
        Font grilledCheeseOnlyButtonFont  = new Font("Sans Serif", Font.PLAIN,  18);

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

        //creates chicken finger "single" button
        JButton singleFingerButton = new JButton("Chicken Finger (Single)");
        singleFingerButton.setBounds(250,200,300,75);
        singleFingerButton.setBackground(Color.WHITE);
        singleFingerButton.setForeground(new Color(80,0,0));
        singleFingerButton.setFont(aLaCartePageButtonFont);
        this.add(singleFingerButton);
        //message to show when button is clicked
        singleFingerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Chicken Finger (Single)");
                JOptionPane.showMessageDialog(null, "Chicken Finger (Single) Added!");
            }
        });

        //creates sandwich only button
        JButton sandOnlyButton = new JButton("Sandwich Only");
        sandOnlyButton.setBounds(250,350,300,75);
        sandOnlyButton.setBackground(Color.WHITE);
        sandOnlyButton.setForeground(new Color(80,0,0));
        sandOnlyButton.setFont(aLaCartePageButtonFont);
        this.add(sandOnlyButton);
        //message to show when button is clicked
        sandOnlyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Sandwich Only");
                JOptionPane.showMessageDialog(null, "Sandwich Only Added!");
            }
        });

        //creates club sandwich only button
        JButton clubSandOnlyButton = new JButton("Club Sandwich Only");
        clubSandOnlyButton.setBounds(250,500,300,75);
        clubSandOnlyButton.setBackground(Color.WHITE);
        clubSandOnlyButton.setForeground(new Color(80,0,0));
        clubSandOnlyButton.setFont(aLaCartePageButtonFont);
        this.add(clubSandOnlyButton);
        //message to show when button is clicked
        clubSandOnlyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Club Sandwich Only");
                JOptionPane.showMessageDialog(null, "Club Sandwich Only Added!");
            }
        });

        //creates grilled cheese sandwich only button
        JButton grilledCheeseOnlyButton = new JButton("Grilled Cheese Sandwich Only");
        grilledCheeseOnlyButton.setBounds(250,650,300,75);
        grilledCheeseOnlyButton.setBackground(Color.WHITE);
        grilledCheeseOnlyButton.setForeground(new Color(80,0,0));
        grilledCheeseOnlyButton.setFont(grilledCheeseOnlyButtonFont);
        this.add(grilledCheeseOnlyButton);
        //message to show when button is clicked
        grilledCheeseOnlyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Grilled Cheese Sandwich Only");
                JOptionPane.showMessageDialog(null, "Grilled Cheese Sandwich Only Added!");
            }
        });

        //creates layne's sauce button
        JButton laynesSauceButton = new JButton("Layne's Sauce");
        laynesSauceButton.setBounds(950,200,300,75);
        laynesSauceButton.setBackground(Color.WHITE);
        laynesSauceButton.setForeground(new Color(80,0,0));
        laynesSauceButton.setFont(aLaCartePageButtonFont);
        this.add(laynesSauceButton);
        //message to show when button is clicked
        laynesSauceButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Layne's Sauce");
                JOptionPane.showMessageDialog(null, "Layne's Sauce Added!");
            }
        });

        //creates texas toast button
        JButton texasToastButton = new JButton("Texas Toast");
        texasToastButton.setBounds(950,350,300,75);
        texasToastButton.setBackground(Color.WHITE);
        texasToastButton.setForeground(new Color(80,0,0));
        texasToastButton.setFont(aLaCartePageButtonFont);
        this.add(texasToastButton);
        //message to show when button is clicked
        texasToastButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Texas Toast");
                JOptionPane.showMessageDialog(null, "Texas Toast Added!");
            }
        });

        //creates potato salad button
        JButton potatoSaladButton = new JButton("Potato Salad");
        potatoSaladButton.setBounds(950,500,300,75);
        potatoSaladButton.setBackground(Color.WHITE);
        potatoSaladButton.setForeground(new Color(80,0,0));
        potatoSaladButton.setFont(aLaCartePageButtonFont);
        this.add(potatoSaladButton);
        //message to show when button is clicked
        potatoSaladButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Potato Salad");
                JOptionPane.showMessageDialog(null, "Potato Salad Added!");
            }
        });

        //creates crinkle cut fries button
        JButton friesButton = new JButton("Crinkle Cut Fries");
        friesButton.setBounds(950,650,300,75);
        friesButton.setBackground(Color.WHITE);
        friesButton.setForeground(new Color(80,0,0));
        friesButton.setFont(aLaCartePageButtonFont);
        this.add(friesButton);
        //message to show when button is clicked
        friesButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                orderData.addItem("Crinkle Cut Fries");
                JOptionPane.showMessageDialog(null, "Crinkle Cut Fries Added!");
            }
        });
    }
}