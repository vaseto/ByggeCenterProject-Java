package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.CustomerCtrl;
import ControlLayer.EmployeeCtrl;
import ControlLayer.OrderCtrl;
import ControlLayer.ProductCtrl;
import ControlLayer.ProductSaleCtrl;
import ModelLayer.Product;

public class RegistrateOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private LinkedList<Product> basket;
	private ProductCtrl product;
	private ProductSaleCtrl saleCtrl;
	private OrderCtrl order;
	private Random random;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrateOrder frame = new RegistrateOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrateOrder() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/

	}
	protected void registrateOrder(){
		contentPane = (JPanel) OrderMenu.getFrame().getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		random = new Random();
		order = new OrderCtrl();
		saleCtrl = new ProductSaleCtrl();
		product = new ProductCtrl();
		basket = new LinkedList<Product>();
		
		JLabel lblAllMachines = new JLabel("Creat machine - Menu");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(283, 32, 150, 24);
		contentPane.add(lblAllMachines);
		
		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				OrderMenu.mainMenu();
			}
		});
		button.setBounds(100, 320, 150, 23);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setBounds(71, 100, 150, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPricePerDay = new JLabel("Quantity");
		lblPricePerDay.setBounds(71, 145, 150, 14);
		contentPane.add(lblPricePerDay);
		
		JLabel lblQuantity = new JLabel("Customer ID");
		lblQuantity.setBounds(398, 100, 139, 14);
		contentPane.add(lblQuantity);
		
		JLabel lblCategoryOfMachine = new JLabel("Employee ID");
		lblCategoryOfMachine.setBounds(398, 145, 139, 14);
		contentPane.add(lblCategoryOfMachine);
		
		textField = new JTextField();
		textField.setBounds(203, 97, 103, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(203, 142, 103, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(547, 97, 103, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(547, 142, 103, 20);
		contentPane.add(textField_3);
		
		JButton btnAdd = new JButton("Add to basket");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String barcode = textField.getText();
					int quantity = Integer.parseInt(textField_1.getText());
					if(saleCtrl.makeSale(barcode, quantity) == false){
						JOptionPane.showMessageDialog(null, "There is no enough products");
					}else{
					
						for(int i = 0 ; i< quantity;i++){
							basket.add(product.getItemForSale(barcode));
						}
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(122, 179, 128, 23);
		contentPane.add(btnAdd);
		
		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(268, 223, 150, 14);
		contentPane.add(lblDiscount);

		textField_4 = new JTextField("0");
		textField_4.setColumns(10);
		textField_4.setBounds(374, 220, 103, 20);
		contentPane.add(textField_4);

		JButton btnCreateAndFinish = new JButton("Create and Finish");
		btnCreateAndFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					CustomerCtrl customer = new CustomerCtrl();
					EmployeeCtrl employee = new EmployeeCtrl();
					String custID = textField_2.getText();
					String empID = textField_3.getText();
					if (employee.findEmployee(empID) == null || customer.findCustomer(custID) == null) {
						JOptionPane.showMessageDialog(null, "Incorrect customer or employee ID!");
					} else {
						String orderID = generateOrderID();
						int discount = Integer.parseInt(textField_4.getText());
						double sum = saleCtrl.calculatePrice(basket);

						sum = saleCtrl.applyDiscount(sum, discount);
						sum = Math.round(sum * 100.0) / 100.0;

						if (customer.getBalance(custID) > sum) {
							order.newOrder(sum, discount, custID, empID, orderID);
							product.storeItemInFile();

							JOptionPane.showMessageDialog(null, "The sum is: " + sum);
							JOptionPane.showMessageDialog(null, "Successful made order!");
						} else {
							JOptionPane.showMessageDialog(null,
									"There is no enough money in customer's balance to pay the bill");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect Data");
					ex.printStackTrace();
				}
			}
		});
		btnCreateAndFinish.setBounds(442, 320, 150, 23);
		contentPane.add(btnCreateAndFinish);
	}

	private String generateOrderID() {
		try {
			boolean check = false;
		String number = "";
		while (check == false) {
			number = Integer.toString(random.nextInt(1000));
			if (order.findTrade(number) == null) {// check whether there is order with this id 
				check = true;// just for sure
				return number;
			}
		}
		
		}catch(Exception ex){
		ex.printStackTrace();	
		}
		return null;
	}
}
