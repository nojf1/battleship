package battleship;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GameController {

    // field to store the player's board
    // initialised in the constructor
    private GameBoard playerBoard;
    private GameBoard computerBoard;

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    private boolean gameOver = false; // Boolean to keep track of the game state
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
                                                             // xy coordinates to prevent duplicate shots

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
        System.out.println("\nWelcome to Battleship!");

        // Message to show the player's board
        System.out.println("\nThis is your board, place up to 5 ships to begin the game.\n");

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
        runGame();

    }

    // method to run the game
    private void runGame() {
        // while loop to keep the game running until the game is over
        while (gameOver == false) {
            if (playerShips == 0) {
                System.out.println("\nYou've lost all your ships! Game over!");
                gameOver = true;
                break;
            } else if (computerShips == 0) {
                System.out.println("\nYou've sunk all the computer's ships! You win!");
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
        System.out.println("\nPlayer's score: " + playerScore +
                " | Computer's score: " + computerScore + "\n\nGame over!");
        restartGame();
        
    }

    public void gameTurn() {

        // Player's turn
        if (currentTurn == Turn.PLAYER) {
            System.out.println("\nEnter coordinates to fire at (x, y):"); // Prompt player for coordinates
            String inputString = scanner.nextLine(); // Read input from player
            String[] splitCoordinates = inputString.split(","); // Split the input by comma
            int x, y = 0; // Initialize x and y to 0

            try {
                x = Integer.parseInt(splitCoordinates[1].trim()); // Trim to remove any leading or trailing spaces
                y = Integer.parseInt(splitCoordinates[0].trim()); // to prevent NumberFormatException

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { // Catch exceptions for invalid input
                                                                                 // format, e.g. "a,b"
                System.out.println("\nInvalid input format. Please enter coordinates as x,y."); // Error message
                return; // Exit the method
            }

            // Check if the position is already taken
            if (playerBoard.board[x][y] == playerHit || playerBoard.board[x][y] == computerHit) {
                System.out.println("\nYou've already shot here. Take another shot somewhere else.");
                return; // Exit the method
            }

            // if the position is a ship ('1' for player's ship, '2' for computer's ship)
            if (playerBoard.board[x][y] == '1') {
                System.out.println("\nYou sunk your own ship! Why did you do that? You lost a ship!");
                playerBoard.board[x][y] = playerHit; // Mark player's ship as hit
                playerScore--; // Decrement player's score since they hit their own ship
                playerShips--; // Decrement player's ships
            } else if (computerBoard.board[x][y] == '2') {
                System.out.println("\nYou sunk the computer's ship! Computer lost a ship!");
                playerBoard.board[x][y] = computerHit; // Mark computer's ship as hit
                computerScore--; // Decrement computer's score since player hit their ship
                playerScore++; // Increment player's score since they hit the computer's ship
                computerShips--; // Decrement computer's ships
            } else {
                System.out.println("\nYou missed! You're a terrible shot!");
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
                System.out.println("\nComputer sunk its own ship! Computer lost a ship!");
                computerBoard.board[x][y] = computerHit; // Mark computer's ship as hit
                playerBoard.board[x][y] = computerHit; // Mark player's board computer's ship as hit
                computerScore--; // Decrement computer's score since they hit their own ship
                computerShips--; // Decrement computer's ships
            } else if (playerBoard.board[x][y] == '1') {
                System.out.println("\nComputer sunk your ship! You lost a ship!");
                computerBoard.board[x][y] = playerHit; // Mark player's ship as hit
                playerBoard.board[x][y] = playerHit; // Mark player's board player's ship as hit
                playerScore--; // Decrement player's score since computer hit their ship
                computerScore++; // Increment computer's score since they hit the player's ship
                playerShips--; // Decrement player's ships
            } else {
                System.out.println("\nComputer missed! It's a terrible shot!");
                computerBoard.board[x][y] = miss; // Mark as miss
                playerBoard.board[x][y] = miss; // Mark player's board as miss
            }
            System.out.println("\n" + playerShips + " player ships left | " + computerShips + " computer ships left");
            playerBoard.printBoard(playerBoard.board); // Print the updated board
            currentTurn = Turn.PLAYER; // Switch to player's turn

        }

    }

    public void restartGame () {
        System.out.println("\n" + "Do you want to play again? (Type Yes or No)");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y")) {
            
            playerShips = 5; // Reset player's ships so they can place them again
            computerShips = 5; // Reset computer's ships so they can be placed again
            playerScore = 0; // Reset player's score
            computerScore = 0; // Reset computer's score
            chosenCoordinates.clear(); // Clear the set of computer's previously chosen coordinates
            gameOver = false ; // Reset game over condition
            // all of this is to clear existing game data and start a new game
            startGame(); // start a new game by calling the startGame method
        } else if (playAgain.equalsIgnoreCase("no") || playAgain.equalsIgnoreCase("n")){
            gameOver = true; // set game over condition to true
            System.out.println("\n" + "Thanks for playing!");
        } else {
            System.out.println("\n" + "Invalid input. Please type Yes or No.");
            restartGame(); // repeatedly call the method until valid input is given
        }
    }
}
