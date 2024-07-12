
package x_o_simpel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private TicTacToe game;

    public TicTacToeGUI() {
        game = new TicTacToe();
        buttons = new JButton[3][3];

        setLayout(new GridLayout(3, 3));
        initializeButtons();

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonListener(i, j, game));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        int row, col;
        TicTacToe game;

        public ButtonListener(int row, int col, TicTacToe game) {
            this.row = row;
            this.col = col;
            this.game = game;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("-")) {
                buttons[row][col].setText(String.valueOf(game.getCurrentPlayer()));
                game.placeMark(row, col);

                if (game.checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + game.getCurrentPlayer() + " wins!");
                    resetBoard();
                } else if (game.isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "The game is a tie!");
                    resetBoard();
                } else {
                    game.changePlayer();
                }
            }
        }
    }

    public void resetBoard() {
        game.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
    }

  
}
