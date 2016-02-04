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
public class EmployeeContainer {
     private ArrayList<Employee> employees;
     public static EmployeeContainer instance;
     
     public static EmployeeContainer getInstance(){
         if(instance ==null){
             instance = new EmployeeContainer();
         }
         return instance;
     }
     
     private EmployeeContainer(){
         employees = new ArrayList<>();
         employees = loadPeople("employees.ser");
     }
     
     public ArrayList<Employee> getEmployee(){
         return employees;
     }
     
       public ArrayList loadPeople(String file){
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
                employees = (ArrayList)input.readObject(); 
        }catch(EOFException | FileNotFoundException | NullPointerException e){
                 try {
                     input.close();
             } catch (IOException | NullPointerException ex){
                 System.out.println(ex);
             }
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return employees;
        }
    }


