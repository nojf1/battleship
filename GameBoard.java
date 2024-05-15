package battleship;

import java.util.Scanner;

public class GameBoard {

    public char[][] board; // Instance variable to store the board
    public static final int BOARD_SIZE = 10; // declared as constant to prevent changes
    public static final int SHIPS = 5;

    // constructor for the GameBoard class
    public GameBoard() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE]; // Initialize the board array
        for (int i = 0; i < BOARD_SIZE; i++) { // increment i when i is less than BOARD_SIZE for x coordinate
            for (int j = 0; j < BOARD_SIZE; j++) { // increment j when j is less than BOARD_SIZE for y coordinate
                this.board[i][j] = '~'; // Fill each cell with '~' so it looks like the sea
            }
        }
    }

    // method for printing the board into terminal
    public void printBoard(char[][] board) {
        System.out.print("  "); // two spaces for the column headers
        for (int i = 0; i < BOARD_SIZE; i++) { // increment i when i is less than BOARD_SIZE
            System.out.print(i + " "); // column headers
        }
        System.out.println(); // prints new line to separate column headers from the board

        for (int i = 0; i < BOARD_SIZE; i++) { // for loop to print the board until i is equal to BOARD_SIZE
            System.out.print(i + " "); // Row headers
            for (int j = 0; j < BOARD_SIZE; j++) { // increment j when j is less than BOARD_SIZE
                if (board[i][j] == '1') { // if the cell is equal to 1, print the player's ship
                    System.out.print("@ "); //prints players ship as @ to hide ship location, extra whitespace for formatting
                } else {
                    System.out.print(board[i][j] + " "); // prints the board with spaces between each cell
                }
            }
            System.out.println(); // prints new line to separate rows
        }

    }

    // method to place ships on the board
    public void placeShips(char[][] board) {
        @SuppressWarnings("resource") // suppresses the warning for scanner not closed because the warning is annoying to look at
        Scanner scanner = new Scanner(System.in); // scanner object to get user input
        System.out.println("Place your " + SHIPS + " ships:"); // prints the number of ships the player has to place

        for (int i = 1; i <= SHIPS;) { // for loop to place ships until i is equal to SHIPS
            System.out.print("Enter X coordinate for ship " + i + ": "); // prompts user to enter x coordinate
            int x = scanner.nextInt(); // stores the x coordinate
            System.out.print("Enter Y coordinate for ship " + i + ": "); // prompts user to enter y coordinate
            int y = scanner.nextInt(); // stores the y coordinate

            // check if coordinates are within bounds and the position is not already taken
            // if x is greater than or equal to 0 and less than BOARD_SIZE and y is greater
            // than
            // or equal to 0 and less than BOARD_SIZE and the position is not already taken
            if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[x][y] == '~') {
                board[x][y] = '1'; // place the player's ship
                i++; // only increment if the ship is successfully placed
            } else { // error message
                System.out.println("Invalid coordinates or position already taken. Try again.");
            }
        }
    }

    // method to place computer ships
    public void placeComputerShips(char[][] board) {
        System.out.println("\nComputer is placing ships...");
        for (int i = 1; i <= SHIPS;) { // for loop to place ships until i is equal to SHIPS
            int x = (int) (Math.random() * BOARD_SIZE); // random x coordinate
            int y = (int) (Math.random() * BOARD_SIZE); // random y coordinate

            // check if the position is not already taken
            if (board[x][y] == '~') {
                board[x][y] = '2'; // place the computer's ship
                System.out.println("Computer has placed ship " + i + ".");
                i++; // only increment if the ship is successfully placed
            }
        }
        System.out.println("\nComputer has placed all its ships.");
    }

}