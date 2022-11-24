package lambdaExercise;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LambdaExerciseGUI extends JFrame {

	private JPanel contentPane;
	
	private int count; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LambdaExerciseGUI frame = new LambdaExerciseGUI();
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
	public LambdaExerciseGUI() {
		
		count = 0; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 299, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIncrement = new JButton("Increment");
		btnIncrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
			}
		});
		btnIncrement.setBounds(24, 35, 103, 23);
		contentPane.add(btnIncrement);
		
		JButton btnDisplay = new JButton("Display and Reset");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDisplayActionPerformed(e); 
			}
		});
		btnDisplay.setBounds(137, 35, 138, 23);
		contentPane.add(btnDisplay);
	}
	
	
    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {                                            
        char s = count == 1? ' ' : 's'; // time or times 
        JOptionPane.showMessageDialog(this, 
                "Increment has been clicked " + count + " time" + s, 
                "Results", 
                JOptionPane.PLAIN_MESSAGE); 

        count = 0; 
    }
	
}
