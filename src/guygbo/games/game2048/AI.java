package guygbo.games.game2048;

public class AI {
    private static double[][] tileWeight = new double[4][4];

    public AI() {
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++)
                tileWeight[i][j] = Math.pow(2, i + j);
    }

    public static double evaluate(Board aBoard) {
        if (aBoard == null) return Double.MAX_VALUE;
        return testFunction(aBoard);
    }

    private static double testFunction(Board aBoard) {
        double value = 0;
        for (int i = 0; i < Board.BOARD_SIZE; i++)
            for (int j = 0; j < Board.BOARD_SIZE; j++)
                value += aBoard.getTile(i, j) * tileWeight[i][j];
        value = value + 10000*aBoard.getTileNumber();
        return value;
    }
}
