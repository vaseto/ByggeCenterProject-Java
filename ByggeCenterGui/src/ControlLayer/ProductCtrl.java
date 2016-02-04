package ControlLayer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ModelLayer.Item;

import ModelLayer.Product;



public class ProductCtrl extends ItemCtrl {
    private ArrayList<Product> products;
    /**
     *  Constructor of class ItemCtrl
     */
    public ProductCtrl(){
        
        super();
        setProducts(super.getItems().getProducts());
        loadProduct();
    }

    /**
     * Creates a new item(Product), add it to a collection and save it to the file
     */
    public Product createItem(String name, double price, int quantity, String category, String location,int min,int max) {
        Product product = new Product(name, price, quantity, category, location);
        setMinimumBorder(product,min);
        setMaximumBorder(product,max);
        getProducts().add((Product) product);
        storeItemInFile();
        return product;
    }
    /**
     * Search for paricular Item (Product) and return it if the product is found otherwise return null.
     * @param barcode  is the of the fields of the Product class by which instacne of product class is searched.
     * @return p is a instance of the Product class or null if there was no match in searching.
     */
    @Override
    public Product getItem(String barcode) {
        for(Product p : getProducts()){
            if(p.getBarcode().equals(barcode))
                return p;
        }
        
        return null;
    }
    
    /**
     * check whether there is a particular product searched by id.
     * @return ture if there is a coincidence 
     * @return false if there is no found item(product)
     */
    public boolean checkForItem(String itemId){
        if(getItem(itemId) != null){
            return true;
        }else{
            return false;
        }
    }
    
    public Product getProductByName(String name){
     for(Product pr : products){
         if(pr.getName().equals(name)){
             return pr;
         }
     }
       return null;
    }
    
    /**
     * get a particular product searched by barcode and return its quantity.
     * @param barcode  is the of the fields of the Product class by which instacne of product class is searched.
     * @return getItem(barcode).getQuantity() is the quantity of particular product
     */ 
    public int getProductQuantity(String barcode){
        return getItem(barcode).getQuantity();
    }
    
    public Product findProductByName(String name){
        for(Product pr: products){
            if(pr.getName().equals(name)){
                return pr;
            }
        }
        return null;
    }
    
    public void setProductQuantity(Product pr,int quantity){
        pr.setQuantity(pr.getQuantity() + quantity);
        storeItemInFile();
    }
     /**
     * get a particular product searched by barcode and return its name.
     * @param barcode  is the of the fields of the Product class by which instacne of product class is searched.
     * @return getItem(barcode).getName() is the name of particular product
     */ 
    public String getProductName(String barcode){
        return getItem(barcode).getName();
    }
    /**
     * print on the screen all products instance.
     */
    public void showAll(){
        for(Product p : getProducts()){
           System.out.println(p.toString());
       }
    }
    /**
     * load all products from file
     */
    public ArrayList loadProduct(){
        return super.getItems().getProducts();
    }
     
    
    public boolean sendRequest(String barcode,int quantity){
        Product pr = getItem(barcode);
        int beforeOrd = pr.getQuantity();
        pr.setQuantity(quantity+beforeOrd);
        int afterOrd = pr.getQuantity();
        if(afterOrd > pr.getMaximum()){
            return true;
        }else{
            pr.setQuantity(beforeOrd);
            return false;
        }
    }
     /**
      * set the  maximum border for inventory of given product
      * @param barcode  is the of the fields of the Product class by which instacne of product class is searched.
      * @param maximum is the value whish is will replace the old value
      * 
      */
   
    public void setMaximumBorder(Product product, int maximum){
        product.setMaximum(maximum);
      
        
    }
    /**
     * @return  getItem(barcode).getMaximum() is the maximum border of the quantity
     */
    public int getMaximumBorder(String barcode){
        return getItem(barcode).getMaximum();
        
    }
     /**
      * set the  minimum border for inventory of given product
      * @param barcode  is the of the fields of the Product class by which instacne of product class is searched.
      * @param minimum is the value whish is will replace the old value
      */
    
    public void setMinimumBorder(Product product, int minimum){
        product.setMinimum(minimum);
    }
     /**
     * @return  getItem(barcode).getMinimum() is the maximum border of the quantity
     */
   public int getMinimumBorder(String barcode){
        return getItem(barcode).getMinimum();
    }
     /**
      * store product in file
      */
    @Override
    public void storeItemInFile() {
        try{
            
            FileOutputStream fos = new FileOutputStream("Product.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(getProducts());
            oos.close();
            fos.close();
            
        } catch (FileNotFoundException ex) {
            System.out.printf("the file   was not found");

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }
    
     /**
      * @return products is collection which store all products instance of class Product
      */

    public ArrayList<Product> getProducts() {
        return products;
    }


    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
     /**
     * search for particular item by barcode and then remove it.
     * @param barcode is the barcode of the product which is searching for removing
     */

    @Override
    public void removeItem(String barcode) {
       
                getProducts().remove(getItem(barcode));
        storeItemInFile();
        
    }
    /**
     * search for particular item(Product) by barcode and then return its name.
     * @param barcode is the barcode of the product which is searching for .
     */

    @Override
    public String getItemName(String barcode) {
  
                return getItem(barcode).getName();
        
            }
    /**
     * search for particular item(Product) by barcode and then return its price.
     * @param barcode is the barcode of the product which is searching for .
     */

    @Override
    public double getItemPrice(String barcode) {
       
             return getItem(barcode).getPrice();
        
    }
     /**
     * search for particular item(Product) by barcode and then return its quantity.
     * @param barcode is the barcode of the product which is searching for .
     */
    

    @Override
    public int getItemQuantity(String barcode) {
      
           return getItem(barcode).getQuantity();
      
    }
    /**
     * search for particular item(Product) by barcode and then return its category.
     * @param barcode is the barcode of the product which is searching for .
     */

    @Override
    public String getItemCategory(String barcode) {
       
                return getItem(barcode).getCategory();
                    
    }
    /**
     * search for particular item(Product) by barcode and then set its quantity.
     * @param barcode is the barcode of the machine which is searching for .
     *  @param quantity is the value which is will replace the old value
     */
     public void setQuantity(String barcode, int quantity){
        getItem(barcode).setQuantity(quantity);
                    storeItemInFile();
    }

    /**
     * return item and reduces its quantity by one
     * new
     */
     public Product getItemForSale(String barcode) {
         loadProduct();
         for(Product p : getProducts()){
             if(p.getBarcode().equals(barcode))
            	 p.setQuantity(p.getQuantity()-1);
             	
                 return p;
         }
         
         return null;
     }


}
