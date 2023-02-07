package courseStructures;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener{
		
private JFrame frame;
private JPanel panel;
private JButton searchButton;
private JTextField searchBar;
private JLabel searching;

private String userInput; // user's input into search bar returned as string
	
	public GUI() {
		
		frame = new JFrame();
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		
		searching = new JLabel("");
		
		searchBar = new JTextField();
		searchBar.addActionListener(this);
		panel.
		
		panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(searchButton);
		panel.add(searchBar, 1);
		panel.add(searching);
		
	
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("YULookUp");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public actionPerformed(ActionEvent e) {
		userInput = searchBar.getText();
		searching.setText("searching...");
		System.out.println(userInput);
	}	
	

}