package guygbo.games.game2048;

import java.util.ArrayList;

public class Board {
    public static final int BOARD_SIZE = 4;
    public static final int TOTAL_TILE = BOARD_SIZE * BOARD_SIZE;

    private int currentScore;
    private int numTile;
    private int tile[][] = new int[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        currentScore = 0;
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                tile[i][j] = 0;
        initialization();
    }

    public Board(Board that) {
        currentScore = that.currentScore;
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                this.tile[i][j] = that.tile[i][j];
        this.numTile = that.numTile;
    }

    // add two random tiles
    private void initialization() {
        addNewTile();
        addNewTile();
    }

    // if the board is full
    public boolean isFull() {
        return numTile == TOTAL_TILE;
    }

    // add a new tile
    public void addNewTile() {
        int newTileLocation;
        while (true) {
            newTileLocation = tileLocation();
            int row = newTileLocation / BOARD_SIZE;
            int column = newTileLocation % BOARD_SIZE;
            if (tile[row][column] == 0) {
                tile[row][column] = tileTwoOrFour();
                numTile++;
                break;
            }
        }
    }

    // choose a random grid
    private int tileLocation() {
        return (int) Math.floor(Math.random() * TOTAL_TILE);
    }

    // if add a 2 or 4
    private int tileTwoOrFour() {
        if (Math.random() < 0.9)
            return 2;
        else
            return 4;
    }

    public boolean move(int direction) {
        if (direction == 0)
            return moveUp();
        else if (direction == 1)
            return moveDown();
        else if (direction == 2)
            return moveLeft();
        else if (direction == 3)
            return moveRight();
        else
            return false;
    }

    // move up, return if actually moved
    private boolean moveUp() {
        boolean hasMoved = false;
        for (int j = 0; j < BOARD_SIZE; j++) {
            int[] line = { tile[0][j], tile[1][j], tile[2][j], tile[3][j] };
            ArrayList<Integer> alist = moveLine(line);
            for (int i = 0; i < BOARD_SIZE; i++)
                this.tile[i][j] = 0;
            for (int i = 0; i < alist.size() - 1; i++)
                tile[i][j] = alist.get(i);
            if (alist.get(alist.size() - 1) == 1) hasMoved = true;
        }
        return hasMoved;
    }

    // move down, return if actually moved
    private boolean moveDown() {
        boolean hasMoved = false;
        for (int j = 0; j < BOARD_SIZE; j++) {
            int[] line = { tile[3][j], tile[2][j], tile[1][j], tile[0][j] };
            ArrayList<Integer> alist = moveLine(line);
            for (int i = 0; i < BOARD_SIZE; i++)
                tile[i][j] = 0;
            for (int i = BOARD_SIZE - 1; i > BOARD_SIZE - alist.size(); i--)
                tile[i][j] = alist.get(BOARD_SIZE - 1 - i);
            if (alist.get(alist.size() - 1) == 1) hasMoved = true;
        }
        return hasMoved;
    }

    // move left, return if actually moved
    private boolean moveLeft() {
        boolean hasMoved = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int[] line = { tile[i][0], tile[i][1], tile[i][2], tile[i][3] };
            ArrayList<Integer> alist = moveLine(line);
            for (int j = 0; j < BOARD_SIZE; j++)
                tile[i][j] = 0;
            for (int j = 0; j < alist.size() - 1; j++)
                tile[i][j] = alist.get(j);
            if (alist.get(alist.size() - 1) == 1) hasMoved = true;
        }
        return hasMoved;
    }

    // move right, return if actually moved
    private boolean moveRight() {
        boolean hasMoved = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int[] line = { tile[i][3], tile[i][2], tile[i][1], tile[i][0] };
            ArrayList<Integer> alist = moveLine(line);
            for (int j = 0; j < BOARD_SIZE; j++)
                tile[i][j] = 0;
            for (int j = BOARD_SIZE - 1; j > BOARD_SIZE - alist.size(); j--)
                tile[i][j] = alist.get(BOARD_SIZE - 1 - j);
            if (alist.get(alist.size() - 1) == 1) hasMoved = true;
        }
        return hasMoved;
    }

    // help method to move a row/column, last item if 1, moved, or if 0, not moved
    private ArrayList<Integer> moveLine(int[] line) {
        ArrayList<Integer> alist = new ArrayList<Integer>();
        boolean hasMoved = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (line[i] != 0) alist.add(line[i]);
        }
        if (alist.size() == 2) {
            if (alist.get(0).equals(alist.get(1))) {
                alist.set(0, alist.get(1) * 2);
                currentScore += alist.get(0);
                alist.remove(1);
                numTile--;
            }
        } else if (alist.size() == 3) {
            if (alist.get(0).equals(alist.get(1))) {
                alist.set(0, alist.get(1) * 2);
                currentScore += alist.get(0);
                alist.remove(1);
                numTile--;
            } else if (alist.get(1).equals(alist.get(2))) {
                alist.set(1, alist.get(2) * 2);
                currentScore += alist.get(1);
                alist.remove(2);
                numTile--;
            }
        } else if (alist.size() == 4) {
            if (alist.get(0).equals(alist.get(1))) {
                alist.set(0, alist.get(1) * 2);
                currentScore += alist.get(0);
                alist.remove(1);
                numTile--;
                if (alist.get(1).equals(alist.get(2))) {
                    alist.set(1, alist.get(2) * 2);
                    currentScore += alist.get(1);
                    alist.remove(2);
                    numTile--;
                }
            } else if (alist.get(1).equals(alist.get(2))) {
                alist.set(1, alist.get(2) * 2);
                currentScore += alist.get(1);
                alist.remove(2);
                numTile--;
            } else if (alist.get(2).equals(alist.get(3))) {
                alist.set(2, alist.get(3) * 2);
                currentScore += alist.get(2);
                alist.remove(3);
                numTile--;
            }
        }
        for (int i = 0; i < alist.size(); i++)
            if (alist.get(i) != line[i]) hasMoved = true;
        if (hasMoved)
            alist.add(1);
        else
            alist.add(0);
        return alist;
    }

    // check if Movable
    public boolean isMovable() {
        Board bGame = new Board(this);
        for (int i = 0; i < 4; i++)
            if (bGame.move(i)) return true;
        return false;
    }

    public int getScore() {
        return currentScore;
    }

    public int getTile(int i, int j) {
        return tile[i][j];
    }
    
    public int getTileNumber(){
        return numTile;
    }
    

    public void print() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++)
                System.out.print(tile[i][j]);
            System.out.println("\n");
        }
    }

}
