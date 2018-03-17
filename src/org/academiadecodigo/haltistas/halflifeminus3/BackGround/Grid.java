package org.academiadecodigo.haltistas.halflifeminus3.BackGround;

public class Grid {

    public static final int CELLSIZE = 30;
    public static final int PADDING = 10;

    public static final int MAX_COL = 50;
    public static final int MAX_ROW = 50;

    private GameBackground[][] grid;

    public Grid() {
        grid = new GameBackground[MAX_COL][MAX_ROW];

    }

    public void init() {
        for (int col = 0; col < MAX_COL; col++) {
            for (int row = 0; row < MAX_ROW; row++) {
                grid[col][row] = new UncollidableBackground(col, row, "assets/grass_tile.jpg");
            }

        }
    }

    public GameBackground[][] getGrid() {
        return grid;
    }

    public void translateGrid(int x, int y) {
        for (int col = 0; col < MAX_COL; col++) {
            for (int row = 0; row < MAX_ROW; row++) {
                grid[col][row].translateTile(x * CELLSIZE, y * CELLSIZE);

            }

        }

    }
}
