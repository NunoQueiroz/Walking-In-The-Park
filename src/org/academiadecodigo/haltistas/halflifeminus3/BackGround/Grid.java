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
        createTrees();
        createFrame();
    }

    public void createFrame() {
        int inicialColFrame = Camera.CAMERA_WIDTH / 2 - 9;
        int finalColFrame = Camera.CAMERA_WIDTH / 2 - 8;
        int inicialRowFrame = Camera.CAMERA_HEIGHT / 2 - 5;
        int finalRowFrame = Camera.CAMERA_HEIGHT / 2 - 4;

        for (int col = 0; col < MAX_COL; col++) {

            for (int row = 0; row < MAX_ROW; row++) {
                if ((col > inicialColFrame && col < MAX_COL - finalColFrame) &&
                        (row > inicialRowFrame && row < MAX_ROW - finalRowFrame)) {
                    continue;
                }

                grid[col][row] = new UncollidableBackground(col, row, "assets/wall_tile.png");
            }
        }


    }

    public void createTrees() {
        int inicalColTrees = Camera.CAMERA_WIDTH / 2 -2;
        int finalColTrees = Camera.CAMERA_WIDTH / 2 - 1;
        int inicialRowTrees = Camera.CAMERA_HEIGHT / 2 - 2;
        int finalRowFTrees = Camera.CAMERA_HEIGHT / 2 - 1;

        for (int col = 0; col < MAX_COL; col++) {
            for (int row = 0; row < MAX_ROW; row++) {

                if ((col > inicalColTrees && col < MAX_COL - finalColTrees) &&
                        (row > inicialRowTrees && row < MAX_ROW - finalRowFTrees)) {
                    continue;
                }
                grid[col][row] = new UncollidableBackground(col, row, "assets/tree1.png");

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
