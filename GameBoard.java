package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class GameBoard extends JFrame {
    private Random random = new Random();
    private JButton[][] buttons = new JButton[10][10]; // 2D array of JButtons
    private boolean[][] playerBoard = new boolean[10][10]; // 2D array to track player's ship placement
    private boolean[][] computerBoard = new boolean[10][10]; // 2D array to track computer's ship placement
    private int playerShips = 5; // Number of player's ships remaining
    private int computerShips = 5; // Number of computer's ships remaining

    public GameBoard() {
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) { // for loop to create the 10x10 grid of JButtons
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                gridPanel.add(buttons[i][j]);
            }
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(gridPanel, new GridBagConstraints());

        getContentPane().add(centerPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        placeShips(this, true); // Place player's ships
        placeShips(this, false); // Place computer's ships
    }

    private void placeShips(GameBoard board, boolean isPlayer) {
        int shipSize = 1;
        boolean placed = false;
        while (!placed) {
            int x = isPlayer ? getUserInputForX() : random.nextInt(10);
            int y = isPlayer ? getUserInputForY() : random.nextInt(10);
            boolean horizontal = random.nextBoolean();
            placed = board.placeShip(x, y, shipSize, horizontal, isPlayer);
        }
    }

    private boolean placeShip(int x, int y, int shipSize, boolean horizontal, boolean isPlayer) {
        // Check if ship can be placed at the given coordinates
        if (isPlayer) {
            if (x + shipSize > 10 || y + shipSize > 10) {
                return false; // Ship goes out of bounds
            }
            for (int i = 0; i < shipSize; i++) {
                if (playerBoard[x + i][y]) {
                    return false; // Ship overlaps with another ship
                }
            }
            for (int i = 0; i < shipSize; i++) {
                playerBoard[x + i][y] = true; // Mark ship placement on player's board
            }
        } else {
            if (x + shipSize > 10 || y + shipSize > 10) {
                return false; // Ship goes out of bounds
            }
            for (int i = 0; i < shipSize; i++) {
                if (computerBoard[x + i][y]) {
                    return false; // Ship overlaps with another ship
                }
            }
            for (int i = 0; i < shipSize; i++) {
                computerBoard[x + i][y] = true; // Mark ship placement on computer's board
            }
        }
        return true; // Ship successfully placed
    }

    private int getUserInputForX() {
        String input = JOptionPane.showInputDialog("Enter X coordinate (0-9):");
        try {
            int x = Integer.parseInt(input);
            if (x >= 0 && x <= 9) {
                return x;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 9.");
                return getUserInputForX();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            return getUserInputForX();
        }
    }

    private int getUserInputForY() {
        String input = JOptionPane.showInputDialog("Enter Y coordinate (0-9):");
        try {
            int y = Integer.parseInt(input);
            if (y >= 0 && y <= 9) {
                return y;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 9.");
                return getUserInputForY();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            return getUserInputForY();
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int x;
        private int y;

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            // Implement button click logic
            if (playerBoard[x][y]) {
                // Player hit their own ship
                clickedButton.setBackground(Color.RED);
            } else {
                // Player missed
                clickedButton.setBackground(Color.WHITE);
            }
            // Check if the clicked button is a valid move
            if (playerBoard[x][y]) {
                // Invalid move, do nothing
                return;
            }
            // Update the game state based on the move
            if (computerBoard[x][y]) {
                // Computer's ship was hit
                computerShips--;
                if (computerShips == 0) {
                    // Computer has no ships left, player wins
                    JOptionPane.showMessageDialog(GameBoard.this, "You win!");
                    System.exit(0);
                }
            }
            // Check for win conditions
            if (playerShips == 0) {
                // Player has no ships left, computer wins
                JOptionPane.showMessageDialog(GameBoard.this, "Computer wins!");
                System.exit(0);
            }
            // Example: Update the player's board with the clicked move
            playerBoard[x][y] = true;
            // Example: Disable the clicked button to prevent further clicks
            clickedButton.setEnabled(false);
        }
    }
}