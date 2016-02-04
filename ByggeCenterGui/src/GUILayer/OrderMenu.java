package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderMenu {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMenu window = new OrderMenu();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 727, 424);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		mainMenu();
	}
	protected static void mainMenu(){
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		JLabel lblNewLabel = new JLabel("ORDER MENU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(315, 21, 89, 37);
		getFrame().getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Registrate order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrateOrder order = new RegistrateOrder();
				order.registrateOrder();
				
			}
		});
		btnNewButton.setBounds(279, 94, 161, 23);
		getFrame().getContentPane().add(btnNewButton);
		
		JButton btnFindOrder = new JButton("Find order");
		btnFindOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindOrder find = new FindOrder();
				 find.findOrder();
			}
		});
		btnFindOrder.setBounds(279, 140, 161, 23);
		getFrame().getContentPane().add(btnFindOrder);
		
		JButton btnAllOrders = new JButton("All orders");
		btnAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllOrders orders = new AllOrders();
				
				orders.allOrders();
			}
		});
		btnAllOrders.setBounds(279, 189, 161, 23);
		getFrame().getContentPane().add(btnAllOrders);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrame().dispose();
				EmployeeUI.getInstance().setVisible(true);
			}
		});
		btnExit.setBounds(279, 317, 161, 23);
		getFrame().getContentPane().add(btnExit);
		
		JButton btnRemoveOrder = new JButton("Remove order");
		btnRemoveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveOrder remove = new RemoveOrder();
				remove.removeOrder();
			}
		});
		btnRemoveOrder.setBounds(279, 240, 161, 23);
		getFrame().getContentPane().add(btnRemoveOrder);
	}

	protected static JFrame getFrame() {
		return frame;
	}

	protected static void setFrame(JFrame frame) {
		OrderMenu.frame = frame;
	}
}
