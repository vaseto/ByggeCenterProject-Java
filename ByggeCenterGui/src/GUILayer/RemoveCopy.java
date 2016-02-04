package GUILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ModelLayer.Machine;

public class RemoveCopy extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private MachineCtrl machineCtrl;
	private CreateMachine createMachine;
	private JTextArea textArea ;
	private JScrollPane sp ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveCopy frame = new RemoveCopy();
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
	public RemoveCopy() {
		textArea = new JTextArea();
		sp= new JScrollPane(textArea);
		createMachine = new CreateMachine();
		 machineCtrl = new MachineCtrl();
		 /**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
	}
	protected void removeCopy(){
		contentPane = (JPanel) MachineMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		
		JLabel lblAllMachines = new JLabel("Remove machine - Menu");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(251, 33, 201, 24);
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
		button.setBounds(100, 320, 150, 23);
		contentPane.add(button);
		
		JLabel lblPricePerDay = new JLabel("Machine's copy serial number");
		lblPricePerDay.setBounds(212, 284, 173, 14);
		contentPane.add(lblPricePerDay);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 281, 103, 20);
		contentPane.add(textField_1);
		showCopies();
		
		JButton btnCreate = new JButton("Remove ");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						String copySerialNumber = textField_1.getText();
						machineCtrl.removeCopy((copySerialNumber));
						
					JOptionPane.showMessageDialog(null, "Successfully removed copy of machine!");
						textArea.setText("");
						textField_1.setText("");
						showCopies();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Incorrect data!");
				}
			}
		});
		btnCreate.setBounds(478, 320, 150, 23);
		contentPane.add(btnCreate);
	}
	private void showCopies(){
		
		
		sp.setBounds(35, 76, 643, 176);
		contentPane.add(sp);
		
		//adding machines in the text area
		
		MachineCtrl machineCopies = new MachineCtrl();
		
		if(machineCopies.getCopiesOfMachines().isEmpty()== false){
			Stack copies = machineCopies.getCopiesOfMachines();//get the whole stack of copies
			
    		while(copies.isEmpty() == false){
    			textArea.append(copies.pop().toString());//get the machine's copy of given machine which is on the top of the stack and remove it.
    		}
    	}else{
    		textArea.append("There is no copies at this moment ");
    	}
	
	}

}
