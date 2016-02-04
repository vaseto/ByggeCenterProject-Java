package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.EmployeeCtrl;
import ControlLayer.MachineCtrl;
import ControlLayer.RentCtrl;
import ModelLayer.Rent;

public class ReturnMachine extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private static String dateOfReturn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnMachine frame = new ReturnMachine();
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
	public ReturnMachine() {
	/**	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
	}
	
	/**
	 * Append all rents in a text area
	 * 
	 */
	RentCtrl rent = new RentCtrl();
	public void returnMachine(){
		contentPane = (JPanel) GUIRent.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				GUIRent.mainMenu();
			}
		});
		button.setBounds(106, 351, 160, 23);
		contentPane.add(button);
		
		// label return machine
		JLabel lblAllMachines = new JLabel("Return machine");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 30, 100, 24);
		contentPane.add(lblAllMachines);
		
		
		JLabel lblNewLabel = new JLabel("Input the ID of the rent");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(79, 264, 153, 29);
		contentPane.add(lblNewLabel);
		//text field
		textField = new JTextField();
		textField.setBounds(289, 269, 141, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//text area with scroll
		textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(46, 78, 622, 179);
		// appending all rents and printing on the screen
	
		
		contentPane.add(sp);
		
		// return rent and going back to the start menu
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//check whether the month start with 0 or not
					
				
					MachineCtrl machine = new MachineCtrl();
					RentCtrl rent = new RentCtrl();
					int index = rent.returnMachine(textField.getText(),date());
					if(index == -1){
						JOptionPane.showMessageDialog(null, "The machine returned earlier");
						JOptionPane.showMessageDialog(null, "successful returned machine");
						textArea.setText(null);
						appendText();
					}else if(index == -2){
					
						JOptionPane.showMessageDialog(null, "successful returned machine");
						textArea.setText(null);
						appendText();
					}else if(index > 0){
						JOptionPane.showMessageDialog(null, "Time period exceeded " + index +" days");
						String sum = "The customer owe  " + machine.getItemPrice(rent.findTrade(textField.getText()).getMachineBarcode())*index ;
						double owe = machine.getItemPrice(rent.findTrade(textField.getText()).getMachineBarcode())*index;
						String message = "Type in the filed sum which customer give you and click "+ "OK" +" to make payment";
						String enteredSum =  JOptionPane.showInputDialog(null, message, sum, 0);
						System.out.println(enteredSum);
						if(enteredSum != null){
							double enteredSumInt = Double.parseDouble(enteredSum);
							if(enteredSumInt > owe ){
								EmployeeCtrl empl = new EmployeeCtrl();
								String emplID = rent.getTradeEmplID(textField.getText());
								double change = enteredSumInt - owe;
								change = Math.round(change * 100.0) / 100.0;
								JOptionPane.showMessageDialog(null, "The change is: " + Double.toString(change));
								rent.removeTrade(textField.getText());
								JOptionPane.showMessageDialog(null, "successful payment and returned machine!");
								// add owe to the Employee's productSold field
								
								
								
								  empl.changeProductsSold(empl.findEmployee( emplID), owe);
								textArea.setText(null);
								appendText();
							}else{
								JOptionPane.showMessageDialog(null, "The entered sum is not enough!");
							}
							
						
						
						}else{
							JOptionPane.showMessageDialog(null, "Incorrect entered money amount!");
						}
					}
										
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Incorrect data or rent ID");
					
				}
			}
		});
		btnReturn.setBounds(443, 351, 160, 23);
		contentPane.add(btnReturn);
		appendText();
	}
	public void appendText(){
	
		if (rent.getRents().isEmpty() == false) {
			
			for (Rent rents : rent.getRents()) {
				
				
				textArea.append(rents.toString()+ "\n");
				
			}
		
         }else{
        	 textArea.setText("There is no rents at this moment ");
         }
		}

	public static String getDateOfReturn() {
		return dateOfReturn;
	}

	public static void setDateOfReturn(String dateOfReturn) {
		ReturnMachine.dateOfReturn = dateOfReturn;
	}
	private String date(){
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		// if it is January returns 0
		if(month == 0){
			month = 1;
		}
		int year = calendar.get(Calendar.YEAR);
		return  day + "/" + month + "/" + year ;
	}

}
