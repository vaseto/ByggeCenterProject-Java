package ControlLayer;

import java.util.ArrayList;

import ModelLayer.Customer;
import ModelLayer.CustomerContainer;
import ModelLayer.Group;
import ModelLayer.Item;
import ModelLayer.Rent;

/**
 *  Public class CustomerCtrl used to manipulate customer objects
 */
public class CustomerCtrl extends PersonCtrl{
    private final String fileName = "customers.ser";
    private ArrayList<Customer> customers;
    private CustomerContainer custCont;

    /**
     * Constructor of class CustomerCtrl
     */
    public CustomerCtrl() {
        customers = new ArrayList<>();
        custCont = CustomerContainer.getInstance();
        customers = custCont.getCustomers();
    }

    /**
     * Creates a new customer and save it to the file
     */
    public Customer createCustomer(String name, String address, String phone, String email,
    Group group, double balance, String creditCardNo, double bill,ArrayList customers) {
        Customer cust = new Customer(name, address, phone, email,group, balance, creditCardNo, bill);
        addPerson(fileName,cust,customers);
        return cust;
    }
    
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
        public Customer findCustomerByName(String name){
        for(Customer cust : customers){
            if(cust.getName().equals(name)){
                return cust;
            }
        }
        return null;
    }
    

    /** @param totalPrice is the price of the sale 
     *  @param amount is the amount a customer pays
     *  @return the difference from the calculation
     */
    public double makePayment(double totalPrice, double amount) throws Exception{
        if(totalPrice <= amount){
            // Calculate the change
            double change =  amount - totalPrice;
            // Reset the total
            totalPrice = 0;
            return change;
        } else{
           throw new Exception();
        }
    }

    /**
     *  Return a customer found by id from the collection null otherwise
     */
    public Customer findCustomer(String id) {
        for (Customer cust : customers) {
            if (cust.getID().equals(id)) {
                return cust;
            }
        }
        return null;
    }
    
    /**
     * Changes the group of a found customer and save the changes to the file
     */
    public void changeGroup(String id,Group grp){
       findCustomer(id).setGroup(grp);
       save(fileName,customers);
    }
    
    /**
     * Changes the balance of a found customer and save the changes to the file
     */
    public void changeBalance(String id, double str){
        findCustomer(id).setBalance(str);
        save(fileName,customers);
    }
    
    /**
     * Changes the creditCardNo of a found customer and save the changes to the file
     */
    public void changeCreditCardNo(String id,String str){
        findCustomer(id).setCreditCardNo(str);
        save(fileName,customers);
    }
    
    /**
     * Changes the bill of a found customer and save the changes to the file
     */
    public void changeBill(String id,double str){
        findCustomer(id).setBill(str);
        save(fileName,customers);
    }
    
    /** @return the filename of the file where customer objects are kept
     */
    public String getFileName(){
        return fileName;
    }
    
    /** @return the balance of a find customer by id
     */
    public double getBalance(String id){
        return findCustomer(id).getBalance();
    }
    
    /** @return the bill of a find customer by id
     */
    public double getBill(String id){
        return findCustomer(id).getBill();
    }
    
    /**  @return the creditCardNo of a find customer by id 
     */
    public String getCreditCardNo(String id){
        return findCustomer(id).getCreditCardNo();
    }
    
    public void changeProductsBought(Customer cs,double total){
        cs.setProductsBought(cs.getProductsBought() + total);
        save(fileName,customers);
    }
    
    /**
     * @return the group of a find customer by id
     */
    public String getGroup(String id){
        return findCustomer(id).getGroup().getName();
    }
    
    /** @return an arrayList of ordered goods by a customer 
     */
    public ArrayList getOrderedGoods(String id){
        return findCustomer(id).getOrderedGoods();
    }
    
    /** adds an arrayList of items to a customer order goods
    */
    public void addProducts(String id,ArrayList items){
        getOrderedGoods(id).add(items);
    }
    
    /** remove products from a customer's products 
     */
    public void removeProducts(String id,ArrayList items){
        getOrderedGoods(id).removeAll(items);
    }
    //new
    public void addRent(String custID, Rent rent){
    	findCustomer(custID).getRents().add(rent);
    	save(fileName, customers);
    }
    
   
    
}