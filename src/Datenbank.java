/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kay Patzwald
 */
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datenbank implements IDatenhaltung {
  
  private Connection connection;
  private Statement statement;
  public Datenbank() {
    initDB();
  }
  
  private void initDB() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      if (connection == null) {
        connection = DriverManager.getConnection("jdbc:mysql://erp-learning.info:3306/customers_test", "customer", "customer123!");
        statement = connection.createStatement();
          System.out.println("Datenbank.initDB()");
      } 
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    } catch(Exception e) {
      System.out.println(e.getMessage());
      //closeConnection();
    }// end of try
  }
  
  private void closeDB() {
    try {
      statement.close();
      connection.close();
    } catch(SQLException e) {
      e.getMessage();
    } // end of try
    
  }


  
  public void storeCustomer(Customer customer){
    try {
      //loadDatabase();
      String sql = "INSERT INTO customers (firstname, lastname, email) VALUES ('" + customer.getFirstname() + "','" + customer.getLastname()+ "','" + customer.getEmail()+ "');";
      statement.executeUpdate(sql);
      //lStatus.setText("Everything is ok. A new customer was created");
    } catch(SQLException e) {
      System.out.println(e.getMessage());
      //closeConnection();
    } // end of try
  }

  public Customer getCustomer(int id){
    String sql = "SELECT * from customers WHERE id=" + id + ";";
    ResultSet res;
    Customer cust = null;
    try {
      res = statement.executeQuery(sql);
      while (res.next()) { 
        String fn = res.getString("firstname");
        String ln = res.getString("lastname");
        String email = res.getString("email");
        cust = new Customer(fn,ln);
        cust.setEmail(email);
        cust.setId(id);
        //model.addElement(ln + ", " + fn);
      } // end of while
      //lStatus.setText("All customers loaded!");
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    } // end of try
    
    return cust;
  }

  public ArrayList<Customer> getAllCustomers(){
    String sql = "select * from customers";
    ArrayList<Customer> allCustomers = new ArrayList<>();
    ResultSet res;
    try {
      res = statement.executeQuery(sql);
      while (res.next()) { 
        String fn = res.getString("firstname");
        String ln = res.getString("lastname");
        String email = res.getString("email");
        int id = res.getInt("id");
        Customer cust = new Customer(fn,ln);
        cust.setId(id);
        cust.setEmail(email);
        allCustomers.add(cust);
          System.out.println(fn);
        //model.addElement(ln + ", " + fn);
      } // end of while
      //lStatus.setText("All customers loaded!");
    } catch(SQLException e) {
      System.out.println(e.getMessage());
      //closeConnection();
    } // end of try
    return allCustomers;
  }

  public void delCustomer(int id){
    try {
      //loadDatabase();
      String sql = "DELETE FROM customers WHERE id=" + id +";";
      statement.executeUpdate(sql);
      // TODO Rückgabewert für Status wäre nett
    } catch(SQLException e) {
      System.out.println(e.getMessage());
      //closeConnection(); //TODO
    } // end of try
  }

  public void delCustomer(Customer customer){
    try {
      //loadDatabase();
      String sql = "DELETE FROM customers WHERE id=" + customer.getId() +";";
      statement.executeUpdate(sql);
      // TODO Rückgabewert für Status wäre nett
    } catch(SQLException e) {
      System.out.println(e.getMessage());
      //closeConnection(); //TODO
    } // end of try
  }

}
