package guygbo.games.game2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class View extends JFrame {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;
    public static final int GAME_PANEL_SIZE = 400;

    private JLabel tile[][] = new JLabel[Board.BOARD_SIZE][Board.BOARD_SIZE];
    private static JLabel currentScoreLabel;
    private static JLabel highestScoreLabel;
    private static JLabel displayAreaLabel;
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JButton exitButton = new JButton("Exit");
    private JButton autoplayButton = new JButton("Auto Play");
    private Dimension buttonDimension = new Dimension(90, 30);

    public View() {

        // setup frame
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        // this.setBackground(Color.WHITE);

        // setup main game panel, use GridLayout
        JPanel gamePanel = new JPanel(new GridLayout(4, 4, 2, 2));
        gamePanel.setMaximumSize(new Dimension(GAME_PANEL_SIZE, GAME_PANEL_SIZE));
        gamePanel.setMinimumSize(new Dimension(GAME_PANEL_SIZE, GAME_PANEL_SIZE));
        gamePanel.setPreferredSize(new Dimension(GAME_PANEL_SIZE, GAME_PANEL_SIZE));
        gamePanel.setBackground(Color.DARK_GRAY);
        this.add(gamePanel);

        // setup 4*4 sub-panels
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                tile[i][j] = new JLabel();
                tile[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tile[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                tile[i][j].setOpaque(true);
                tile[i][j].setBackground(new Color(120, 120, 120));
                gamePanel.add(tile[i][j]);
            }
        }

        // setup side control panel, use BoxLayout
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        controlPanel.setBackground(new Color(255, 255, 204));
        this.add(controlPanel);

        JLabel gameTitle = new JLabel("2048");
        gameTitle.setFont(new Font("Serif", Font.PLAIN, 48));
        gameTitle.setForeground(new Color(76, 0, 153));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        c.insets = new Insets(20, 10, 0, 20);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weighty = 0.0;
        controlPanel.add(gameTitle, c);

        currentScoreLabel = new JLabel("Current score: " + 0);
        c.insets = new Insets(20, 10, 0, 10);
        currentScoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(currentScoreLabel, c);

        highestScoreLabel = new JLabel();
        c.insets = new Insets(10, 10, 0, 10);
        highestScoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(highestScoreLabel, c);

        displayAreaLabel = new JLabel("<html>Welcome to the game!<br><br>Press [Start] to start.</html>");
        displayAreaLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        displayAreaLabel.setForeground(new Color(76, 0, 153));
        displayAreaLabel.setMinimumSize(new Dimension(WINDOW_WIDTH - GAME_PANEL_SIZE, WINDOW_HEIGHT / 6));
        displayAreaLabel.setMaximumSize(new Dimension(WINDOW_WIDTH - GAME_PANEL_SIZE, WINDOW_HEIGHT / 6));
        displayAreaLabel.setPreferredSize(new Dimension(WINDOW_WIDTH - GAME_PANEL_SIZE, WINDOW_HEIGHT / 6));
        c.insets = new Insets(10, 10, 0, 10);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(displayAreaLabel, c);

        startButton.setPreferredSize(buttonDimension);
        startButton.setMaximumSize(buttonDimension);
        startButton.setMinimumSize(buttonDimension);
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        controlPanel.add(startButton, c);

        resetButton.setPreferredSize(buttonDimension);
        resetButton.setMaximumSize(buttonDimension);
        resetButton.setMinimumSize(buttonDimension);
        c.insets = new Insets(20, 0, 0, 20);
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        controlPanel.add(resetButton, c);

        autoplayButton.setPreferredSize(buttonDimension);
        autoplayButton.setMaximumSize(buttonDimension);
        autoplayButton.setMinimumSize(buttonDimension);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        controlPanel.add(autoplayButton, c);

        exitButton.setPreferredSize(buttonDimension);
        exitButton.setMaximumSize(buttonDimension);
        exitButton.setMinimumSize(buttonDimension);
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        controlPanel.add(exitButton, c);
    }

    void drawBoard(Board aBoard) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                int tileValue = aBoard.getTile(i, j);
                if (tileValue != 0) {
                    tile[i][j].setText(((Integer) tileValue).toString());
                    if (tileValue == 2) {
                        tile[i][j].setBackground(new Color(224, 224, 224));
                        tile[i][j].setForeground(new Color(64, 64, 64));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 60));
                    } else if (tileValue == 4) {
                        tile[i][j].setBackground(new Color(192, 192, 192));
                        tile[i][j].setForeground(new Color(64, 64, 64));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 60));
                    } else if (tileValue == 8) {
                        tile[i][j].setBackground(new Color(160, 160, 160));
                        tile[i][j].setForeground(new Color(64, 64, 64));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 60));
                    } else if (tileValue == 16) {
                        tile[i][j].setBackground(new Color(255, 204, 153));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 60));
                    } else if (tileValue == 32) {
                        tile[i][j].setBackground(new Color(255, 178, 102));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 60));
                    } else if (tileValue == 64) {
                        tile[i][j].setBackground(new Color(255, 128, 0));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 60));
                    } else if (tileValue == 128) {
                        tile[i][j].setBackground(new Color(255, 204, 204));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 48));
                    } else if (tileValue == 256) {
                        tile[i][j].setBackground(new Color(255, 102, 102));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 48));
                    } else if (tileValue == 512) {
                        tile[i][j].setBackground(new Color(255, 0, 0));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 48));
                    } else if (tileValue == 1024) {
                        tile[i][j].setBackground(new Color(204, 255, 204));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 36));
                    } else if (tileValue == 2048) {
                        tile[i][j].setBackground(new Color(102, 255, 102));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 36));
                    } else if (tileValue == 4096) {
                        tile[i][j].setBackground(new Color(0, 255, 0));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 36));
                    } else if (tileValue == 8192) {
                        tile[i][j].setBackground(new Color(204, 204, 255));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 36));
                    } else if (tileValue == 16384) {
                        tile[i][j].setBackground(new Color(102, 102, 255));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 28));
                    } else if (tileValue == 32768) {
                        tile[i][j].setBackground(new Color(0, 0, 255));
                        tile[i][j].setForeground(new Color(255, 255, 255));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 28));
                    } else {
                        tile[i][j].setBackground(new Color(204, 0, 102));
                        tile[i][j].setForeground(new Color(0, 0, 0));
                        tile[i][j].setFont(new Font("Serif", Font.PLAIN, 28));
                    }
                } else {
                    tile[i][j].setText("");
                    tile[i][j].setBackground(new Color(120, 120, 120));
                }
            }
        }
    }

    void setDisplayArea(String s) {
        displayAreaLabel.setText(s);
    }

    void setLabelCurrentScore(int i) {
        currentScoreLabel.setText("Current score: " + i);
    }

    void setLabelHighestScore(int i) {
        highestScoreLabel.setText("Highest score: " + i);
    }

    void addStartListener(ActionListener startListener) {
        startButton.addActionListener(startListener);
    }

    void addResetListener(ActionListener resetListener) {
        resetButton.addActionListener(resetListener);
    }

    void addExitListener(ActionListener exitListener) {
        exitButton.addActionListener(exitListener);
    }

    void addAutoplayListener(ActionListener autoplayListener) {
        autoplayButton.addActionListener(autoplayListener);
    }
}
