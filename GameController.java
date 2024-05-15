package battleship;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GameController {

    // field to store the player's board
    // initialised in the constructor
    // accessed through playerBoard/computerBoard object instance created in
    // Main.java
    private GameBoard playerBoard;
    private GameBoard computerBoard;

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    private int playerScore = 0; // Score to keep track of player's hits
    private int computerScore = 0; // Score to keep track of computer's hits
    private int playerShips = 5; // Max starting number of player's ships
    private int computerShips = 5; // Max starting number of computer's ships

    private char playerHit = 'X'; // Character to represent a hit on player's board
    private char computerHit = '!'; // Character to represent a hit on computer's board
    private char miss = '-'; // Character to represent a miss on the board

    private enum Turn { // Enum to represent the current turn
        PLAYER, COMPUTER
    }

    private Turn currentTurn = Turn.PLAYER; // Start with player's turn

    private Set<String> chosenCoordinates = new HashSet<>(); // data structure set to store computer's chosen
                                                             // coordinates

    public GameController() {
        // Constructor for the GameController class
    }

    // method to start the game
    public void startGame() {
        // Create a new GameBoard object for the player
        playerBoard = new GameBoard();
        // Create a new GameBoard object for the computer
        computerBoard = new GameBoard();

        // Welcome message
        System.out.println("\n " + "Welcome to Battleship!");

        // Message to show the player's board
        System.out.println("\n " + "This is your board, place up to 5 ships to begin the game.\n");

        // Initialise and print initial board
        playerBoard.printBoard(playerBoard.board);

        // Input for player to place ships
        playerBoard.placeShips(playerBoard.board);

        // Place the computer's ships, not printed for player
        computerBoard.placeComputerShips(computerBoard.board, playerBoard.board);

        // Print the board with the ships placed
        System.out.println("\nYour board after ship placement:");
        playerBoard.printBoard(playerBoard.board);

        // For debugging to see where computer ships are placed
        System.out.println("\nComputer's board with ships hidden:");
        computerBoard.printBoard(computerBoard.board); // must show 2s

        // run the game
        runGame(false);

    }

    // method to run the game
    private boolean runGame(boolean gameOver) {
        // while loop to keep the game running until the game is over
        while (gameOver == false) {
            if (playerShips == 0) {
                System.out.println("\n " + "You've lost all your ships! Game over!");
                gameOver = true;
                break;
            } else if (computerShips == 0) {
                System.out.println("\n " + "You've sunk all the computer's ships! You win!");
                gameOver = true;
                break;
            }
            // start the game turn mechanics
            gameTurn();
            // Check if the game is over
            if (gameOver == true) {
                break;
            }
        }
        System.out.println("\n " + "Player's score: " + playerScore + 
        " | Computer's score: " + computerScore + "\nGame over!");
        System.out.println("\n " + "Do you want to play again? (Type Yes or No)");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("yes")) {
            startGame();
        } else {
            System.out.println("\n " + "Thanks for playing!");
        }
        return gameOver;
    }

    public void gameTurn() {

        // Player's turn
        if (currentTurn == Turn.PLAYER) {
            System.out.println("\n" + "Enter coordinates to fire at (x, y):"); // Prompt player for coordinates
            String inputString = scanner.nextLine(); // Read input from player
            String[] splitCoordinates = inputString.split(","); // Split the input by comma
            int x, y = 0; // Initialize x and y to 0

            try {
                x = Integer.parseInt(splitCoordinates[1].trim()); // Trim to remove any leading or trailing spaces
                y = Integer.parseInt(splitCoordinates[0].trim());

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { // Catch exceptions for invalid input
                                                                                 // format, e.g. "a,b"
                System.out.println("\n" + "Invalid input format. Please enter coordinates as x,y."); // Error message
                return; // Exit the method
            }

            // Check if the position is already taken
            if (playerBoard.board[x][y] == playerHit || playerBoard.board[x][y] == computerHit) {
                System.out.println("\n" + "You've already shot here. Take another shot somewhere else.");
                return; // Exit the method
            }

            // if the position is a ship ('1' for player's ship, '2' for computer's ship)
            if (playerBoard.board[x][y] == '1') {
                System.out.println("\n" + "You sunk your own ship! Why did you do that? You lost a ship!");
                playerBoard.board[x][y] = playerHit; // Mark player's ship as hit
                playerScore--; // Decrement player's score since they hit their own ship
                playerShips--; // Decrement player's ships
            } else if (computerBoard.board[x][y] == '2') {
                System.out.println("\n" + "You sunk the computer's ship! Computer lost a ship!");
                playerBoard.board[x][y] = computerHit; // Mark computer's ship as hit
                computerScore--; // Decrement computer's score since player hit their ship
                playerScore++; // Increment player's score since they hit the computer's ship
                computerShips--; // Decrement computer's ships
            } else {
                System.out.println("\n" + "You missed! You're a terrible shot!");
                playerBoard.board[x][y] = miss; // Mark as miss
            }

            System.out.println("\n" + playerShips + " player ships left | " + computerShips + " computer ships left");
            playerBoard.printBoard(playerBoard.board); // Print the updated board
            currentTurn = Turn.COMPUTER; // Switch to computer's turn
        }

        // Computer's turn
        if (currentTurn == Turn.COMPUTER) {
            int x, y;
            String coord;
            do {
                x = random.nextInt(10); // computer chooses random x coordinate
                y = random.nextInt(10); // computer chooses random y coordinate
                coord = x + "," + y; // Create a string representation of the coordinates
            } while (chosenCoordinates.contains(coord)); // Check if the coordinates were already chosen

            chosenCoordinates.add(coord); // Add new coordinates to the set so they are not chosen again

            // Check if the position is already taken
            if (computerBoard.board[x][y] == playerHit || computerBoard.board[x][y] == computerHit) {
                return; // Exit the method
            }

            // if the position is a ship ('1' for player's ship, '2' for computer's ship)
            if (computerBoard.board[x][y] == '2') {
                System.out.println("\n" + "Computer sunk its own ship! Computer lost a ship!");
                computerBoard.board[x][y] = computerHit; // Mark computer's ship as hit
                computerScore--; // Decrement computer's score since they hit their own ship
                computerShips--; // Decrement computer's ships
            } else if (playerBoard.board[x][y] == '1') {
                System.out.println("\n" + "Computer sunk your ship! You lost a ship!");
                computerBoard.board[x][y] = playerHit; // Mark player's ship as hit
                playerScore--; // Decrement player's score since computer hit their ship
                computerScore++; // Increment computer's score since they hit the player's ship
                playerShips--; // Decrement player's ships
            } else {
                System.out.println("\n" + "Computer missed! It's a terrible shot!");
                computerBoard.board[x][y] = miss; // Mark as miss
            }
            System.out.println("\n" + playerShips + " player ships left | " + computerShips + " computer ships left");
            playerBoard.printBoard(playerBoard.board); // Print the updated board
            currentTurn = Turn.PLAYER; // Switch to player's turn

        }

    }

}
