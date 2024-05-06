import java.util.Scanner;

public class Game {
	private char[][] board;
	private char currentPlayer;

	public Game() {
		board = new char[3][3];
		currentPlayer = 'X';
		initializeBoard();
	}

	public void startGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to The Game!");
		printBoard();

		while (true) {
			System.out.println("Player " + currentPlayer + ", enter row and column (1-3):");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;
				if (isGameOver(row, col)) {
					System.out.println("Game over! Player " + currentPlayer + " wins!");
					break;
				}
				switchPlayer();
				printBoard();
			} else {
				System.out.println("Invalid move, try again.");
			}
		}
		scanner.close();
	}

	private void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '_';
			}
		}
	}

	private void printBoard() {
		for (char[] row : board) {
			for (char cell : row) {
				System.out.print("| " + cell + " ");
			}
			System.out.println("|");
		}
	}

	private boolean isValidMove(int row, int col) {
		return row >= 0 && row < board.length &&
			col >= 0 && col < board[row].length &&
			board[row][col] == '_';
	}

	private boolean isGameOver(int lastRow, int lastCol) {
		return checkRow(lastRow) || checkColumn(lastCol) ||
			checkDiagonal() || checkAntiDiagonal();
	}

	private boolean checkRow(int row) {
		return board[row][0] == board[row][1] && board[row][0] == board[row][2];
	}

	private boolean checkColumn(int col) {
		return board[0][col] == board[1][col] && board[0][col] == board[2][col];
	}

	private boolean checkDiagonal() {
		return board[0][0] == board[1][1] && board[0][0] == board[2][2];
	}

	private boolean checkAntiDiagonal() {
		return board[0][2] == board[1][1] && board[0][2] == board[2][0];
	}

	private void switchPlayer() {
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
	}
}
