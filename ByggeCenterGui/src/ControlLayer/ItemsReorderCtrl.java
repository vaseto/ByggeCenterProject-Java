package ControlLayer;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import ModelLayer.*;
/**
 * Write a description of class ItemsReorderCtrl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemsReorderCtrl
{
    private Queue<Product> products;
    private ItemsReorderContainer container ;
     /**
     *  Constructor of class ItemsReorderCtrl
     */
    public ItemsReorderCtrl(){
        
        container = ItemsReorderContainer.getInstance();
        products = container.getProductReorder();
    }
        /**
      * store product in file
      */
    public void storeItemInFile() {
        try{
            
            FileOutputStream fos = new FileOutputStream("ProductReorder.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(products);
            oos.close();
            fos.close();
            
        } catch (FileNotFoundException ex) {
            System.out.printf("the file   was not found");

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }
    /**
     * add product instance in the collection and store it to a file.
     */
    public void addProductForReorder(Product product){        
        products.add(product);
        storeItemInFile();
    }
    /**
      * @return products is a collection which store all product instances which are met
      * the minimum porder of a quantity.
      */
    
    public Queue<Product> getProductsForReorder(){
        
        return products;
    }
    
    /**
     * Get eact  product from the products collection and set  new quantity for each product
     */
     
     public boolean enterItemForReorder(Product pr,int quantity){
        return pr.getMaximum() < pr.getQuantity() + quantity;   
     }
     
     public void makeReorder(TreeMap<Product,Integer> map,Contractor cont){
         for(Map.Entry<Product,Integer> entry : map.entrySet()){
             cont.getRequestForReorder().put(entry.getKey(), entry.getValue());
         }
     }
     
     public void addProductsToStock(TreeMap<Product,Integer> map){
         ProductCtrl prCtrl = new ProductCtrl();
         for(Map.Entry<Product,Integer> entry : map.entrySet()){
            prCtrl.setProductQuantity(entry.getKey(),entry.getValue());
         }
     }
     
     public void addProductToStock(Product pr,int i){
         ProductCtrl prCtrl = new ProductCtrl();
         prCtrl.setProductQuantity(pr, i);
     }
    
}
