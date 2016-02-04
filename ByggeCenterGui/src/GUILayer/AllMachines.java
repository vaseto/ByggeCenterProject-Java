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

import ControlLayer.MachineCtrl;
import ModelLayer.Machine;

public class AllMachines extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllMachines frame = new AllMachines();
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
	public AllMachines() {
		/**
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100,
		 * 727, 424); contentPane = new JPanel(); contentPane.setBorder(new
		 * EmptyBorder(5, 5, 5, 5)); setContentPane(contentPane);
		 * contentPane.setLayout(null);
		 * 
		 */

	}

	public void allMachines(){
		//label all machines
	
		if(GUIRent.getIndex() == 1){
		
			contentPane = (JPanel) GUIRent.frame.getContentPane();
		}else{
			
			contentPane = (JPanel) MachineMenu.frame.getContentPane();
		}
				contentPane.removeAll();
				contentPane.repaint();
				JLabel lblAllMachines = new JLabel("ALL MACHINES");
				lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblAllMachines.setBounds(313, 30, 100, 24);
				contentPane.add(lblAllMachines);
				
				//back button
				JButton button = new JButton("Back");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					
						if(GUIRent.getIndex() == 1){
							GUIRent.mainMenu();
							GUIRent.setIndex(0);
						}else{
							
							contentPane.removeAll();
							contentPane.repaint();
							MachineMenu.initialize();
						}
					}
				});
				button.setBounds(288, 334, 150, 23);
				contentPane.add(button);
				
				//text area with scroll
				JTextArea textArea = new JTextArea();
				JScrollPane sp = new JScrollPane(textArea);
				sp.setBounds(35, 76, 643, 232);
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
