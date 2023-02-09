package courseStructures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchbarGUI implements ActionListener {
    private JPanel panel;
    private JTextField searchBar;
    private JButton searchButton;
    private JFrame frame;

    public searchbarGUI() {
        frame = new JFrame();

        searchButton = new JButton("search");
        searchButton.addActionListener(this);

        searchBar = new JTextField();

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout());
        panel.add(searchBar);
        panel.add(searchButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("YULookUp");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new searchbarGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



}
