package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;
import ModelLayer.Copy;

public class FindMachine extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindMachine frame = new FindMachine();
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
	public FindMachine() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
	
	}
	protected void findMachine(){
		contentPane = (JPanel) MachineMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("Find information about particular machine");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(235, 32, 275, 24);
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
		button.setBounds(100, 334, 150, 23);
		contentPane.add(button);
		
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(41, 169, 643, 154);
		contentPane.add(sp);
		
		JLabel lblNewLabel_1 = new JLabel("Input the barcode of the machine you want to find");
		lblNewLabel_1.setBounds(41, 123, 317, 24);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(394, 125, 113, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNext = new JButton("Find machine");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					MachineCtrl machine = new MachineCtrl();
					String barcode = textField_1.getText();
					if (machine.getItem(barcode) == null) {
						JOptionPane.showMessageDialog(null, "There is no machine whith this barcode!");
					} else {
						textArea.setText("");
						textArea.append(machine.getItem(barcode).toString());
						ArrayList<Copy> copies = machine.getCopiesOfMachine(barcode);
						for(Copy copy : copies){
							textArea.append(copy.toString() + "\n");
						}
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
