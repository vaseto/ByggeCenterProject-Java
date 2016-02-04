package ControlLayer;

import java.io.*;
import java.util.LinkedList;
import ModelLayer.*;
import java.util.Map;
import java.util.TreeMap;
public class ProductSaleCtrl {

    private TradeContainer tr ;
    private LinkedList<ProductSale> sales;
    private LinkedList<Product> products;
    private ProductCtrl prCtrl;
    private ItemsReorderCtrl itrCtrl;
    private EmployeeCtrl empCtrl;
    private CustomerCtrl custCtrl;
    public ProductSaleCtrl(){
        setSales(new LinkedList<ProductSale>());
        products = new LinkedList<Product>();
        tr = TradeContainer.getInstance();
        prCtrl = new ProductCtrl();
        itrCtrl = new ItemsReorderCtrl();
        setSales(tr.getSales());
        custCtrl = new CustomerCtrl();
        empCtrl = new EmployeeCtrl();
    }

    public void newProductSale(double price, int dicount, String custID, String empID,String id){
        ProductSale pr = new ProductSale(calculatePrice(products), dicount, custID, empID,id);

        getSales().push(pr);
        removeSales();
        storeTradeInFile();
        prCtrl.storeItemInFile();

    }
    
     public void newProductSale(TreeMap map,double price, int dicount, Customer cust, Employee emp) {
        ProductSale pr = new ProductSale(products, getPriceOfWholeSale(map), dicount,cust,emp);
        getSales().push(pr);
        removeSales();
        prCtrl.storeItemInFile();
    }
    
    public double makeSale(Employee emp,Customer cust,TreeMap map,int discount){
        double price = getPriceOfWholeSale(map);
        newProductSale(map,price,discount,cust,emp);
        price = applyDiscount(price, discount);
        empCtrl.changeProductsSold(emp,price);
        custCtrl.changeProductsBought(cust, price);
        return price;
    }
    
     public double getPriceOfWholeSale(TreeMap<Product,Integer> map){
        double totPrice = 0;
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            totPrice+=entry.getKey().getPrice()*entry.getValue();
        }
         return totPrice;
    }
     
       public double calculatePrice(Product pr, int quantity) {
        return pr.getPrice()*quantity;
    }
    
        public double enterItem(Product pr, int quantity) {
        if (makeCalculation(pr, quantity)) {
            pr.setQuantity(pr.getQuantity()-quantity);
        } else {
            return -1;
        }
        return calculatePrice(pr,quantity);
    }
        
    public void newProductSale(double price, int dicount, Customer cust, Employee emp) {
        ProductSale pr = new ProductSale(calculatePrice(products), dicount,cust,emp);
        getSales().push(pr);
        removeSales();
        storeTradeInFile();
        prCtrl.storeItemInFile();

    }
    public double makeSale(Employee emp,Customer cust,LinkedList products,int discount){
        double price = calculatePrice(products);
        newProductSale(price,discount,cust,emp);
        price = applyDiscount(price, discount);
        prCtrl.storeItemInFile();
        empCtrl.changeProductsSold(emp,price);
        custCtrl.changeProductsBought(cust, price);
        return price;
    }
    //if list reach size of 5000 elements, the first 1000 which ware added are removed from the list
    private void removeSales(){
        if(getSales().size() == 5000){
            for(int i = 0; i < 1000; i++){

                getSales().removeLast();
            }
        }       

        storeTradeInFile();
    }
    
    public boolean checkDiscount(int discount){
        if(discount <= 20){
            return true;
        }else{
            return false;
        }   
    }
    
    public double applyDiscount(double total,int discount){
        if(checkDiscount(discount)){
            if(discount != 0){
               float disc = ((float)discount / 100) *(float) total;
               return total = (total - disc);
            }else{
                return total;
            }
        }
        return total;
    }
    
   
    public int calculateQuantity(String barcode,int sold,int ordering){
        Product pr = prCtrl.getItem(barcode);
        int start = pr.getQuantity();
        pr.setQuantity(start - sold);
        if(pr.getQuantity() <= pr.getMinimum()){
            itrCtrl.addProductForReorder(pr);
            prCtrl.sendRequest(barcode,ordering);
        }
        return pr.getQuantity();
    } 
    

    public boolean makeSale(String barcode,int quantity){
        Product pr = prCtrl.getItem(barcode);
        int stockItems = pr.getQuantity();
        if(stockItems >= quantity){
            return true;
        }else{
            return false;
        }
    }
    public double enterItem(Product pr, int quantity, LinkedList products) {
        if (makeCalculation(pr, quantity)) {
            for (int i = 0; i < quantity; i++) {
                products.add(pr);
            }
            pr.setQuantity(pr.getQuantity()-quantity);
        } else {
            return -1;
        }
        return calculatePrice(products);
    }
    public double enterItem(String itemId, int quantity,LinkedList products){
        if(prCtrl.getItem(itemId)!= null){
            for(int i = 0; i < quantity; i++){
                products.add(prCtrl.getItem(itemId));
            }
        }else{
            return -1;
        }

        return calculatePrice(products);
    }
    public boolean makeCalculation(Product prd, int quantity) {
        return prd.getQuantity() >= quantity;
    }


    public double calculatePrice(LinkedList<Product> products){
        double total = 0 ;
        for(Product pr : products){
            total = total + pr.getPrice();
        }
        return total;
    }

    //search all object in the list until there is a match by id 
    public ProductSale getSale(String id){
        for(ProductSale pr : getSales()){
            if(pr.getId().equals(id)){
                return pr;
            }
        }
        return null;
    }

    public void removeSale(String id){

        getSales().remove(getSale(id));
        storeTradeInFile();
    }

    public void storeTradeInFile(){
        try {
            FileOutputStream fos = new FileOutputStream(tr.getFileNameSale());
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(getSales());
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("the file   was not found");

        } catch (IOException ex) {
            ex.printStackTrace();
        } 

    }

    public LinkedList<ProductSale> getSales() {
        return sales;
    }

    public void setSales(LinkedList<ProductSale> sales) {
        this.sales = sales;
    }
    // TODO Auto-generated method stub
    public static void main(String[] args) {

        ProductSaleCtrl pr = new ProductSaleCtrl();

        System.out.println(pr.getSale("15"));

        System.out.println(pr.getSale("16"));
        System.out.println(pr.getSale("15"));
        pr.newProductSale(100, 300, "14", "15", "17");
        System.out.println(pr.getSale("17"));
        System.out.println(pr.getSale("16"));

    }

}
