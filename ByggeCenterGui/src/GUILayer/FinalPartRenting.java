package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.CustomerCtrl;
import ControlLayer.EmployeeCtrl;
import ControlLayer.RentCtrl;

public class FinalPartRenting extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	private static int discount;
	private static String custID;
	private static String empID;
	private JLabel lblNewLabel ;
	private static int period;
	private static String borrowDate;
	EmployeeCtrl employeeCtrl = new EmployeeCtrl();
	RentCtrl rentCtrl = new RentCtrl();
	CustomerCtrl customerCtrl = new CustomerCtrl();
	private JTextField textField_3;
	private JTextField textField_7;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalPartRenting frame = new FinalPartRenting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void clock(){
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		// if it is January returns 0
		if(month == 0){
			month = 1;
		}
		int year = calendar.get(Calendar.YEAR);
		lblNewLabel.setText("Date " + day + "/" + month + "/" + year );
	}
	/**
	 * Create the frame.
	 */
	public FinalPartRenting() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		

	}
	protected void finalPartRenting(){
		contentPane = (JPanel) GUIRent.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		ChooseCopy copy = new ChooseCopy();
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				copy.chooseCopy();
			}
		});
		button.setBounds(114, 334, 150, 23);
		contentPane.add(button);
		

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(313, 261, 150, 37);
		contentPane.add(lblNewLabel);
		clock();
		
		JLabel lblAllMachines = new JLabel("Rent machine");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 30, 100, 24);
		contentPane.add(lblAllMachines);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerId.setBounds(27, 87, 111, 24);
		contentPane.add(lblCustomerId);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmployeeId.setBounds(428, 133, 100, 24);
		contentPane.add(lblEmployeeId);
		
		JLabel lblRetingPeriod = new JLabel("Reting period");
		lblRetingPeriod.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRetingPeriod.setBounds(27, 133, 100, 24);
		contentPane.add(lblRetingPeriod);
		
		JLabel lblDayOfBorrowing = new JLabel("Date of borrowing");
		lblDayOfBorrowing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDayOfBorrowing.setBounds(202, 179, 111, 24);
		contentPane.add(lblDayOfBorrowing);
		
		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiscount.setBounds(428, 87, 100, 24);
		contentPane.add(lblDiscount);
		
		textField = new JTextField();
		textField.setBounds(148, 90, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(323, 182, 24, 20);
		contentPane.add(textField_2);
		
		textField_4 = new JTextField("0");
		textField_4.setColumns(10);
		textField_4.setBounds(536, 90, 86, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(538, 136, 86, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(148, 136, 86, 20);
		contentPane.add(textField_6);
		
		JButton btnFinish = new JButton("Next");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					period = Integer.parseInt(textField_6.getText());
					custID = textField.getText();
					empID = textField_5.getText();
					//check whether the month start with 0 or not
					
					String testMonth = textField_3.getText();
					if(testMonth.contains("0")){
						
						String[] var = testMonth.split("");
						System.out.println(var[1]);
						testMonth = var[1];
					}
					borrowDate = textField_2.getText()+"/"+ testMonth + "/"+ textField_7.getText();
					discount = Integer.parseInt(textField_4.getText());
					if(discount < 0 && discount > 0){
						JOptionPane.showMessageDialog(null, "The amount of the discount is incorrect ");
					
					}else if(employeeCtrl.findEmployee(empID) == null){
						JOptionPane.showMessageDialog(null, "There is no employee with that ID! ");
					}else if(customerCtrl.findCustomer(custID)== null){
						JOptionPane.showMessageDialog(null, "There is no customer with that ID! ");
					}else{
																
						
						PayingRent paying = new PayingRent();
						paying.payingRent();
					
					}
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Incorrect data");
					ex.printStackTrace();
				}
			}
		});
		btnFinish.setBounds(438, 334, 150, 23);
		contentPane.add(btnFinish);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(372, 182, 24, 20);
		contentPane.add(textField_3);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(421, 182, 45, 20);
		contentPane.add(textField_7);
		
		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setBounds(357, 185, 15, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("/");
		label.setBounds(406, 185, 15, 14);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("e.g. 23/ 1/ 2016");
		lblNewLabel_2.setBounds(333, 205, 126, 23);
		contentPane.add(lblNewLabel_2);
	
	}
	public int getDiscount() {
		return discount;
	}
	public String getCustID() { 
		return custID;
	}
	public String getEmpID() {
		return empID;
	}

	public int getPeriod() {
		return period;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
}
