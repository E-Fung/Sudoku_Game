package Version_2;



public class Main {
	public static void main(String[] arg) {
		Sudoku game = new Sudoku();
		int [][] p = {{9,4,8,6,0,0,3,0,1},
			    	  {0,0,5,8,0,0,4,6,9},
			    	  {6,0,0,5,4,0,2,0,0},
			    	  {3,0,7,4,1,5,6,9,0},
			    	  {0,0,0,0,6,3,8,0,0},
			    	  {0,1,0,9,2,0,0,0,4},
			    	  {0,0,0,3,9,0,0,0,8},
			    	  {0,2,0,0,5,7,0,0,0},
			    	  {1,0,0,2,0,4,0,0,0}
					};
		game.changePuzzle(p);
		game.solve();
		//new GUI();
		new Test(game);
	}
}
