package guygbo.games.game2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Controller {
    private Game currentGame;
    private View gameView;

    public Controller(Game aGame, View aView) {
        this.currentGame = aGame;
        this.gameView = aView;
        gameView.setLabelHighestScore(currentGame.getHighestScore());
        addListener();
        addKeyBinding();
    }

    private void addListener() {
        this.gameView.addStartListener(new StartListener());
        this.gameView.addResetListener(new ResetListener());
        this.gameView.addExitListener(new ExitListener());
        this.gameView.addAutoplayListener(new AutoplayListener());
    }

    private void addKeyBinding() {
        gameView.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        gameView.getRootPane().getActionMap().put("up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!currentGame.getStatus() && !currentGame.getMode()) {
                    currentGame.move(0);
                    gameView.setLabelCurrentScore(currentGame.getBoard().getScore());
                    gameView.setLabelHighestScore(currentGame.getHighestScore());
                    gameView.drawBoard(currentGame.getBoard());
                    if (currentGame.getStatus()) gameView.setDisplayArea("<html>Game Over.<br><br>Try again!<html>");
                }
            }
        });

        gameView.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        gameView.getRootPane().getActionMap().put("down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!currentGame.getStatus() && !currentGame.getMode()) {
                    currentGame.move(1);
                    gameView.setLabelCurrentScore(currentGame.getBoard().getScore());
                    gameView.setLabelHighestScore(currentGame.getHighestScore());
                    gameView.drawBoard(currentGame.getBoard());
                    if (currentGame.getStatus()) gameView.setDisplayArea("<html>Game Over.<br><br>Try again!<html>");
                }
            }
        });

        gameView.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        gameView.getRootPane().getActionMap().put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!currentGame.getStatus() && !currentGame.getMode()) {
                    currentGame.move(2);
                    gameView.setLabelCurrentScore(currentGame.getBoard().getScore());
                    gameView.setLabelHighestScore(currentGame.getHighestScore());
                    gameView.drawBoard(currentGame.getBoard());
                    if (currentGame.getStatus()) gameView.setDisplayArea("<html>Game Over.<br><br>Try again!<html>");
                }
            }
        });

        gameView.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        gameView.getRootPane().getActionMap().put("right", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!currentGame.getStatus() && !currentGame.getMode()) {
                    currentGame.move(3);
                    gameView.setLabelCurrentScore(currentGame.getBoard().getScore());
                    gameView.setLabelHighestScore(currentGame.getHighestScore());
                    gameView.drawBoard(currentGame.getBoard());
                    if (currentGame.getStatus()) gameView.setDisplayArea("<html>Game Over.<br><br>Try again!<html>");
                }
            }
        });

    }

    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentGame.getStatus()) {
                currentGame.newGame();
                gameView.setLabelCurrentScore(0);
                gameView.setDisplayArea("<html>Have fun!<br><br><br></html>");
                gameView.drawBoard(currentGame.getBoard());
            }
        }
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!currentGame.getStatus()) {
                currentGame.newGame();
                gameView.setLabelCurrentScore(0);
                gameView.setDisplayArea("<html>Have fun!<br><br></html>");
                gameView.drawBoard(currentGame.getBoard());
            }
        }
    }

    private class AutoplayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentGame.setMode(true);
            autoPlay();
        }
    }

    private void autoPlay() {
        new Thread(new Runnable() {
            public void run() {
                while (!currentGame.getStatus()) {
                    currentGame.autoMove();
                    gameView.drawBoard(currentGame.getBoard());
                    gameView.setLabelCurrentScore(currentGame.getBoard().getScore());
                    if (currentGame.getStatus())
                        gameView.setDisplayArea("<html>I have finished.<br><br>It's your turn!</html>");
                    else gameView.setDisplayArea("<html>I am playing...<br><br></html>");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
