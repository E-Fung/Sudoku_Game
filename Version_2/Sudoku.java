package Version_2;

import java.util.HashMap;

public class Sudoku {
	private int[][] puzzle = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	
	private int[][] original = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	
	public int getOrig(int row, int col) {
		return original[row][col];
	}

	public void changePuzzle(int[][] p) {
		puzzle=p;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				original[i][j]=p[i][j];
			}
		}
	}

	public Sudoku() {

	}

	public int getPuzzle(int row, int col) {
		return puzzle[row][col];
	}

	public void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(puzzle[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void printOrig() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(original[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public void solve() {
		if (solvePuzzle(0)) {
			System.out.println("Solved");
		} else {
			System.out.println("No Solution");
		}
	}

	private boolean solvePuzzle(int pos) {
		int row = pos / 9;
		int col = pos % 9;
		if (puzzle[row][col] != 0) {// if current cell isn't 0 go to next cell
			if (pos == 80) {
				return true;
			}
			return solvePuzzle(pos + 1);
		} else {
			while (puzzle[row][col] != 9) {
				puzzle[row][col]++;
				if (check(pos)) {
					if (pos == 80) {
						return true;
					}
					if (solvePuzzle(pos + 1)) {
						return true;
					}
				}
			}
		}
		puzzle[row][col] = 0;
		return false;
	}

	private boolean check(int pos) {
		int row = pos / 9;
		int col = pos % 9;
		HashMap<Integer, Integer> hmap = new HashMap<>();
		HashMap<Integer, Integer> hmap2 = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			if (hmap.containsKey(puzzle[row][i]) | hmap2.containsKey(puzzle[i][col])) {
				return false;
			} else {
				if (puzzle[row][i] != 0) {
					hmap.put(puzzle[row][i], 1);
				}
				if (puzzle[i][col] != 0) {
					hmap2.put(puzzle[i][col], 1);
				}
			}
		}
		hmap.clear();
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (hmap.containsKey(puzzle[(row / 3) * 3 + j][(col / 3) * 3 + k])) {
					return false;
				} else {
					if (puzzle[(row / 3) * 3 + j][(col / 3) * 3 + k] != 0) {
						hmap.put(puzzle[(row / 3) * 3 + j][(col / 3) * 3 + k], 1);
					}
				}
			}
		}
		return true;
	}
}
