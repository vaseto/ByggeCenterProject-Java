package GUILayer;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ControlLayer.RentCtrl;
import ModelLayer.Rent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllRentings extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllRentings frame = new AllRentings();
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
	public AllRentings() {
		/**
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/
		
		
	}
	public void allRents(){
		RentCtrl rent = new RentCtrl();
		
		GUIRent.frame.getContentPane().removeAll();
		GUIRent.frame.getContentPane().repaint();
		//text area with scroll
		textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(42, 78, 626, 244);
		
		GUIRent.frame.getContentPane().add(sp);
		//label all rents
		JLabel lblNewLabel = new JLabel("ALL RENTS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(320, 28, 101, 24);
		GUIRent.frame.getContentPane().add(lblNewLabel);
		
		// button back
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GUIRent.frame.getContentPane().removeAll();
				GUIRent.frame.getContentPane().repaint();
				GUIRent.mainMenu();
				
			}
		});
		btnNewButton.setBounds(290, 344, 146, 23);
		GUIRent.frame.getContentPane().add(btnNewButton);
		
		if (rent.getRents().isEmpty() == false) {
			
			for (Rent rents : rent.getRents()) {
				
				
				textArea.append(rents.toString() + "\n");
				
			}
		
         }else{
        	 textArea.setText("There is no rents at this moment ");
         }
	}
}
