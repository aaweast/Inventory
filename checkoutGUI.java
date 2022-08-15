import java.sql.*;
import javax.swing.JFrame;
import java.awt.event.*;  
import javax.swing.*;    
import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.ArrayList;
import java.util.Arrays;

public class checkoutGUI extends JFrame {

    //stores order information
    checkoutInfo orderData;

    //function to hide frame
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

    //takes in item names from orderData and updates orders in database 
    //when pay button is clicked
    private class payButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {

            String[][] stringArray = orderData.table.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
            for (String[] row : stringArray)
            {
                jdbcpostgreSQL.UpdateOrders(row[0],"1");
            }

            //takes user to order complete page
            orderCompleteGUI OrderComplete = new orderCompleteGUI();
            hideForm();
        }
    }

    public checkoutGUI(checkoutInfo orderData) 
    {
        this.orderData = orderData;
        //setting up frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Checkout Screen");
        this.getContentPane().setBackground(new Color(80,0,0));

        //creates frame title label
        Font  checkoutLabelFont  = new Font("Sans Serif", Font.PLAIN,  50);
        JLabel checkoutLabel = new JLabel("Checkout");
        checkoutLabel.setBounds(650, 50, 300, 100);
        checkoutLabel.setForeground(Color.WHITE);
        checkoutLabel.setFont(checkoutLabelFont);
        this.add(checkoutLabel);

        //font for meal buttons
        Font checkoutPageButtonFont  = new Font("Sans Serif", Font.PLAIN,  20);

        //creates pay button
        JButton payButton = new JButton("Pay");
        payButton.setBounds(100,650,200,100);
        payButton.setBackground(Color.WHITE);
        payButton.setForeground(new Color(80,0,0));
        Font payButtonFont  = new Font("Sans Serif", Font.PLAIN,  25);
        payButton.setFont(payButtonFont);
        payButton.addActionListener(new payButtonListener());
        this.add(payButton);


        //creates back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100,50,200,100);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(80,0,0));
        Font backButtonFont  = new Font("Sans Serif", Font.PLAIN,  25);
        backButton.setFont(backButtonFont);
        backButton.addActionListener(new backButtonListener());
        this.add(backButton);


        //columns for checkout table
        String column[] =
        {
            "ITEM","PRICE"
        };     


        //takes in orderData and calculates order total for totaPay text
        Double orderTotal = 0.0;

        String[][] stringArray = orderData.table.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

        for (String[] row : stringArray)
        {
            orderTotal += Double.valueOf(row[1]);
        }

        //creating text field for total pay calculator
        JTextField totalPay = new JTextField("Total = $" + orderTotal);
        totalPay.setEditable(false);
        totalPay.setHorizontalAlignment(JTextField.CENTER);
        totalPay.setBounds(1225,650,200,100);
        totalPay.setBackground(Color.WHITE);
        totalPay.setForeground(new Color(80,0,0));
        Font totalPayFont  = new Font("Sans Serif", Font.PLAIN,  25);
        totalPay.setFont(totalPayFont);
        this.add(totalPay);

        //creating checkout table and scroll pane for table
        JTable checkoutTable = new JTable(stringArray,column);  
        checkoutTable.setBackground(Color.WHITE);
        checkoutTable.setForeground(new Color(80,0,0));
        Font checkoutTableFont  = new Font("Sans Serif", Font.PLAIN,  20);
        checkoutTable.setFont(checkoutTableFont);
        checkoutTable.setRowHeight(25);
        JScrollPane sp= new JScrollPane(checkoutTable); 

        //styling for scroll pane scroll bar
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() 
        {
            @Override
            protected void configureScrollBarColors() 
            {
                this.thumbColor = (new Color(80,0,0));
            }
        });


        //styling for the table header
        JTableHeader tableHeader = checkoutTable.getTableHeader();
        tableHeader.setBackground(Color.WHITE);
        tableHeader.setForeground(new Color(80,0,0));
        Font checkoutTableHeaderFont  = new Font("Sans Serif", Font.PLAIN,  25);
        tableHeader.setFont(checkoutTableHeaderFont);

        //styling for scroll pane and adding it to frame
        sp.setForeground(new Color(80,0,0));  
        sp.setBounds(100,200,1325,400);   
        this.add(sp); 
    }
}