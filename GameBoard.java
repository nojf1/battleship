package battleship;

import java.util.Scanner;

public class GameBoard {

    public char[][] board; // Instance variable to store the board
    public static final int BOARD_SIZE = 10; // declared as constant to prevent changes
    public static final int SHIPS = 5;

    // constructor for the GameBoard class
    public GameBoard() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE]; // Initialize the board array
        for (int i = 0; i < BOARD_SIZE; i++) { // for loop to iterate through the board
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = '~'; // Fill each cell with '~' so it looks like the sea ~ ~ ~ water
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
                    System.out.print("@ "); // prints players ship as @ to hide ship location, extra whitespace for
                                            // formatting
                } else {
                    System.out.print(board[i][j] + " "); // prints the board with spaces between each cell
                }
            }
            System.out.println(); // prints new line to separate rows
        }

    }

    // method to place ships on the board
    public void placeShips(char[][] board) {
        @SuppressWarnings("resource") // suppresses the warning for scanner not closed
        Scanner scanner = new Scanner(System.in); // scanner object to get user input

        for (int i = 1; i <= SHIPS;) { // for loop to place ships until i is equal to SHIPS
            boolean validInput = false; // reset validInput to false at the start of each ship placement attempt
            while (!validInput) { // while input is not valid
                System.out.println("\nEnter coordinates to place ship " + i + " at (x, y):"); // Prompt player for
                                                                                              // coordinates
                String inputString = scanner.nextLine(); // Read input from player
                String[] splitCoordinates = inputString.split(","); // Split the input by comma
                int x = 0, y = 0; // Initialize x and y

                try {
                    x = Integer.parseInt(splitCoordinates[0].trim()); // Parse x from input
                    y = Integer.parseInt(splitCoordinates[1].trim()); // Parse y from input
                    validInput = true; // Input is valid, proceed with placement

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { // Catch exceptions for invalid
                                                                                     // input
                    System.out.println("\nInvalid input format. Please enter coordinates as x,y."); // Error message
                    // loop to continue for another input attempt
                }

                if (validInput) { // Proceed only if input is valid
                    // Check if coordinates are within bounds and the position is not already taken
                    if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[y][x] != '1') {
                        board[y][x] = '1'; // Place the player's ship
                        i++; // Only increment if the ship is successfully placed
                    } else { // Invalid coordinates or position already taken
                        System.out.println("Invalid coordinates or position already taken. Try again.");
                        validInput = false; // Ensure the loop prompts for input again
                    }
                }
            }
        }
    }

    // method to place computer ships
    public void placeComputerShips(char[][] board, char[][] playerBoard) {
        System.out.println("\nComputer is placing ships...");
        for (int i = 1; i <= SHIPS;) { // for loop to place ships until i is equal to SHIPS
            int x = (int) (Math.random() * BOARD_SIZE); // computer chooses random x coordinate
            int y = (int) (Math.random() * BOARD_SIZE); // random y coordinate

            // check if the position is not already taken
            if (board[x][y] == '~' && playerBoard[x][y] != '1') {
                board[x][y] = '2'; // place the computer's ship
                System.out.println("Computer has placed ship " + i + ".");
                i++; // only increment if the ship is successfully placed
            }
        }
        System.out.println("\nComputer has placed all its ships.");
    }

}