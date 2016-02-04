package ModelLayer;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trade implements Serializable {
    private static final long serialVersionUID = -4048681096091498081L;
    private double price;
    private int dicount;
    private  String custID;
    private String empID;
    private Employee emp;
    private Customer cust;
    private String id;
    private static int counter = 1;
    
    public Trade(double price, int dicount, String custID, String empID,String id) {
        this.price = price;
        this.dicount = dicount;
        this.custID = custID;
        this.empID = empID;
        this.id = id;
    }
    public Trade(double price, int dicount, Customer cust, Employee emp) {
        getCounter();
        this.price = price;
        this.dicount = dicount;
        this.cust = cust;
        this.emp = emp;
        this.id = counter +"";
        counter++;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDicount() {
        return dicount;
    }

    public void setDicount(int dicount) {
        this.dicount = dicount;
    }
    
    public void setId(String newId){
        id = newId;
    }
    
    public String getId(){
        return id;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String newCustID) {
        custID = newCustID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String newEmpID) {
        empID = newEmpID;
    }
    private int getCounter(){
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream("Counter"));
            try {
                counter = (int) input.readObject();
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
       return counter;
    }
        
    
    
    
    private void saveCounter(){
          ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("TradeCounter.ser"));
            out.writeObject(counter);
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


    @Override
    public String toString() {
		//set the number of decimal places
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		return "Trade [price=" + df.format(price) + ", dicount=" + dicount + ", Customer" + cust + ", Employee " + emp + " id " + id + "\n";
	}



}
