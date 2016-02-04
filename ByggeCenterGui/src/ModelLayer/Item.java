package ModelLayer;

import java.io.Serializable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
public abstract class Item implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8256556348195959378L;
	 private static int barcodeCounter = 1;
	private String name;
	private String barcode;
	private double price;
	private int quantity;
	private String category;
	
	public Item(String name, double price, int quantity, String category){
        getCounter();
        setName(name);
        this.barcode = barcodeCounter +"";
        barcodeCounter++;
        setPrice(price);
        setQuantity(quantity);
    	setCategory(category);
        saveCounter();
    }  
	
	public Item(String name, String barcode, double price, int quantity, String category){
		setName(name);
		setBarcode(barcode);
		setPrice(price);
		setQuantity(quantity);
		setCategory(category);
	}
	
	 private void saveCounter(){
         ObjectOutputStream out = null;
       try {
           out = new ObjectOutputStream(new FileOutputStream("BarcodeCounter.ser"));
           out.writeObject(barcodeCounter);
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
       
       
       private int getCounter(){
       ObjectInputStream input = null;
       try {
           input = new ObjectInputStream(new FileInputStream("BarcodeCounter.ser"));
           try {
               barcodeCounter = (int) input.readObject();
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
      return barcodeCounter;
   }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Name " + name + " | barcode " + barcode + " | price=" + price + " | quantity=" + quantity
				+ " | category=" + category;
	}
	

}
