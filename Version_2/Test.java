package Version_2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;




public class Test {
	
	
	JFrame frame = new JFrame("GridLayout demo");
	JPanel mainPanel = new JPanel();
	JPanel panels[] = new JPanel[9];
	JTextField tFields[][]=new JTextField[9][9];
	JLabel labels[][]=new JLabel[9][9];
	JPanel finalPanel = new JPanel();
	
	Border border = BorderFactory.createLineBorder(Color.black);

	public Test(Sudoku game) {
		mainPanel.setLayout(new GridLayout(3, 3, 5, 5));
		mainPanel.setBackground(Color.black);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		finalPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		finalPanel.setLayout(new GridLayout(1,1,5,5));
		
		for (int i = 0; i < 9; i++) {//creates the 3 by 3 panels
			panels[i] = new JPanel();
			panels[i].setLayout(new GridLayout(3, 3, 2, 2));
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(game.getOrig(i, j)!=0) {
				String temp=String.valueOf(game.getOrig(i, j));
				labels[i][j]=new JLabel(temp);
				labels[i][j].setHorizontalAlignment(JLabel.CENTER);
				labels[i][j].setFont(new Font("Courier", Font.BOLD,20));
				labels[i][j].setBorder(border);
				panels[i].add(labels[i][j]);
				}
				else {
					tFields[i][j] = new JTextField();
					tFields[i][j].setHorizontalAlignment(JTextField.CENTER);//centers text
					tFields[i][j].setFont(new Font("Courier", Font.BOLD,20));
					//tFields[i][j].setForeground(Color.blue);
					panels[i].add(tFields[i][j]);
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			mainPanel.add(panels[i]);
		}
		finalPanel.add(mainPanel);
		frame.add(finalPanel);
		mainPanel.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
