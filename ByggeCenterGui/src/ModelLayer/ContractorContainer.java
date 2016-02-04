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
public class ContractorContainer {
    private ArrayList<Contractor> contractors;
     public static ContractorContainer instance;
     
     public static ContractorContainer getInstance(){
         if(instance ==null){
             instance = new ContractorContainer();
         }
         return instance;
     }
     
     private ContractorContainer(){
         contractors = new ArrayList<>();
         contractors = loadPeople("contractors.ser");
     }
     
     public ArrayList<Contractor> getContractors(){
         return contractors;
     }
     
       public ArrayList loadPeople(String file){
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
                contractors = (ArrayList)input.readObject(); 
        }catch(EOFException | FileNotFoundException | NullPointerException e){
                 try {
                     input.close();
             } catch (IOException | NullPointerException ex){
                 System.out.println(ex);
             }
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return contractors;
        }
    }

