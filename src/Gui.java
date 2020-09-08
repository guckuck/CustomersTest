/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 07.09.2020
 * @author Kay Patzwald
 */
public class Gui extends JFrame {
  // Anfang Attribute

  private JButton bCreateCustomer = new JButton();
  private JTextField tfLastname = new JTextField();
  private JTextField tfFirstname = new JTextField();
  private JButton bLoadCustomer = new JButton();
  private JLabel lStatus = new JLabel();
  private JList jListCustomers = new JList();
  private DefaultListModel jListCustomersModel = new DefaultListModel();
  private JScrollPane jListCustomersScrollPane = new JScrollPane(jListCustomers);
  //private Connection connection;
  //private Statement statement;
  private JButton bDeleteCustomer = new JButton();
  private JTextField jTextFieldDelCustomer = new JTextField();
  private IDatenhaltung datenhaltung;
  // Ende Attribute

  public Gui() {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 662;
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Gui");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten

    bCreateCustomer.setBounds(207, 31, 187, 25);
    bCreateCustomer.setText("Create Customer");
    bCreateCustomer.setMargin(new Insets(2, 2, 2, 2));
    bCreateCustomer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bCreateCustomer_ActionPerformed(evt);
      }
    });
    cp.add(bCreateCustomer);
    tfLastname.setBounds(30, 30, 150, 20);
    tfLastname.setText("Lastname");
    cp.add(tfLastname);
    tfFirstname.setBounds(30, 65, 150, 20);
    tfFirstname.setText("Firstname");
    cp.add(tfFirstname);
    bLoadCustomer.setBounds(207, 65, 187, 25);
    bLoadCustomer.setText("Load Customer");
    bLoadCustomer.setMargin(new Insets(2, 2, 2, 2));
    bLoadCustomer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bLoadCustomer_ActionPerformed(evt);
      }
    });
    cp.add(bLoadCustomer);
    lStatus.setBounds(29, 118, 302, 20);
    lStatus.setText("Status");
    cp.add(lStatus);
    jListCustomers.setModel(jListCustomersModel);
    jListCustomersScrollPane.setBounds(413, 32, 198, 204);
    cp.add(jListCustomersScrollPane);
    bDeleteCustomer.setBounds(208, 200, 75, 25);
    bDeleteCustomer.setText("Delete Customer");
    bDeleteCustomer.setMargin(new Insets(2, 2, 2, 2));
    bDeleteCustomer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bDeleteCustomer_ActionPerformed(evt);
      }
    });
    cp.add(bDeleteCustomer);
    jTextFieldDelCustomer.setBounds(32, 200, 150, 20);
    cp.add(jTextFieldDelCustomer);
    bDeleteCustomer.setText("bDeleteCustomer");
    bDeleteCustomer.setMargin(new Insets(2, 2, 2, 2));
    cp.add(bDeleteCustomer);
    // Ende Komponenten

    setVisible(true);
  } // end of public Gui

  public Gui(Datenbank db) {
    this();
    this.datenhaltung = db;
  }

  public Gui(XML xml) {
    this();
    this.datenhaltung = xml;
  }

  // Anfang Methoden
  public void bCreateCustomer_ActionPerformed(ActionEvent evt) {

    String lastname = tfLastname.getText();    // Nutzereingabe holen
    String firstname = tfFirstname.getText();  // Nutzereingabe holen
    Customer cust = new Customer(firstname, lastname);
    ((Datenbank) datenhaltung).storeCustomer(cust);
    //Customer cust = new Customer(firstname, lastname);
    lStatus.setText("Customer stored.");
  } // end of bCreateCustomer_ActionPerformed

  public void bLoadCustomer_ActionPerformed(ActionEvent evt) {
    DefaultListModel model = new DefaultListModel(); // notwendig für die JList
    jListCustomers.setModel(model);                  // notwendig für die JList
    //loadDatabase();
    ArrayList<Customer> allCustomers = ((Datenbank) datenhaltung).getAllCustomers();
    allCustomers = datenhaltung.getAllCustomers();

    for (Customer cust : allCustomers) {
      model.addElement(cust.getFirstname() + " " + cust.getLastname());
    }

    lStatus.setText("All customers loaded.");
  } // end of bLoadCustomer_ActionPerformed

  /*  private void loadDatabase() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      if (connection == null) {
        connection = DriverManager.getConnection("jdbc:mysql://erp-learning.info:3306/customers_test", "customer", "customer123!");
        statement = connection.createStatement();
      } 
    } catch(Exception e) {
      System.out.println(e.getMessage());
      closeConnection();
    }// end of try     
  }*/
 /*private void closeConnection() {
    try {
      statement.close();
      connection.close();
    } catch(Exception e) {
      e.getMessage();
    } // end of try
    
  }*/
  public void bDeleteCustomer_ActionPerformed(ActionEvent evt) {
    int delCustNr = Integer.parseInt(jTextFieldDelCustomer.getText());
    ((Datenbank) datenhaltung).delCustomer(delCustNr);
    lStatus.setText("Customer was deleted!"); //TODO Fehlermeldung ggf. ergänzen

  } // end of bDeleteCustomer_ActionPerformed

  // Ende Methoden
} // end of class Gui

