package battleship;

public class Main {

    //main method to run program
    public static void main(String[] args) {
        GameController game = new GameController(); // Create a new GameController object
        
        game.startGame(); // Start the game

    }

}

/*
 * NOTES/LOG HERE
 * 
 * The abundant amount of comments in the code is for personal understanding of
 * the code because
 * my brain cannot brain and will delete unnecessary ones later for readability
 * 
 * 14/5/2024
 * Finished the board for the player, will add the computer board next
 * The board prints out as expected
 * 
 * 15/5/2024
 * Added player ship placement and input validation
 * Added computer ship placement
 * Added game loop in GameController class
 * Added game over conditions
 * Added computer AI
 * Debugged almost everything I can find
 * 
 */
