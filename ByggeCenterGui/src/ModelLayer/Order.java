package ModelLayer;

import java.io.Serializable;

public class Order extends Trade implements Serializable{
	
	private static final long serialVersionUID = -4768524439131783770L;
	
	public Order(double price, int dicount, String custID, String empID, String id) {
		super(price, dicount, custID, empID,id);
	}
	public Order(double price, int dicount,Customer cust,Employee emp) {
		super(price, dicount, cust,emp);
	}
	@Override
	public String toString() {
		return "Order   Order ID=" + getId()+ ", Dicount=" + getDicount() + ", Price=" + getPrice()
				+ ", Customer ID=" + getCustID() + ", Employee ID=" + getEmpID() + "\n";
	}
	
}
