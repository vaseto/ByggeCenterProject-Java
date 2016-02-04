package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import ModelLayer.Copy;

public class FindOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindOrder frame = new FindOrder();
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
	public FindOrder() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
	}
	protected void findOrder(){
		contentPane = (JPanel) OrderMenu.getFrame().getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("Find information about particular order");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(235, 32, 275, 24);
		
		contentPane.add(lblAllMachines);
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(41, 169, 643, 154);
		contentPane.add(sp);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(289, 125, 113, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				OrderMenu.mainMenu();
			}
		});
		button.setBounds(100, 334, 150, 23);
		contentPane.add(button);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Input the ID of the order you want to find");
		lblNewLabel_1.setBounds(41, 123, 317, 24);
		contentPane.add(lblNewLabel_1);
		
		
		
		JButton btnNext = new JButton("Find order");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					OrderCtrl orders = new OrderCtrl();
					String barcode = textField_1.getText();
					if (orders.findTrade(barcode) == null) {
						JOptionPane.showMessageDialog(null, "There is no order with this ID!");
					} else {
						textArea.setText("");
						textArea.append(orders.findTrade(barcode).toString());
						
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect Data!");
				}
			}
			
		});
		
		btnNext.setBounds(467, 334, 150, 23);
		contentPane.add(btnNext);
	}
	


}
