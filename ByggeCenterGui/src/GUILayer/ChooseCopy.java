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
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;
import ModelLayer.Copy;
import javax.swing.JTextField;

public class ChooseCopy extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String copyID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseCopy frame = new ChooseCopy();
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
	public ChooseCopy() {
		/**
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100,
		 * 727, 424); contentPane = new JPanel(); contentPane.setBorder(new
		 * EmptyBorder(5, 5, 5, 5)); setContentPane(contentPane);
		 * contentPane.setLayout(null);
		 */

	}

	protected void chooseCopy() {
		contentPane = (JPanel) GUIRent.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		ChooseMachine machine = new ChooseMachine();

		JLabel lblAllMachines = new JLabel("Rent machine");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 30, 100, 24);
		contentPane.add(lblAllMachines);

		// back button
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machine.chooseMachine();
			}
		});
		button.setBounds(100, 334, 150, 23);
		contentPane.add(button);

		// next button
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MachineCtrl machineCopy = new MachineCtrl();
					copyID = textField.getText();

					if (machineCopy.getCopy(machine.getBarcode(), copyID).getStatus() == true) {
						
						FinalPartRenting finalPart = new FinalPartRenting();
						finalPart.finalPartRenting();
					} else {
						JOptionPane.showMessageDialog(null, "The particular copy of  machine is not available");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect data!");
				}
			}
		});
		btnNext.setBounds(467, 334, 150, 23);
		contentPane.add(btnNext);

		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(35, 83, 643, 189);
		contentPane.add(sp);

		JLabel lblNewLabel_1 = new JLabel(
				"                                                                              True = Available, False = not Available");
		sp.setColumnHeaderView(lblNewLabel_1);

		JLabel lblInputTheSerial = new JLabel("Input the serial number of the copy you want to be rented");
		lblInputTheSerial.setBounds(49, 301, 317, 24);
		contentPane.add(lblInputTheSerial);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(390, 303, 113, 20);
		contentPane.add(textField);
		MachineCtrl copy = new MachineCtrl();

		ArrayList<Copy> copies = copy.getCopiesOfMachine(machine.getBarcode());
		for (int i = 0; i < copies.size(); i++) {
			textArea.append(copies.get(i).toString());
		}
	}

	protected static String getCopyID() {
		return copyID;
	}

	protected static void setCopyID(String copyID) {
		ChooseCopy.copyID = copyID;
	}

}
