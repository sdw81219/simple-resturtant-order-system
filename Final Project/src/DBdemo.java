import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBdemo {


    public static void main(String[] args){

        // Load the JDBC driver
        //Class.forName("com.mysql.jdbc.Driver");
        //System.out.println("Driver loaded");

        // Connect to a database
//    Connection connection = null;
//    String username = "root";
//    String password = "leonloveyou";
//    try {
//        connection =
//           DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaDB", username, password);
//
//    } catch (SQLException ex) {
//        // handle any errors
//        System.out.println("SQLException: " + ex.getMessage());
//        System.out.println("SQLState: " + ex.getSQLState());
//        System.out.println("VendorError: " + ex.getErrorCode());
//    }
//
//    System.out.println("Database connected");

        // Create a statement
//    Statement statement = null;
//	try {
//		statement = connection.createStatement();
//	} catch (SQLException e) {
//		e.printStackTrace();
//		System.exit(0);
//	}
//
//    // Execute a statement
//    ResultSet resultSet = null;
//	try {
//		resultSet = statement.executeQuery
//				  ("select * from customer");
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		System.exit(0);
//	}
//
//    // Iterate through the result and print the student names
//    try {
//		while (resultSet.next())
//		  System.out.println(resultSet.getString(1));
//	} catch (SQLException e) {
//		e.printStackTrace();
//		System.exit(0);
//	}
//
//    // Close the connection
//    try {
//		connection.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		System.exit(0);
//	}
    }


    public Boolean checkUser(String userName, String pass) {
        Connection connection = null;
        String username = "root";
        String password = "leonloveyou";
        try {
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaDB", username, password);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        System.out.println("Database connected");

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Execute a statement
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery
                    ("select user_name from customer where user_name = '" + userName +  "' and user_pass = '" + pass + "';");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        // Iterate through the result and print the student names
        try {
            while(resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Close the connection
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }


    public Boolean register(String userName, String pass) {
        Connection connection = null;
        String username = "root";
        String password = "leonloveyou";
        try {
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaDB", username, password);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        System.out.println("Database connected");

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Execute a statement
        ResultSet resultSet = null;
        try {
            statement.executeUpdate
                    ("Insert into customer (customer_id, user_name, user_pass) values(default, '" + userName + "','" + pass + "')");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }

        // Close the connection
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
}


