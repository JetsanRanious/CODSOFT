import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true;
    private int movesCount = 0;
    private int scoreX = 0;
    private int scoreO = 0;

    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JButton restartButton;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe vs AI (Minimax)");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Game panel
        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
            }
        }

        // Status and restart
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        statusLabel = new JLabel("Your turn (X)", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score - You (X): 0 | AI (O): 0", SwingConstants.CENTER);
        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(e -> resetGame(true));

        bottomPanel.add(statusLabel);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(restartButton);

        add(gamePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!playerXTurn) return; // Block input during AI's turn

        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return;

        clicked.setText("X");
        movesCount++;
        if (checkWinner("X")) {
            scoreX++;
            JOptionPane.showMessageDialog(this, "You win!");
            updateScore();
            resetGame(false);
            return;
        }

        if (movesCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame(false);
            return;
        }

        playerXTurn = false;
        statusLabel.setText("AI is thinking...");

        // Delay AI move slightly for UX
        Timer timer = new Timer(500, evt -> {
            makeAIMove();
            playerXTurn = true;
            statusLabel.setText("Your turn (X)");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void makeAIMove() {
        int[] move = findBestMove();
        buttons[move[0]][move[1]].setText("O");
        movesCount++;

        if (checkWinner("O")) {
            scoreO++;
            JOptionPane.showMessageDialog(this, "AI wins!");
            updateScore();
            resetGame(false);
        } else if (movesCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame(false);
        }
    }

    private boolean checkWinner(String player) {
        // Rows, columns, diagonals
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) &&
                buttons[i][1].getText().equals(player) &&
                buttons[i][2].getText().equals(player)) return true;

            if (buttons[0][i].getText().equals(player) &&
                buttons[1][i].getText().equals(player) &&
                buttons[2][i].getText().equals(player)) return true;
        }

        if (buttons[0][0].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][2].getText().equals(player)) return true;

        if (buttons[0][2].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][0].getText().equals(player)) return true;

        return false;
    }

    private void updateScore() {
        scoreLabel.setText("Score - You (X): " + scoreX + " | AI (O): " + scoreO);
    }

    private void resetGame(boolean fullReset) {
        movesCount = 0;
        playerXTurn = true;
        statusLabel.setText("Your turn (X)");

        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                btn.setText("");
            }
        }

        if (fullReset) {
            scoreX = 0;
            scoreO = 0;
            updateScore();
        }
    }

    // ======== Minimax AI Implementation ========
    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText("O");
                    int score = minimax(false);
                    buttons[i][j].setText("");
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWinner("O")) return 1;
        if (checkWinner("X")) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText(isMaximizing ? "O" : "X");
                    int score = minimax(!isMaximizing);
                    buttons[i][j].setText("");

                    bestScore = isMaximizing
                            ? Math.max(score, bestScore)
                            : Math.min(score, bestScore);
                }
            }
        }

        return bestScore;
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                if (btn.getText().equals("")) return false;
        return true;
    }

    // ======== Main Method ========
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
