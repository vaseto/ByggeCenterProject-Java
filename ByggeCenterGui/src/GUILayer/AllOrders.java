package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ControlLayer.OrderCtrl;
import ModelLayer.Order;

public class AllOrders extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllOrders frame = new AllOrders();
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
	public AllOrders() {
		/**
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100,
		 * 721, 429); contentPane = new JPanel(); contentPane.setBorder(new
		 * EmptyBorder(5, 5, 5, 5)); setContentPane(contentPane);
		 * contentPane.setLayout(null);
		 */

	}

	protected void allOrders() {
		contentPane = (JPanel) OrderMenu.getFrame().getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		OrderCtrl orders = new OrderCtrl();
		// text area with scroll
		textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(42, 78, 626, 244);

		contentPane.add(sp);
		// label all rents
		JLabel lblNewLabel = new JLabel("ALL ORDERS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(320, 28, 101, 24);
		contentPane.add(lblNewLabel);

		// button back
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OrderMenu.mainMenu();

			}
		});
		btnNewButton.setBounds(290, 344, 146, 23);
		contentPane.add(btnNewButton);

		if (orders.getOrders().isEmpty() == false) {

			for (Order order : orders.getOrders()) {

				textArea.append(order.toString() + "\n");

			}

		} else {
			textArea.setText("There is no rents at this moment ");
		}

	}

}
