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

import ControlLayer.MachineCtrl;
import ModelLayer.Machine;


public class CreateCopy extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private MachineCtrl machineCtrl;
	private CreateMachine createMachine;
	private JTextArea textArea ;
	private  JScrollPane sp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCopy frame = new CreateCopy();
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
	public CreateCopy() {
		 textArea = new JTextArea();
		 sp = new JScrollPane(textArea);
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
	protected void createCopy(){
		contentPane = (JPanel) MachineMenu.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("Create copy of machine - Menu");
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
		
		JLabel lblPricePerDay = new JLabel("Machine's barcode");
		lblPricePerDay.setBounds(239, 284, 123, 14);
		contentPane.add(lblPricePerDay);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(387, 281, 103, 20);
		contentPane.add(textField_1);
		showMachines();
		
		JButton btnCreate = new JButton("Create ");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						String machineBarcode = textField_1.getText();
						machineCtrl.createCopy(machineBarcode, createMachine.generateCopySerialNumber(), true);
						int quantity = machineCtrl.getItem(machineBarcode).getQuantity();
						machineCtrl.setItemQuantity(machineBarcode, quantity+1);
						textArea.setText("");
						textField_1.setText("");
					JOptionPane.showMessageDialog(null, "Successfully created copy!");
						showMachines();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Incorrect data!");
				}
			}
		});
		btnCreate.setBounds(478, 320, 150, 23);
		contentPane.add(btnCreate);
	}
	private void showMachines(){
	
		
		sp.setBounds(35, 76, 643, 176);
		contentPane.add(sp);
		
		//adding machines in the text area
		
		MachineCtrl machines = new MachineCtrl();
		if(machines.getMachines().isEmpty()== false){
			for(Machine machine : machines.getMachines() ){
				textArea.append(machine.toString() + "\n");
			}
    	}else{
    		textArea.append("There is no Machines at this moment ");
    	}
	}

}
