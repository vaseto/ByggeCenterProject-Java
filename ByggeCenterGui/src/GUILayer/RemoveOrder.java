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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.OrderCtrl;
import ControlLayer.RentCtrl;
import ModelLayer.Order;
import ModelLayer.Rent;

public class RemoveOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private OrderCtrl orders;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveOrder frame = new RemoveOrder();
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
	public RemoveOrder() {
		 orders = new OrderCtrl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	}
	protected void removeOrder(){
		contentPane = (JPanel) OrderMenu.getFrame().getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderMenu.mainMenu();
			}
		});
		button.setBounds(106, 351, 160, 23);
		contentPane.add(button);
		
		// label return machine
		JLabel lblAllMachines = new JLabel("Remove order");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 30, 100, 24);
		contentPane.add(lblAllMachines);
		
		
		JLabel lblNewLabel = new JLabel("Input the ID of the order");
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
		appendText();
		
		// return rent and going back to the start menu
		JButton btnReturn = new JButton("Remove");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String barcode = textField.getText();
					if (orders.findTrade(barcode) == null) {
						JOptionPane.showMessageDialog(null, "There is no order with this ID!");
					} else {
						orders.removeTrade(barcode);
						JOptionPane.showMessageDialog(null, "Successfully removed order!");
						textArea.setText("");
						appendText();

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect Data!");
				}
			}
		});
		btnReturn.setBounds(443, 351, 160, 23);
		contentPane.add(btnReturn);
	}
	
	/**
	 * Append all rents in a text area
	 * 
	 */
	public void appendText() {
		
		if (orders.getOrders().isEmpty() == false) {
			for (Order order : orders.getOrders()) {
				textArea.append(order.toString() + "\n");
			}
		} else {
			textArea.setText("There is no rents at this moment ");
		}

	}

	


}
