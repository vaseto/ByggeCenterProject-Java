/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelLayer;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author viva
 */
public class CustomerContainer {
     private ArrayList<Customer> customers;
     public static CustomerContainer instance;
     
     public static CustomerContainer getInstance(){
         if(instance ==null){
             instance = new CustomerContainer();
         }
         return instance;
     }
     
     private CustomerContainer(){
         customers = new ArrayList<>();
         customers = loadPeople("customers.ser");
     }
     
     public ArrayList<Customer> getCustomers(){
         return customers;
     }
     
       public ArrayList loadPeople(String file){
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
                customers = (ArrayList)input.readObject(); 
        }catch(EOFException | FileNotFoundException | NullPointerException e){
                 try {
                     input.close();
             } catch (IOException | NullPointerException ex){
                 System.out.println(ex);
             }
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return customers;
        }
    }

