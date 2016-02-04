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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;

public class FindCopyOfMachine extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindCopyOfMachine frame = new FindCopyOfMachine();
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
	public FindCopyOfMachine() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
		
	}
	protected void findCopy(){
		contentPane = (JPanel) MachineMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("Find information about particular copy of machine");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(193, 32, 330, 24);
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
		sp.setBounds(41, 169, 643, 108);
		contentPane.add(sp);
		
		JLabel lblNewLabel_1 = new JLabel("Input the serail number of the copy you want to find");
		lblNewLabel_1.setBounds(41, 123, 317, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNext = new JButton("Find machine");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					MachineCtrl machine = new MachineCtrl();
					String serialNumber = textField_1.getText();
					if (machine.getCopy(serialNumber) == null) {
						JOptionPane.showMessageDialog(null, "There is no copy of machine with this serial number!");
					} else {
						textArea.setText("");
						textArea.append(machine.getMachinebyCopySerialNumber(serialNumber).toString());
						textArea.append(machine.getCopy(serialNumber).toString());
						
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect Data!");
				}
			}
			
		});
		
		btnNext.setBounds(472, 334, 150, 22);
		contentPane.add(btnNext);
		
		textField_1 = new JTextField();
		textField_1.setBounds(394, 125, 113, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	}
	

}
