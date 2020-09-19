/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kay
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XML implements IDatenhaltung {

  private ArrayList<Customer> allCustomers;
  private int maxID = -1;

  public XML() {
    readXML();
  }

  private void readXML() {
    allCustomers = new ArrayList<>();
    try {
      File inputFile = new File("customers.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
      NodeList nList = doc.getElementsByTagName("customer");
      System.out.println("----------------------------");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        int id;
        String firstname;
        String lastname;
        String email;
        Node nNode = nList.item(temp);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          System.out.println("ID : "
                  + eElement
                          .getElementsByTagName("id")
                          .item(0)
                          .getTextContent());
          
          id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
          System.out.println("First Name : "
                  + eElement
                          .getElementsByTagName("firstname")
                          .item(0)
                          .getTextContent());
          firstname = eElement.getElementsByTagName("firstname").item(0).getTextContent();
          System.out.println("Last Name : "
                  + eElement
                          .getElementsByTagName("lastname")
                          .item(0)
                          .getTextContent());
          lastname = eElement.getElementsByTagName("lastname").item(0).getTextContent();
          System.out.println("Email : "
                  + eElement
                          .getElementsByTagName("email")
                          .item(0)
                          .getTextContent());
          email = eElement.getElementsByTagName("email").item(0).getTextContent();
          Customer newCustomer = new Customer(firstname, lastname);
          newCustomer.setId(id);
          newCustomer.setEmail(email);
          allCustomers.add(newCustomer);
          if (id > maxID) {
            maxID = id;
          }
        }
        
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  public void storeCustomer(Customer customer) {
    //System.out.println("ID: " + customer.getId());
    if (customer.getId() == 0) {
      maxID = maxID + 1;
      customer.setId(maxID);
    }
    allCustomers.add(customer);
    saveXML();
  }

  public Customer getCustomer(int id) {
    for (Customer cust : allCustomers) {
      if (cust.getId() == id)
        return cust;
    }
    return null;
  }

  public ArrayList<Customer> getAllCustomers() {
    return allCustomers;
  }

  /**
   * Deletes a customer with id
   * @param id Customer to delete
   */
  public void delCustomer(int id) {
    for (Customer customer : allCustomers) {
      if (customer.getId() == id) {
        allCustomers.remove(customer);
        saveXML();
        return;
      }
    }
    
  }

  public void delCustomer(Customer customer) {
    allCustomers.remove(customer);
    saveXML();
  }

  private void saveXML() {
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter writer = null;
    try {
      writer = factory.createXMLStreamWriter(new FileOutputStream("customers.xml"));
      // Der XML-Header wird erzeugt
      writer.writeStartDocument();
      // Zuerst wird das Wurzelelement mit Attribut geschrieben
      writer.writeStartElement("data");
      //writer.writeAttribute("datum", "31.12.01");
      // Unter dieses Element das Element gast mit einem Attribut erzeugen
      for (Customer cust : allCustomers) {
        writer.writeStartElement("customer");
        writer.writeStartElement("id");
        writer.writeCharacters(String.valueOf(cust.getId()));
        writer.writeEndElement();
        writer.writeStartElement("firstname");
        writer.writeCharacters(cust.getFirstname());
        writer.writeEndElement();
        writer.writeStartElement("lastname");
        writer.writeCharacters(cust.getLastname());

        writer.writeEndElement();
        writer.writeStartElement("email");
        writer.writeCharacters(cust.getEmail());

        writer.writeEndElement();
        writer.writeEndElement();
      }
      writer.writeEndDocument();
      writer.close();
    } catch (FileNotFoundException ex) {
      Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
    } catch (XMLStreamException ex) {
      Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
