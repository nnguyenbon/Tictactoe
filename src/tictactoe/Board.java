package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Board {

    private JPanel boardPanel;
    private JButton[][] tiles = new JButton[3][3];
    private Game game;

    public Board(Game game) {
        this.game = game;
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                tiles[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 50));
                tile.setFocusable(false);

                int row = r;
                int col = c;

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.handleTileClick(row, col);
                    }
                });
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JButton getTile(int r, int c) {
        return tiles[r][c];
    }

    public boolean checkVictory(String player) {
        for (int r = 0; r < 3; r++) {
            if (tiles[r][0].getText().equals(player)
                    && tiles[r][1].getText().equals(player)
                    && tiles[r][2].getText().equals(player)) {
                return true;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (tiles[0][c].getText().equals(player)
                    && tiles[1][c].getText().equals(player)
                    && tiles[2][c].getText().equals(player)) {
                return true;
            }
        }

        if (tiles[0][0].getText().equals(player)
                && tiles[1][1].getText().equals(player)
                && tiles[2][2].getText().equals(player)) {
            return true;
        }

        if (tiles[0][2].getText().equals(player)
                && tiles[1][1].getText().equals(player)
                && tiles[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }

    public void highlightWinner(String player) {
        // Highlight winning tiles
        for (int r = 0; r < 3; r++) {
            if (tiles[r][0].getText().equals(player)
                    && tiles[r][1].getText().equals(player)
                    && tiles[r][2].getText().equals(player)) {
                setWinnerColor(tiles[r][0], tiles[r][1], tiles[r][2]);
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (tiles[0][c].getText().equals(player)
                    && tiles[1][c].getText().equals(player)
                    && tiles[2][c].getText().equals(player)) {
                setWinnerColor(tiles[0][c], tiles[1][c], tiles[2][c]);
                return;
            }
        }

        if (tiles[0][0].getText().equals(player)
                && tiles[1][1].getText().equals(player)
                && tiles[2][2].getText().equals(player)) {
            setWinnerColor(tiles[0][0], tiles[1][1], tiles[2][2]);
            return;
        }

        if (tiles[0][2].getText().equals(player)
                && tiles[1][1].getText().equals(player)
                && tiles[2][0].getText().equals(player)) {
            setWinnerColor(tiles[0][2], tiles[1][1], tiles[2][0]);
        }
    }

    private void setWinnerColor(JButton... tiles) {
        for (JButton tile : tiles) {
            tile.setBackground(Color.green);
        }
    }

    public void highlightTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c].setBackground(Color.orange);
                tiles[r][c].setForeground(Color.gray);
            }
        }
    }

    public void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c].setText(""); // Xóa giá trị của từng ô
                tiles[r][c].setBackground(Color.darkGray); // Đặt lại màu nền
                tiles[r][c].setForeground(Color.white); // Đặt lại màu chữ
            }
        }
    }
}
