package ControlLayer;

import java.util.ArrayList;

import ModelLayer.Order;



public class OrderCtrl extends TradeCtrl {
    private ArrayList<Order> orders;
    private String fileName ;

    /**
     *  Constructor of class OrderCtrl
     */
    public OrderCtrl(){
        
        super();
        
        
        orders = super.getTradeCont().getOrders();
        fileName = super.getTradeCont().getFileNameOrder();
    }
    /**
     * Creates a new Order, add it to a collection and save it to the file
     * change the employee productsSold filed
     * change the customer's balance field when subtract price from it.
     */
    public void newOrder(double price, int dicount, String custID, String empID, String id){
    	CustomerCtrl customer = new CustomerCtrl();
    	EmployeeCtrl empl = new EmployeeCtrl();
        Order order = new Order(price,dicount, custID, empID, id);
        empl.changeProductsSold(empl.findEmployee(empID), price);
        customer.changeBalance(custID, (customer.getBalance(custID) - price));
        getOrders().add(order);
        super.storeTradeInFile(fileName, getOrders());
        
        
    }
    /**
     * @return fileName is the name of the file which is used for storing order instances
     */
    public String getFilename(){
        return fileName;
    }
    /**
     * Search instance of the class Order by id and if it is found return it.
     * @return or is the instance of the class Order
     */
    @Override
    public Order findTrade(String id) {
        
        for(Order or : orders ){
            
            if(or.getId().equals(id)){
                return  or;
            }
        }
        return null;
    }
    /**
     * Search instance of the class Order by id and if it is found remove it.
     */

    @Override
    public void removeTrade(String id) {
        getOrders().remove(findTrade(id));
        super.storeTradeInFile(fileName, getOrders());
        
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
     * @return findTrade(id).getCustID() is the custID field of the class Order.
     */

    @Override
    public String getTradeCustID(String id) {
        return findTrade(id).getCustID();
        
    }
    /**
     * Search instance of the class Order by id and if it is found return it.
     * @return findTrade(id).getEmpID() is the empID field of the class Order.
     */

    @Override
    public String getTradeEmplID(String id) {
        return findTrade(id).getEmpID();
        
    }
    
    /**
     * Returns all Order instance
     * @return orders is the collection which store all orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }
     /**
     * Returns all Order instance
     * @return orders is the collection which store all orders
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    public static void main(String[] args){
    	OrderCtrl order = new OrderCtrl();
    	
    	System.out.println(order.getTradeCustID("1"));
    	System.out.println(order.findTrade("1"));
    }
    
    
}
