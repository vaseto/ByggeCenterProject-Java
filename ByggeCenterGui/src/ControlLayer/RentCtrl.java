package ControlLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;

import ModelLayer.Rent;

public class RentCtrl extends TradeCtrl{
    private ArrayList<Rent> rents;
    private String fileName ;
    private MachineCtrl machineCtrl;
    private Stack<Rent> exceededRents;
    
    /**
     *  Constructor of class ItemCtrl
     */
    public RentCtrl(){
        
        super();
        setFileName(super.getTradeCont().getFileNameRent());
        rents = super.getTradeCont().getRents();
        machineCtrl = new MachineCtrl();
        exceededRents = new Stack<Rent>();
    }
     /**
     * Creates a new newRent, add it to a collection and save it to the file
     */
    public void newRent( int dicount, String custID, String empID,String id, int period, String borrowDate, String copyID, String machineBarcode){
    
    	EmployeeCtrl empl = new EmployeeCtrl();
    	CustomerCtrl customer = new CustomerCtrl();
        double price= machineCtrl.getItemPrice(machineBarcode)*period;
        price = Math.round(price * 100.0) / 100.0;
        if(dicount> 0){
            float discount = (float)price* dicount/100;
            price = Math.round(price * 100.0) / 100.0;
         price =  (float)(price - discount);
        
        }
        price = Math.round(price * 100.0) / 100.0;
        machineCtrl.changeCopyStatus(copyID, false);
        empl.changeProductsSold(empl.findEmployee(empID), price);
        
        Rent rent  = new Rent(price, dicount,  custID, empID, id, period,  borrowDate, copyID, machineBarcode,returningDate( period, borrowDate));
        
    
        rents.add(rent);
        customer.addRent(custID, rent);
        machineCtrl.storeItemInFile();
        super.storeTradeInFile(fileName, rents);
        
    }
    /**
     * calculate returning date
     */
    public  String returningDate(int period, String borrowDate){
    	
    	String dt = borrowDate;  // Start date
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Calendar c = Calendar.getInstance();
    	try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	c.add(Calendar.DATE, period);  // number of days to add
    	dt = sdf.format(c.getTime());
    	
    	return dt;
    	
    }
    
    /**
     * returning machine 
     * @return -1 the method return 1 if the machine is returned earlier 
     * @return -2 if the final date for returning is equal to the date of return 
     * @return days this is the number of days exceeded the final date for renting
     * @return 0 if there is no match
     */
    public int returnMachine(String rentID, String dateOfReturn){
    	
    	String dateForReturning = findTrade(rentID).getReturningDate();  // Start date
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Calendar c = Calendar.getInstance();
    	try {
			c.setTime(sdf.parse(dateForReturning));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String dateOfReturning = dateOfReturn;  // Start date
    	
    	Calendar ca = Calendar.getInstance();
    	try {
			ca.setTime(sdf.parse(dateOfReturning));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

        if(c.after(ca)){
          
            machineCtrl.changeCopyStatus(findTrade(rentID).getCopy(), true);
        	removeTrade(rentID);
        	return -1;
        }

        if(c.before(ca)){
        	int days = daysBetween(c.getTime(),ca.getTime());
        	 machineCtrl.changeCopyStatus(findTrade(rentID).getCopy(), true);
         	
         	
            return days;
            
        }

        if(c.equals(ca)){
        
            machineCtrl.changeCopyStatus(findTrade(rentID).getCopy(), true);
        	removeTrade(rentID);
        	return -2;
        }
    	return 0;
    	
    	
    }
    public int checkRent(String rentID, String dateOfReturn){

    	String dateForReturning = findTrade(rentID).getReturningDate();  // Start date
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Calendar c = Calendar.getInstance();
    	try {
			c.setTime(sdf.parse(dateForReturning));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String dateOfReturning = dateOfReturn;  // Start date
    	
    	Calendar ca = Calendar.getInstance();
    	try {
			ca.setTime(sdf.parse(dateOfReturning));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

        if(c.after(ca)){
           
           
        	return -1;
        }

        if(c.before(ca)){
        	int days = daysBetween(c.getTime(),ca.getTime());
        	
         	
            return days;
            
        }

        if(c.equals(ca)){
           
        	return -2;
        }
    	return 0;
    	
    }
    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}
    
     /**
     * @return fileName is the name of the file which is used for storing order instances
     */
    
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
   /**
     * Returns all Rents instance
     * @return rents is the collection which store all rents instance
     */

    public ArrayList<Rent> getRents() {
    	
        return rents;
    }

    public void setRents(ArrayList<Rent> rents) {
        this.rents = rents;
    }
    /**
     * Search instance of the class Rent by id and if it is found remove it.
     */

    @Override
    public void removeTrade(String id) {
        
        getRents().remove(findTrade(id));
        super.storeTradeInFile(getFileName(), getRents());
        
    }

    /**
     * Search instance of the class Order by id and if it is found return its price.
     * @return findTrade(id).getPrice() is the price field of the class Order.
     */
    @Override
    public double getTradePrice(String id) {
        
        return findTrade(id).getPrice();
    }
     /**
     * Search instance of the class Order by id and if it is found return it.
     * @return or is the instance of the class Order
     */

    @Override
    public Rent findTrade(String id) {
    	
        for(Rent r: getRents()){
            if(r.getId().equals(id))
                return r;
        }
        
        return null;
    }

     /**
     * Search instance of the class Rent by id and if it is found return it.
     * @return findTrade(id).getCustID() is the custID field of the class Rent.
     */

    @Override
    public String getTradeCustID(String id) {
    
        return findTrade(id).getCustID();
    }

     /**
     * Search instance of the class Rent by id and if it is found return it.
     * @return findTrade(id).getEmpID() is the empID field of the class Rent.
     */
    @Override
    public String getTradeEmplID(String id) {
        
        return findTrade(id).getEmpID();
    }

    public MachineCtrl getMachineCtrl() {
        return machineCtrl;
    }

    public void setMachineCtrl(MachineCtrl machineCtrl) {
        this.machineCtrl = machineCtrl;
    }
   
    
    private String date(){
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		// if it is January returns 0
		if(month == 0){
			month = 1;
		}
		int year = calendar.get(Calendar.YEAR);
		return  day + "/" + month + "/" + year ;
	}
	public  Stack<Rent> getExceededRents() {
		String dateOfReturning = date();
		for(Rent r: getRents()){
			
			int index = checkRent(r.getId(),dateOfReturning  );
			if(index > 0){
				exceededRents.add(r);
			}
		}
		
		
		return exceededRents;
	}
	public void setExceededRents(Stack exceededRents) {
		this.exceededRents = exceededRents;
	}
	public static void main(String[] args){
		RentCtrl rent = new RentCtrl();
		System.out.println(rent.findTrade("12"));
	}
}
