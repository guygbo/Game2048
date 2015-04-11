package guygbo.games.game2048;

public class Game {
    private Board currentBoard;
    private boolean isAutoMode;
    private boolean isGameOver;
    private int oldHighestScore;
    private int highestScore;

    public Game() {
        isAutoMode = false;
        isGameOver = true;
        oldHighestScore = IO.readHighestScore();
        highestScore = oldHighestScore;
    }

    public void newGame() {
        currentBoard = new Board();
        isAutoMode = false;
        isGameOver = false;
    }

    public void move(int direction) {
        if (currentBoard.move(direction)) {
            currentBoard.addNewTile();
            if (!currentBoard.isMovable()) isGameOver = true;
            updateHighestScore();
        }
    }

    public void autoMove() {
        Board bestBoard = null;
        int bestDirection = 0;
        for (int i = 0; i <= 3; i++) {
            Board tempBoard = new Board(currentBoard);
            if (tempBoard.move(i)) {
                if (AI.evaluate(tempBoard) < AI.evaluate(bestBoard)) {
                    bestBoard = tempBoard;
                    bestDirection = i;
                }
            }
        }
        currentBoard.move(bestDirection);
        currentBoard.addNewTile();
        isGameOver = !currentBoard.isMovable();
    }

    private void updateHighestScore() {
        int currentScore = currentBoard.getScore();
        if (currentScore > oldHighestScore) highestScore = currentScore;
        if (isGameOver && highestScore > oldHighestScore) {
            IO.writeNewHighestScore(highestScore);
            oldHighestScore = highestScore;
        }
    }

    public Board getBoard() {
        return currentBoard;
    }

    public boolean getStatus() {
        return isGameOver;
    }

    public boolean getMode() {
        return isAutoMode;
    }

    public void setMode(boolean mode) {
        this.isAutoMode = mode;
    }
    
    public int getHighestScore() {
        return highestScore;
    }

}
