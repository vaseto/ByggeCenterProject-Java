
package ModelLayer;
import java.util.TreeMap;

/**
 * Public class Contractor subclass of Person
 */
public class Contractor extends Person{
    
    private TreeMap<Product,Integer> requestForReorder;
    
    /** Constructor of class Contractor
     */
   
    public Contractor(String name, String address, String phone, String email) {
        super(name, address, phone, email);
        requestForReorder = new TreeMap<>();
    }
    
    /** Returns a list of products requested from the contractor
     */
    public TreeMap<Product,Integer> getRequestForReorder(){
        return requestForReorder;
    }
}
