package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MachineMenu {

	protected static JFrame frame;
	private static int index = 0;
	private static int indexTwo = 2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MachineMenu window = new MachineMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MachineMenu() {
		frame = new JFrame();
		frame.setBounds(100, 100, 727, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected static void initialize() {
		
		
		JLabel lblNewLabel = new JLabel("MACHINE MENU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(307, 21, 122, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Create machine");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateMachine create = new CreateMachine();
				create.createMachine();
			}
		});
		btnNewButton.setBounds(112, 88, 200, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateM = new JButton("Create copy of machine");
		btnCreateM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCopy createCopy = new CreateCopy();
				createCopy.createCopy();;
			}
		});
		btnCreateM.setBounds(112, 137, 200, 23);
		frame.getContentPane().add(btnCreateM);
		
		JButton btnFindMachineInformation = new JButton("Find machine information");
		btnFindMachineInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindMachine findMachine = new FindMachine();
				findMachine.findMachine();
			}
		});
		btnFindMachineInformation.setBounds(393, 88, 200, 23);
		frame.getContentPane().add(btnFindMachineInformation);
		
		JButton btnFindCopyOf = new JButton("Find copy of machine ");
		btnFindCopyOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindCopyOfMachine findCopy = new FindCopyOfMachine();
				findCopy.findCopy();
			}
		});
		btnFindCopyOf.setBounds(112, 188, 200, 23);
		frame.getContentPane().add(btnFindCopyOf);
		
		JButton btnAllMachines = new JButton("All machines");
		btnAllMachines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				AllMachines machines = new AllMachines();
				machines.allMachines();
			}
		});
		btnAllMachines.setBounds(112, 234, 200, 23);
		frame.getContentPane().add(btnAllMachines);
		
		JButton btnAllCopies = new JButton("All copies of Machines");
		btnAllCopies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllCopiesOfMachine  copies = new AllCopiesOfMachine();
				setIndex(1);
				copies.allCopies();
			}
		});
		btnAllCopies.setBounds(393, 234, 200, 23);
		frame.getContentPane().add(btnAllCopies);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getIndexTwo() == 3){
					setIndexTwo(2);
					frame.dispose();
					EmployeeUI.getInstance().setVisible(true);
					
				}else{
					ManagerUI manager = new ManagerUI();
					frame.dispose();
					manager.setVisible(true);
				}
				
			}
		});
		btnNewButton_1.setBounds(260, 314, 200, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnRemoveMachine = new JButton("Remove machine");
		btnRemoveMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveMachine remove = new RemoveMachine();
				remove.removeMachine();
			}
		});
		btnRemoveMachine.setBounds(393, 137, 200, 23);
		frame.getContentPane().add(btnRemoveMachine);
		
		JButton btnRemoveMachinesCopy = new JButton("Remove machine's copy");
		btnRemoveMachinesCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveCopy remove = new RemoveCopy();
				remove.removeCopy();
			}
		});
		btnRemoveMachinesCopy.setBounds(393, 188, 200, 23);
		frame.getContentPane().add(btnRemoveMachinesCopy);
	}
	

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int newIndex) {
		index = newIndex;
	}

	public static int getIndexTwo() {
		return indexTwo;
	}

	public static void setIndexTwo(int newIndexTwo) {
		indexTwo = newIndexTwo;
	}

	
}
