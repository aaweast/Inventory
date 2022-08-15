import java.sql.*;

public class GUItester {

    public static void main(String[] args) {

        //creates orderInfo and generates a homeGUI frame to start application

        checkoutInfo orderInfo = new checkoutInfo();

        homeGUI home = new homeGUI(orderInfo);
    }
}