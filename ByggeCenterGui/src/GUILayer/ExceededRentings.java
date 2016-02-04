package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ControlLayer.RentCtrl;
import javax.swing.JTextField;

public class ExceededRentings extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String customerID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExceededRentings frame = new ExceededRentings();
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
	public ExceededRentings() {
		/**
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100,
		 * 727, 424); contentPane = new JPanel(); contentPane.setBorder(new
		 * EmptyBorder(5, 5, 5, 5)); setContentPane(contentPane);
		 * contentPane.setLayout(null);
		 */
	}

	public void rentExceeded() {
		contentPane = (JPanel) GUIRent.getFrame().getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		// label all machines
		JLabel lblAllMachines = new JLabel("Exceeded Rents");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 11, 100, 24);
		contentPane.add(lblAllMachines);
		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIRent.mainMenu();
			}
		});
		button.setBounds(84, 339, 191, 23);
		contentPane.add(button);

		// text area with scroll
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(35, 46, 643, 212);
		contentPane.add(sp);

		JButton btnSendReturningRequest = new JButton("Send Returning Request ");
		btnSendReturningRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSendReturningRequest.setBounds(421, 339, 191, 23);
		contentPane.add(btnSendReturningRequest);

		JButton btnCustomerInformation = new JButton("See Customer information");
		btnCustomerInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customerID = textField.getText();
					CustomerInformation cust = new CustomerInformation();
					cust.customerInformation();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCustomerInformation.setBounds(393, 287, 191, 23);
		contentPane.add(btnCustomerInformation);

		textField = new JTextField();
		textField.setBounds(313, 288, 39, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Input customer ID");
		lblNewLabel.setBounds(189, 289, 114, 19);
		contentPane.add(lblNewLabel);

		// adding exceeded rents in the text area
		try {
			Stack exceededRents = new Stack();
			RentCtrl rentCtrl = new RentCtrl();
			exceededRents = rentCtrl.getExceededRents();
			if (exceededRents.isEmpty() == true) {

				textArea.append("There is no Machines at this moment ");
			}
			while (exceededRents.isEmpty() == false) {

				textArea.append(exceededRents.pop().toString() + "\n");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getCustomerID() {
		return customerID;
	}

	public static void setCustomerID(String customerID) {
		ExceededRentings.customerID = customerID;
	}

}
