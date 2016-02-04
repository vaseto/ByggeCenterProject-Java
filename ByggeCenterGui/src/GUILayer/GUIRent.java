package GUILayer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GUIRent {

	protected static  JFrame frame;
	private static int index = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIRent window = new GUIRent();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIRent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 727, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		mainMenu();
	}
	public static void mainMenu(){
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		JButton btnNewButton = new JButton("Rent Machine");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ChooseMachine rent = new ChooseMachine();
				rent.chooseMachine();
			}
		});
		btnNewButton.setBounds(233, 59, 244, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("RENT MENU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(318, 11, 75, 37);
		frame.getContentPane().add(lblNewLabel);
		
		// for seeing all rents
		JButton btnNewButton_1 = new JButton("All rentings");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AllRentings allRentings = new AllRentings();
				allRentings.allRents();
			}
		});
		btnNewButton_1.setBounds(233, 104, 244, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		// for seeing all machines
		JButton button = new JButton("All machines");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(1);
				
				AllMachines machines = new AllMachines();
				 machines.allMachines();
			}
		});
		button.setBounds(233, 149, 244, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("The copies of all the machines  ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AllCopiesOfMachine copies = new AllCopiesOfMachine();
				copies.allCopies();
			}
		});
		button_1.setBounds(233, 195, 244, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getFrame().dispose();
				EmployeeUI.getInstance().setVisible(true);
			}
		});
		button_2.setBounds(233, 333, 244, 23);
		frame.getContentPane().add(button_2);
		
		JButton btnReturnMachine = new JButton("Return machine ");
		btnReturnMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnMachine returnMachine = new ReturnMachine();
				returnMachine.returnMachine();
			}
		});
		btnReturnMachine.setBounds(233, 241, 244, 23);
		frame.getContentPane().add(btnReturnMachine);
		
		JButton btnRentsExceededTime = new JButton("Rents Exceeded Time for returning");
		btnRentsExceededTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExceededRentings exceeded = new ExceededRentings();
				
				exceeded.rentExceeded();
			}
		});
		btnRentsExceededTime.setBounds(233, 287, 244, 23);
		frame.getContentPane().add(btnRentsExceededTime);
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int newIndex) {
		index = newIndex;
	}

	protected static JFrame getFrame() {
		return frame;
	}

	private void setFrame(JFrame frame) {
		GUIRent.frame = frame;
	}
}
