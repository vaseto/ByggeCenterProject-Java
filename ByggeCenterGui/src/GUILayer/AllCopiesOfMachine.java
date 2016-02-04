package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;

public class AllCopiesOfMachine extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllCopiesOfMachine frame = new AllCopiesOfMachine();
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
	public AllCopiesOfMachine() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
	
	}
	public void allCopies(){
		
		 
		if(MachineMenu.getIndex() == 1){
			contentPane = (JPanel) MachineMenu.frame.getContentPane();
		}else{
			contentPane = (JPanel) GUIRent.frame.getContentPane();
			
		}
		contentPane.removeAll();
		contentPane.repaint();
		JLabel lblAllMachines = new JLabel("The copies of all the machines");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(268, 30, 183, 24);
		contentPane.add(lblAllMachines);
		
		//button back go back to the rent menu
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(MachineMenu.getIndex() == 1){
					
					MachineMenu.setIndex(0);
					contentPane.removeAll();
					contentPane.repaint();
					MachineMenu.initialize();
				}else{
					
					GUIRent.mainMenu();
					
				}
			}
		});
		contentPane.setLayout(null);
		button.setBounds(288, 334, 150, 23);
		contentPane.add(button);
			
		//text area with scroll
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(35, 76, 643, 232);
		contentPane.add(sp);
		
		// adding copies to the text area
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
