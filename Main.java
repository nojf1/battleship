package battleship;

/*
 * This is the Main class to run the program
 * 
 * BATTLESHIP GAME
 * How it is played:
 * 1. Player places their ship on their board
 * 2. Computer places their ship on their board after player finishes deploying ships
 * 3. Player and computer take turns to guess the location of the other's ships, computer guesses randomly
 * 4. The first player to sink all the other's ships wins
 * 
 * Features:
 * - Two 10 x 10 grid is printed to terminal, one for player and one for computer
 * - Player and computer ships are stored in array and hidden in the board from each other
 * - Player and computer take turns which are tracked while guessing the location of each others ships
 * - ~ = water, @ = player ship, ! = computer ship hit, X = player ship hit, - = miss
 * - Score tracking system, displayed at the end of the game
 * - Turn counter, total turns taken displayed at the end of the game
 * - Game restart function after game over
 * - Input validation for ship placement and guessing, invalid inputs will prompt player to try again in the same turn
 * - Simple computer AI to guess player's ship location, computer guesses randomly but will not guess the same location twice
 * - Computer ship placement is random and will not overlap with player's ships
 * - Game over conditions, player or computer wins when all ships from either are sunk
 * 
 */

public class Main {

    //main method to run program
    public static void main(String[] args) {
        
        GameController game = new GameController(); // Create a new GameController object
        
        game.startGame(); // Start the game

    }

}

/*
 * NOTES/LOGs HERE
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
 * Added scoring system, might reconsider later to get rid of negative scores 
 * (got rid of negative scores now)
 * Added game restart function at game over
 * Added turn counter
 * 
 * 
 */
