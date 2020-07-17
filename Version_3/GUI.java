package Version_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

	Sudoku gameSudoku = new Sudoku();
	JButton button1 = new JButton("Check: Toggle Off");
	JButton button2 = new JButton("Give Up");
	JButton button3 = new JButton("New Game");
	JPanel panels[] = new JPanel[9];
	JPanel cells[][] = new JPanel[9][9];
	JLabel labels[][] = new JLabel[9][9];
	JTextField texts[][] = new JTextField[9][9];
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	Container mainContainer = this.getContentPane();
	int games[][][];	
	int gameNumber=0;
	int totalGames;
	
	

	public GUI(String title, int [][][] g) {
		// sets up main container
		super(title);
		this.setSize(500, 550);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		games=g;


		totalGames=games.length;

		// initializers and setup

		mainContainer.setLayout(new BorderLayout(5, 5));
		mainContainer.setBackground(Color.white);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.white));

		// panel with buttons added to main container
		topPanel.setBorder(new LineBorder(Color.black, 3));
		topPanel.setBackground(Color.black);
		topPanel.setLayout(new GridLayout(1, 2, 50, 5));
		button1.addActionListener(new check());
		button2.addActionListener(new solve());
		button3.addActionListener(new newGame());
		topPanel.add(button1);
		topPanel.add(button2);
		mainContainer.add(topPanel, BorderLayout.NORTH);

		// adds group to main panel which is added to main container
		bottomPanel.setBorder(new LineBorder(Color.black, 3));
		bottomPanel.setBackground(Color.black);
		bottomPanel.setLayout(new GridLayout(3, 3, 5, 5));
		for (int i = 0; i < 9; i++) {
			panels[i] = new JPanel();
			panels[i].setLayout(new GridLayout(3, 3, 2, 2));
			panels[i].setBackground(Color.black);
			bottomPanel.add(panels[i]);
		}

		// adds 9 cells to each group
		int row;
		int col;
		for (int i = 0; i < 9; i++) {
			row = i / 3;
			for (int j = 0; j < 9; j++) {
				col = j / 3;
				cells[i][j] = new JPanel();
				cells[i][j].setLayout(new GridLayout(1, 1, 5, 5));
				panels[row * 3 + col].add(cells[i][j]);
			}
		}
		// ---------------------------------------------------------------------
		setupGame();
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//
//				if (game.getOrig(i, j) != 0) {
//					String temp = String.valueOf(game.getOrig(i, j));
//					labels[i][j] = new JLabel(temp);
//					labels[i][j].setHorizontalAlignment(JLabel.CENTER);
//					labels[i][j].setFont(new Font("Courier", Font.BOLD, 20));
//					labels[i][j].setOpaque(true);
//					labels[i][j].setBackground(Color.lightGray);
//					cells[i][j].add(labels[i][j]);
//				} else {
//					texts[i][j] = new JTextField("");
//					texts[i][j].setHorizontalAlignment(JTextField.CENTER);// centers text
//					texts[i][j].setFont(new Font("Courier", Font.BOLD, 20));
//					// tFields[i][j].setForeground(Color.blue);
//					cells[i][j].add(texts[i][j]);
//				}
//			}
//		}
		mainContainer.add(bottomPanel);
	}
	//----------------------------------------------------------------------------
	boolean finished = true;
	class check implements ActionListener {// toggle button
		boolean on = true;

		check() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (on) {
				on = false;
				button1.setText("Check: Toggle On");
				button2.setEnabled(false);
				// turns wrong text red
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (gameSudoku.getOrig(i, j) == 0) {// only check non original numbers
							String temp = texts[i][j].getText();
							String temp2 = String.valueOf(gameSudoku.getPuzzle(i, j));
							if (!temp.equals(temp2) & !temp.isEmpty()) {
								texts[i][j].setForeground(Color.red);
								finished = false;
							}
							if (temp.isEmpty()) {
								finished = false;
							}
						}
					}
				}
				if (finished) {
					String name = JOptionPane.showInputDialog(mainContainer,"Yayy!!!, you're done!!!" ,null);
					solve newSolve = new solve();
					newSolve.actionPerformed(e);
				}
			} else {
				button2.setEnabled(true);
				on = true;
				finished = true;
				button1.setText("Check: Toggle Off");
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (gameSudoku.getOrig(i, j) == 0) {// only check non original numbers
							texts[i][j].setForeground(Color.black);
						}
					}
				}
			}
		}
	}

	class solve implements ActionListener {
		solve() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			button1.setEnabled(false);
			// TODO Auto-generated method stub
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (gameSudoku.getOrig(i, j) == 0) {// only check non original numbers
						String temp = texts[i][j].getText();
						String temp2 = String.valueOf(gameSudoku.getPuzzle(i, j));
						texts[i][j].setEnabled(false);
						if (!temp.equals(temp2) | temp.isEmpty()) {
							texts[i][j].setText(temp2);
							texts[i][j].setForeground(Color.blue);
							texts[i][j].setBackground(Color.DARK_GRAY);
						} else {
							texts[i][j].setDisabledTextColor(Color.black);
						}
					}
				}
			}
			topPanel.remove(button2);
			topPanel.add(button3);
			repaint();
			revalidate();
		}
	}

	class newGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					cells[i][j].removeAll();
				}
			}
			button1.setEnabled(true);
			topPanel.remove(button3);
			topPanel.add(button2);
			button2.setEnabled(true);
			setupGame();
			repaint();
			revalidate();
		}

	}
	
	private void setupGame() {
		if(gameNumber==totalGames) {
			String name = JOptionPane.showInputDialog(mainContainer,"Out of new games ):",null);
			System.exit(0);
		}
		gameSudoku.changePuzzle(games[gameNumber]);
		gameNumber++;
		gameSudoku.solve();
		gameSudoku.print();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (gameSudoku.getOrig(i, j) != 0) {
					String temp = String.valueOf(gameSudoku.getOrig(i, j));
					labels[i][j] = new JLabel(temp);
					labels[i][j].setHorizontalAlignment(JLabel.CENTER);
					labels[i][j].setFont(new Font("Courier", Font.BOLD, 20));
					labels[i][j].setOpaque(true);
					labels[i][j].setBackground(Color.lightGray);
					cells[i][j].add(labels[i][j]);
				} else {
					texts[i][j] = new JTextField("");
					texts[i][j].setHorizontalAlignment(JTextField.CENTER);// centers text
					texts[i][j].setFont(new Font("Courier", Font.BOLD, 20));
					// tFields[i][j].setForeground(Color.blue);
					cells[i][j].add(texts[i][j]);
				}
			}
		}
	}

}
