package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ControlLayer.CustomerCtrl;

public class CustomerInformation extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInformation frame = new CustomerInformation();
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
	public CustomerInformation() {
		
		/**setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
	}
	public void customerInformation(){
		ExceededRentings exceeded = new ExceededRentings();
		contentPane = (JPanel) GUIRent.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("Customer information");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(278, 11, 155, 24);
		contentPane.add(lblAllMachines);
		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				exceeded.rentExceeded();
			}
		});
		button.setBounds(84, 339, 191, 23);
		contentPane.add(button);

		// text area with scroll
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(35, 46, 643, 212);
		contentPane.add(sp);
		try {
			String customerID = exceeded.getCustomerID();
			CustomerCtrl customer = new CustomerCtrl();
			textArea.append(customer.findCustomer(customerID).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane j = new JOptionPane();
			j.showMessageDialog(null, "Incorrect data");
		}
	}

}
