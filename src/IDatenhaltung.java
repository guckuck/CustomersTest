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

public interface IDatenhaltung {
  public void storeCustomer(Customer customer);
  public Customer getCustomer(int id);
  public ArrayList<Customer> getAllCustomers();
  public void delCustomer(int id);
  public void delCustomer(Customer customer);
}
