package playGroundGames;

import java.util.Scanner;

public class NaughtsAndCrosses {

	public static char turn = 'X';
	public static Scanner placement = new Scanner(System.in);
	public static char[][] grid;
	public static int row, col;

	public static void main(String[] args) {
		grid = new char[3][3];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				grid[i][j] = '_';
			}
		}
		PrintBoard();
		PlayGame();

	}

	public static void PrintBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.println();
			for (int j = 0; j < 3; j++) {
				if (j < 2) {
					System.out.print(grid[i][j] + " | ");
				} else {
					System.out.print(grid[i][j]);
				}
			}
		}
		System.out.println();
	}

	public static void PlayGame() {
		boolean play = true;
		System.out.println("Let's play! Xs go first!");
		int turncounter = 0;
		while (play == true) {
			System.out.println("Chose your placement by entering a row, then a column:");
			row = placement.nextInt() - 1;
			col = placement.nextInt() - 1;
			
			
			while (grid[row][col] == 'X' || grid[row][col] == 'O') {
				System.out.println("That place is taken.");
				System.out.println("Chose your row, then chose your column again... numpty... ");
				row = placement.nextInt() - 1;
				col = placement.nextInt() - 1;
			}
			
			grid[row][col] = turn;
			PrintBoard();
			if (CheckWinner(row, col) == true) {
				System.out.println("Awesome! The " + turn + "s won!");
				System.out.println("Rerun for a rematch!");
				play = false;
				break;
			}
			
			turncounter++;
			
			if(turncounter >= 9) {
				System.out.println("It's a draw! Rerun to play again.");
				break;
			}

			if (turn == 'X') {
				turn = 'O';
			} else {
				turn = 'X';
			}

			System.out.println("Alright now it's " + turn + "s turn.");
		}
	}

	public static boolean CheckWinner(int row, int col) {
		if (
		// check row win
		(grid[row][0] == grid[row][1] && grid[row][2] == grid[row][0]) ||
		// check col win
				(grid[0][col] == grid[1][col] && grid[2][col] == grid[0][col]) ||
				// check diagonal win
				(grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2])
				|| (grid[0][2] == grid[1][1] && grid[0][0] == grid[2][0])) {
			return true;
		} else {
			return false;
		}
	}
	
//	public static void MakeMove() {
//		try {
//			row = placement.nextInt() - 1;
//			col = placement.nextInt() - 1;
//		}
//		catch
//	}
	
}
