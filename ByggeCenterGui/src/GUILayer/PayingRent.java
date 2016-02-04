package GUILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.MachineCtrl;
import ControlLayer.RentCtrl;

public class PayingRent extends JFrame {

	private JPanel contentPane;

	private JTextField textField;
	private RentCtrl rent;
	private Random random;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayingRent frame = new PayingRent();
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
	public PayingRent() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
	}
	protected void payingRent(){
		contentPane = (JPanel) GUIRent.frame.getContentPane();
		contentPane.removeAll();
		contentPane.repaint();
		random = new Random();
		rent = new RentCtrl();
		FinalPartRenting finalPart = new FinalPartRenting();
		RentCtrl rentCtrl = new RentCtrl();
		ChooseMachine machine = new ChooseMachine();
		MachineCtrl machineCtrl = new MachineCtrl();

		textField = new JTextField();
		textField.setBounds(347, 159, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblAllMachines = new JLabel("Paying rent");
		lblAllMachines.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAllMachines.setBounds(313, 30, 100, 24);
		contentPane.add(lblAllMachines);

		JLabel lblNewLabel = new JLabel("The price for all days is:");
		lblNewLabel.setBounds(77, 115, 230, 24);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(347, 120, 86, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Type in the filed sum which customer give you");
		lblNewLabel_2.setBounds(77, 161, 260, 17);
		contentPane.add(lblNewLabel_2);

		// getting the cost of the rent searched by id
		double realCost = machineCtrl.getItemPrice(machine.getBarcode()) * finalPart.getPeriod();

		float discount = finalPart.getDiscount();
		if (discount > 0) {
			discount = (float) realCost * discount / 100;
			realCost = Math.round(realCost * 100.0) / 100.0;
			realCost = (float) (realCost - discount);
			realCost = Math.round(realCost * 100.0) / 100.0;
		}
		String cost = Double.toString(realCost);
		lblNewLabel_1.setText(cost);

		// button finish
		JButton btnNext = new JButton("Pay and finish");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					ChooseMachine machine = new ChooseMachine();
					ChooseCopy copy = new ChooseCopy();
					String copyID = copy.getCopyID();
					// customer sum is the sum which customer give to the
					// employee to pay the rent
					double customerSum = Double.parseDouble(textField.getText());
					double change = customerSum - Double.parseDouble(cost);
					// round up the sum
					customerSum = Math.round(customerSum * 100.0) / 100.0;
					// round up the sum
					change = Math.round(change * 100.0) / 100.0;

					if (change < 0) {
						JOptionPane.showMessageDialog(null, "the inserted sum is not enough to pay the rent ");

					} else {
						String rentID = generateRentID();

						rent.newRent(finalPart.getDiscount(), finalPart.getCustID(), finalPart.getEmpID(), rentID,
								finalPart.getPeriod(), finalPart.getBorrowDate(), copyID, machine.getBarcode());
						if (change > 0)
							;
						JOptionPane.showMessageDialog(null, "The change is: " + Double.toString(change));

						JOptionPane.showMessageDialog(null, "successful rent and payment!");

						contentPane.removeAll();
						contentPane.repaint();
						
						GUIRent.mainMenu();
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Incorrect data");
				}
			}
		});
		btnNext.setBounds(467, 334, 150, 23);
		contentPane.add(btnNext);

		/**
		 * button back going back to the previous menu
		 */
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				finalPart.finalPartRenting();
			}
		});
		button.setBounds(100, 334, 150, 23);
		contentPane.add(button);

		// printing the how much will cost renting particular machine

	}

	private String generateRentID() {
		try {
			boolean check = false;
			String number = "";
			while (check == false) {
				number = Integer.toString(random.nextInt(1000));
				if (rent.findTrade(number) == null) {// check whether there is
														// order with this id
					check = true;// just for sure
					return number;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

}
