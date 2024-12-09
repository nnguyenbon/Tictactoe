package tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.util.Queue;
import javax.swing.*;

public class Game {

    int turn = 0;
    boolean gameOver = false;
    String currentPlayer = "X";
    String playerX = "X";
    String playerO = "O";
    JLabel textLabel = new JLabel();
    JButton rePlay = new JButton();
    Board board;

    public Game() {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new java.awt.BorderLayout());

        //Setup text label
        textLabel.setBackground(java.awt.Color.darkGray);
        textLabel.setForeground(java.awt.Color.white);
        textLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 50));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        frame.add(textLabel, BorderLayout.NORTH);

        //Setup board
        board = new Board(this);
        frame.add(board.getBoardPanel(), BorderLayout.CENTER);

        rePlay.setText("Replay");
        rePlay.setFont(new Font("sans serif", Font.CENTER_BASELINE, 20));
        rePlay.setForeground(Color.white);
        rePlay.setBackground(Color.red);
        rePlay.setFocusPainted(false);
        rePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        frame.add(rePlay, BorderLayout.SOUTH);
        rePlay.setPreferredSize(new Dimension(600, 50));
        frame.setVisible(true);
    }

    public void handleTileClick(int r, int c) {
        if (gameOver) {
            return;
        }

        JButton tile = board.getTile(r, c);

        if (tile.getText().isEmpty()) {
            tile.setText(currentPlayer);
            turn++;
            checkWinner();
            if (!gameOver) {
                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                textLabel.setText(currentPlayer + "'s turn.");
            }
        }
    }

    void checkWinner() {
        if (board.checkVictory(currentPlayer)) {
            gameOver = true;
            textLabel.setText(currentPlayer + " is the winner!");
            textLabel.setForeground(java.awt.Color.yellow);
            board.highlightWinner(currentPlayer);
            return;
        }

        // Check tie
        if (turn == 9) {
            gameOver = true;
            textLabel.setText("Tie!!!!");
            board.highlightTie();
        }
    }

    public void resetGame() {
        gameOver = false;
        turn = 0;
        currentPlayer = playerX;
        textLabel.setText("Tic-Tac-Toe");
        board.resetBoard();
    }
}
