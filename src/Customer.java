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

public class Customer {
  private int id;
  private String firstname;
  private String lastname;
  public static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
  
  public Customer(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
    allCustomers.add(this);
  }
  
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  
  public String getFirstname() {
    return firstname;
  }
  
  public int getID() {
    return id;
  }
  
  public String getLastname() {
    return lastname;
  }



}
