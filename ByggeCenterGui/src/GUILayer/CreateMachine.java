package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;

public class CreateMachine extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Random random;

	private MachineCtrl machineCtrl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMachine frame = new CreateMachine();
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
	public CreateMachine() {
		 machineCtrl = new MachineCtrl();
		
		random = new Random();
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 */
	}
	protected void createMachine(){
		contentPane = (JPanel) MachineMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		
		JLabel lblAllMachines = new JLabel("Creat machine - Menu");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(283, 32, 150, 24);
		contentPane.add(lblAllMachines);
		
		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.repaint();
				MachineMenu.initialize();
				
			}
		});
		button.setBounds(100, 320, 150, 23);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("Machine name");
		lblNewLabel.setBounds(239, 100, 150, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPricePerDay = new JLabel("Price per day");
		lblPricePerDay.setBounds(239, 145, 150, 14);
		contentPane.add(lblPricePerDay);
		
		JLabel lblQuantity = new JLabel("Number copies of machine");
		lblQuantity.setBounds(239, 194, 160, 14);
		contentPane.add(lblQuantity);
		
		JLabel lblCategoryOfMachine = new JLabel("Category of machine");
		lblCategoryOfMachine.setBounds(239, 240, 150, 14);
		contentPane.add(lblCategoryOfMachine);
		
		textField = new JTextField();
		textField.setBounds(399, 97, 103, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(399, 142, 103, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(399, 191, 103, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(399, 237, 103, 20);
		contentPane.add(textField_3);

		JButton btnCreate = new JButton("Create ");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField.getText();
					double price = Double.parseDouble(textField_1.getText());
					int number = Integer.parseInt(textField_2.getText());// number
																			// of
																			// machine
					String category = textField_3.getText();
					String barcode =generateMachineBarcode();
					machineCtrl.createItem(name, barcode, price, number, category);
					for(int i = 0; i< number ; i++){
						machineCtrl.createCopy(barcode, generateCopySerialNumber(), true);
					}
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					JOptionPane.showMessageDialog(null, "Successfully created machine!");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Incorrect data!");
				}
			}
		});
		btnCreate.setBounds(478, 320, 150, 23);
		contentPane.add(btnCreate);
	}

	// automatically generating barcode of machine
	private String generateMachineBarcode() {
		try{
		boolean check = false;
		String number = "";
		while (check == false) {
			number = Integer.toString(random.nextInt(1000));
			if (machineCtrl.getItem(number) == null) {
				return number;
			}
		}
		
		}catch(Exception ex){
		ex.printStackTrace();	
		}
		return null;
	}
	public String generateCopySerialNumber() {
		try{
		boolean check = false;
		String number = "";
		while (check == false) {
			number = Integer.toString(random.nextInt(1000));
			if (machineCtrl.getCopy(number) == null) {
				return number;
			}
		}
		
		}catch(Exception ex){
		ex.printStackTrace();	
		}
		return null;
	}
}
