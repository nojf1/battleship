package battleship;

public class Main {

    //main method to run program
    public static void main(String[] args) {
        GameBoard playerBoard = new GameBoard(); // Create a new GameBoard object for the player
        GameBoard computerBoard = new GameBoard(); // Create a new GameBoard object for the computer

        System.out.println("Welcome to Battleship!\n"); // welcome message
        System.out.println("This is your board, place up to 5 ships to begin the game.\n"); 
        // message to show the player's board
        playerBoard.printBoard(playerBoard.board);  // initialise and print initial board
        playerBoard.placeShips(playerBoard.board); // input for player to place ships

        computerBoard.placeComputerShips(computerBoard.board); // place the computer's ships, not printed for player

        System.out.println("\nYour board after ship placement:");
        playerBoard.printBoard(playerBoard.board); // print the board with the ships placed

        //for debugging to see computer ships
        System.out.println("\nComputer's board with ships hidden:");
        computerBoard.printBoard(computerBoard.board); // must show 2's
        

    }

}

/*
 * NOTES/LOG HERE
 * 
 * The abundant amount of comments in the code is for personal understanding of
 * the code because
 * my brain cannot brain and will delete later for readability
 * 
 * 14/5/2024
 * Finished the board for the player, will add the computer board next
 * The board prints out as expected
 * 
 * 15/5/2024
 * Added player ship placement and input validation
 * Added computer ship placement
 */
