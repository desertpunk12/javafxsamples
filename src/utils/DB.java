package utils;

import java.sql.*;

public class DB {
  private static final String DB_IP = "localhost";
  private static final String DB_PORT = "3306";
  private static final String DB_NAME = "javafxcrud";
  private static String DB_USER = "root";
  private static String DB_PASSWORD = "root";

  private static Connection con;


  //This method connects to the specified database
  public static Connection getConnection() throws SQLException {
    if (con != null && !con.isClosed())
      return con;
    else {
      con = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASSWORD);
        return con;
    }
  }

  //This method exectues a query passed into it
  public static ResultSet query(String q, boolean isUpdate) throws SQLException {
    Statement stmnt = getConnection().createStatement();
    System.out.println("Executing query: " + q);
    if (isUpdate)
      stmnt.executeUpdate(q);
    else
      return stmnt.executeQuery(q);

    stmnt.close();
    close();

    return null;
  }

  //Overloaded the above method for default false update
  public static ResultSet query(String q) throws SQLException{
    return query(q,false);
  }



  public static void close() throws SQLException {
    if (con != null && !con.isClosed()) {
      con.close();
    }
  }

}
