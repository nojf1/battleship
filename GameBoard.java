package battleship;

public class GameBoard {

    public char[][] board; // Instance variable to store the board
    public static final int BOARD_SIZE = 10; //declared as constant to prevent changes
    public static final int SHIPS = 5;

    //constructor for the GameBoard class
    public GameBoard() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE]; // Initialize the board array
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = '~'; // Fill each cell with '~' so it looks like the sea
            }
        }
    }

    //method for printing the board into terminal
    public void printBoard(char[][] board) { 
        System.out.print("  "); //two spaces for the column headers
        for (int i = 0; i < BOARD_SIZE; i++) { //increment i when i is less than BOARD_SIZE
            System.out.print(i + " "); //column headers
        }
        System.out.println(); //prints new line to separate column headers from the board

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " "); // Row headers
            for (int j = 0; j < BOARD_SIZE; j++) { //increment j when j is less than BOARD_SIZE
                System.out.print(board[i][j] + " "); //prints the board with spaces between each cell
            }
            System.out.println(); //prints new line to separate rows
        }

    }

}