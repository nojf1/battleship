package battleship;

public class Main {

    //main method to run program
    public static void main(String[] args) {
        GameBoard playerBoard = new GameBoard(); // Create a new GameBoard object for the player

        playerBoard.printBoard(playerBoard.board);  // iniialise and print initial board
        playerBoard.placeShips(playerBoard.board); // input for player to place ships

        playerBoard.printBoard(playerBoard.board); // print the board with the ships placed
        

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
 */
