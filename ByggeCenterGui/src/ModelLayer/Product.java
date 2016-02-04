package ModelLayer;

public class Product extends Item implements Comparable<Product>{
	/**
	 * 
	 */
	private String location;
	private int minimum;
	private int maximum;
        private int soldProducts;
	
	public Product(String name, double price, int quantity, String category,String location){
		super(name, price , quantity, category);
                soldProducts = 0;
		this.location = location;
	}

    public int getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Location " + location + " | " + super.toString() ;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

    @Override
    public int compareTo(Product pr) {
       return Double.compare(this.getSoldProducts(),pr.getSoldProducts());
    }
}
