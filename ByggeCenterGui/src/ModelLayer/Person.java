package ModelLayer;
import java.io.Serializable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
 * Super claass Person 
 * 
 */
public class Person implements Serializable{
	 private static int idCounter = 1;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String id;
    
    
    /** Constructor of super class Person 
     */
    public Person(String name,String address, String phone, String email){
        getCounter();
        this.name = name; 
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.id = idCounter + "";
        idCounter++;
        saveCounter();
    }
    private int getCounter(){
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Counter"));
            try {
                idCounter = (int) input.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(EOFException | FileNotFoundException | NullPointerException e){
            System.out.println(e);
            System.out.println(e.getCause());
                 try {
                     input.close();
             } catch (IOException | NullPointerException ex){
                 System.out.println(ex);
             }
        } catch (IOException ex) {
            System.out.println(ex);
        }
       return idCounter;
    }
        
    
    
    
    private void saveCounter(){
          ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Counter"));
            out.writeObject(idCounter);
        } catch (IOException e) {
            System.out.println("Io exception while saving");
        } finally {
            try {
                out.close();
            } catch (IOException es) {
                System.out.println("error with file");
            }
        }
    }
    
    
    /** Return the name of the person
     */
    public String getName(){
        return name;
    }
    
    /**  Return the address of the person
     */
    public String getAddress(){
        return address;
    }
    
    /**  Return the phone of the person
     */
    public String getPhone(){
        return phone;
    }
    
    /**  Return the email of the person
     */
    public String getEmail(){
        return email;
    }
    
    /**  Return the id of the person
     */
    public String getID(){
        return id;
    }
    
    /**  Changes the name of the person
     */
    public void setName(String newName){
        name = newName;
    }
    
    /**  Changes the address of the person
     */
    public void setAddress(String newAddress){
        address = newAddress;
    }
    
    /**  Changes the phone of the person
     */
    public void setPhone(String newPhone){
        phone = newPhone;
    }
    
    /**  Changes the email of the person
     */
    public void setEmail(String newMail){
        email = newMail;
    }
    
    /**  Changes the id of the person
     */
    public void setID(String newID){
        id = newID;
    }
    
    /** Return a String representation of the person
     */
    public String toString(){
   
    	        return "Name: " + name + " | Address: " + address + " | Phone: " + phone 
    	        + " | Email: " + email + " | Id: " + id + " ";
    	    
    }
    
}
