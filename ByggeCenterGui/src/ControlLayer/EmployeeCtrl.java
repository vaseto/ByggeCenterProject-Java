package ControlLayer;

import ModelLayer.*;
import java.io.Serializable;
import java.util.*;
/**
 * Public class EmployeeCtrl used to manipulate employee objects
 */
public class EmployeeCtrl extends PersonCtrl implements Serializable{
    private ArrayList<Employee> employees;
    private final String fileName = "employees.ser";
    private ProductSaleCtrl psCtrl;
    private EmployeeContainer empCont;
    /** Constructor of class EmployeeCtrl 
     */
    public EmployeeCtrl(){
       employees = new ArrayList<>();
       empCont = EmployeeContainer.getInstance();
       employees  = empCont.getEmployee();
    }
    

    /** Creates a new employee and ads it to the collection
     */
    public Employee createEmployee(String name,String address, String phone, String email, String bankAcc,String position, double salary,ArrayList employees){
       Employee emp = new Employee(name, address, phone, email, bankAcc, position, salary);
       addPerson(fileName,emp,employees);
       return emp;
    }
    
    public ArrayList<Employee> getEmployees(){    
        return employees;
    }
    
    /** 
     * @return an Employee object found by its id
     */ 
    public Employee findEmployee(String id){
        for(Employee emp : employees){
            if(emp.getID().equals(id)){
                return emp;
            }
        }
        return null;
    }    
    
    /** Makes new sale
     * @param prSaleID is the id of the sale
     * @param empID is the id of the employee doing the sale
     * @param cusId is the id of the customer paying for the sale
     * @param products is a LinkedList which holds all the products in the sale
     * @param discount is the discount percentage of the whole price of the sale
     */    
    
    
    /** Finds an employee with the position of Manager and send him a request for delivery
     */
    public void makeRequest(){
        for(Employee emp : employees){
            if(emp.getPosition().equals("Manager")){
            }
        }
    }
                
    /**
     *  Changes the position of a found employee and save the changes to the file
     */
    public void changePosition(String id, String str){
        findEmployee(id).setPosition(str);
    }
    
    /**
     *  Changes the salary of a found employee and save the changes to the file 
     */
    public void changeSalary(String id, double str){
        findEmployee(id).setSalary(str);
    }
    
    /** @return the file where the employees are kept
     */
    public String getFileName(){
        return fileName;
    }
    
    /**
     *  Changes the bank account of a found employee and save the changes to the file
     */
    public void changeBankAcc(String id, String str){
        findEmployee(id).setBankAcc(str);
    }
    
    public Employee findEmployeeLoginName(String loginName){
        for(Employee emp : employees){
            if(emp.getLoginName().equals(loginName)){
                return emp;
            }
        }
        return null;
    }
    
    public Employee findEmployeePassword(String loginName,String password){
        Employee emp = findEmployeeLoginName(loginName);
        if(emp != null){
            if(emp.getPassword().equals(password)){
                return emp;
            }
        }
        return null;
    }

    
    /** @return the bankAcc of an employee found by id 
     */
    public String getBankAcc(String id){
        return findEmployee(id).getBankAcc();
    }
    
    /** @return the position  of an employee found by id
     */
    public String getPosition(String id){
        return findEmployee(id).getPosition();
    }
    
    public void changeProductsSold(Employee e, double amount){
       e.setProductsSold(e.getProductsSold() + amount);
        save(fileName, employees);
    }
       
    
    public double getProductsSold(Employee e){
        return e.getProductsSold();
    }
  
}
