package ModelLayer;
import java.util.ArrayList;
import java.util.LinkedList;
public class ProductSale extends Trade {
    private LinkedList products;
    public ProductSale(double price,int discount, String custID, String empID, String id) {
        super(0,discount,custID,empID,id);
    }
    public ProductSale(double price,int discount, Customer cust,Employee emp) {
        super(0,discount,cust,emp);
    }
    
       public ProductSale(LinkedList products,double price,int discount, Customer cust,Employee emp) {
        super(0,discount,cust,emp);
        this.products = products;
    }

}
