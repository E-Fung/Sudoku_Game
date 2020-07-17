package Version_1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	
	int count=0;
	private JLabel label;
	private JPanel panel;
	
	public GUI() {
		JFrame frame = new JFrame(); // the window

		JButton button = new JButton("Click me");
		button.addActionListener(this);// goes to present class and uses action listener method

		label = new JLabel("Number of clicks: 0");

		panel = new JPanel(); // the layout, what's in the JFrame
		
		panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
		panel.setLayout(new GridLayout(9,9)); // rows/columns
		panel.add(label);
		panel.add(button);
			
		
		
		//Standard shit----------------------------------------------------------------------------------------
		frame.add(panel, BorderLayout.CENTER); // add panel to frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what happens when frame is closed
		frame.setTitle("Sudoku");
		frame.pack(); // set window to match size
		frame.setVisible(true); // set window to be shown and in focus
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		count++;
		label.setText("Number of clicks: "+count);
	}
}
