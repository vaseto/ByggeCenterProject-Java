package GUILayer;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;
import ModelLayer.Machine;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ChooseMachine extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private static String barcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseMachine frame = new ChooseMachine();
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
	public ChooseMachine() {
		/**
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100,
		 * 727, 424); contentPane = new JPanel(); contentPane.setBorder(new
		 * EmptyBorder(5, 5, 5, 5)); setContentPane(contentPane);
		 * contentPane.setLayout(null);
		 */

	}

	public void chooseMachine() {
		// label makes rent
		contentPane = (JPanel) GUIRent.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("Rent machine");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 30, 100, 24);
		contentPane.add(lblAllMachines);

		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIRent.mainMenu();
			}
		});
		button.setBounds(100, 334, 150, 23);
		contentPane.add(button);

		JLabel lblNewLabel_1 = new JLabel("Input the barcode of the machine you want to be rented");
		lblNewLabel_1.setBounds(45, 299, 317, 24);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(387, 301, 113, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(54, 183, 113, 14);
		contentPane.add(lblNewLabel_2);

		// go to the next frame
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MachineCtrl machine = new MachineCtrl();
					barcode = textField_1.getText();
					if (machine.getItem(barcode) == null) {
						JOptionPane.showMessageDialog(null, "There is no machine whith this barcode!");
					} else {

						
						ChooseCopy copy = new ChooseCopy();
						copy.chooseCopy();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect Data!");
				}
			}
		});

		btnNext.setBounds(467, 334, 150, 23);
		contentPane.add(btnNext);

		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(35, 67, 643, 205);
		contentPane.add(sp);

		// adding machines in the text area

		MachineCtrl machines = new MachineCtrl();
		if (machines.getMachines().isEmpty() == false) {
			for (Machine machine : machines.getMachines()) {
				textArea.append(machine.toString() + "\n");
			}
		} else {
			textArea.append("There is no Machines at this moment ");
		}
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
