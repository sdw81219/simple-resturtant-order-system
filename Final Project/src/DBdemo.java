import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBdemo {
  

	public static void main(String[] args){

    // Load the JDBC driver
    //Class.forName("com.mysql.jdbc.Driver");
    //System.out.println("Driver loaded");

    // Connect to a database
//    Connection connection = null;
//    String username = "root";
//    String password = "sdw81219";
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
	    String password = "sdw81219";
	    try {
	        connection =
	           DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAdatabase", username, password);

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
	
	
	public ResultSet getWaitList() {
		Connection connection = null;
	    String username = "root";
	    String password = "sdw81219";
	    try {
	        connection =
	           DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAdatabase", username, password);

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
					  ("select * from waiting_list;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return resultSet;
	}
	
	
 	public Boolean register(String userName, String pass) {
		Connection connection = null;
	    String username = "root";
	    String password = "sdw81219";
	    try {
	        connection =
	           DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAdatabase", username, password);

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

	
	

	public Object Order(ArrayList<Integer> dish) {
		Connection connection = null;
	    String username = "root";
	    String password = "sdw81219";
	    int totalCost = 0;
	    try {
	        connection =
	           DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAdatabase", username, password);

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
			int dish_id = 1;
			for(int x : dish) {
				if(x != 0) {
					resultSet = statement.executeQuery
							("select quantity from dishes where dish_id = '" + dish_id + "'");
					resultSet.next();
					if (x > resultSet.getInt(1)) {
						resultSet = statement.executeQuery
								("select dish_name from dishes where dish_id = '" + dish_id + "'");
						resultSet.next();
						return resultSet.getString(1);
					}
					resultSet = statement.executeQuery
							("select cost from dishes where dish_id = '" + dish_id + "'");
					while(resultSet.next()) {
						totalCost += (resultSet.getInt(1) * x);
					}
					statement.executeUpdate
					  ("update dishes set quantity = quantity - '" + x + "' where dish_id = '" + dish_id + "'");
				}
				
				dish_id += 1;
			}
			System.out.println("table updated");
			
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
	    return totalCost;
	}
	
	
	public Boolean addWaitList(String name, int id) {
		Connection connection = null;
	    String username = "root";
	    String password = "sdw81219";
	    try {
	        connection =
	           DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAdatabase", username, password);

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
					  ("Select * from waiting_list where customer_name ='" + name + "' or customer_id = '" + id + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	    // Iterate through the result and print the student names
	    try {
	    	while(resultSet.next()) {
	    		return false;
	    	}
	    	statement.executeUpdate
			  ("Insert into waiting_list (cur_pos, customer_id, status, customer_name) values(default, '" + id + "','" + "Waiting" +  "','" + name + "')");
	    	return true;
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
	
	
	
	
	
	
}

