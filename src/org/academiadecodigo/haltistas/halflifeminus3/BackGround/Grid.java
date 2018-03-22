package org.academiadecodigo.haltistas.halflifeminus3.BackGround;

import javafx.scene.layout.Background;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;
import java.util.List;

public class Grid {

    public static final int CELLSIZE = 30;
    public static final int PADDING = 10;

    public static final int MAX_COL = 50;
    public static final int MAX_ROW = 50;

    private GameBackground[][] grid;
    private List<Player> enemies;

    public Grid() {
        grid = new GameBackground[MAX_COL][MAX_ROW];
        enemies = new LinkedList<>();
    }

    public void init() {
        for (int col = 0; col < MAX_COL; col++) {
            for (int row = 0; row < MAX_ROW; row++) {
                grid[col][row] = new UncollidableBackground(col, row, "assets/grass_tile.jpg");
            }

        }
        createTrees();
        createFrame();
        createLake(grid, 35, 15, 3);
        createLake(grid, 20, 20, 3);
        createHouse(30, 15, grid);
        createHouse(25, 30, grid);
        createHouse(15, 10, grid);
    }

    public void createFrame() {
        int initialColFrame = Camera.CAMERA_WIDTH / 2 - 9;
        int finalColFrame = Camera.CAMERA_WIDTH / 2 - 8;
        int initialRowFrame = Camera.CAMERA_HEIGHT / 2 - 5;
        int finalRowFrame = Camera.CAMERA_HEIGHT / 2 - 4;

        for (int col = 0; col < MAX_COL; col++) {

            for (int row = 0; row < MAX_ROW; row++) {
                if ((col > initialColFrame && col < MAX_COL - finalColFrame) &&
                        (row > initialRowFrame && row < MAX_ROW - finalRowFrame)) {
                    continue;
                }

                grid[col][row] = new UncollidableBackground(col, row, "assets/wall_tile.png");
            }
        }


    }

    public void createTrees() {
        int initialColTrees = Camera.CAMERA_WIDTH / 2 - 2;
        int finalColTrees = Camera.CAMERA_WIDTH / 2 - 1;
        int initialRowTrees = Camera.CAMERA_HEIGHT / 2 - 2;
        int finalRowFTrees = Camera.CAMERA_HEIGHT / 2 - 1;

        for (int col = 0; col < MAX_COL; col++) {
            for (int row = 0; row < MAX_ROW; row++) {

                if ((col > initialColTrees && col < MAX_COL - finalColTrees) &&
                        (row > initialRowTrees && row < MAX_ROW - finalRowFTrees)) {
                    continue;
                }
                grid[col][row] = new UncollidableBackground(col, row, "assets/tree.png");

            }
        }
    }


    public void createLake(GameBackground[][] grid, int initialRow, int initialCol, int numberOfCols) {

        int row = initialRow;
        int interval = 1;

        for (int i = initialCol; i < initialCol + numberOfCols; i++) {
            for (int j = row; j < initialRow + interval; j++) {

                grid[i][j] = new GameBackground(i, j, "assets/water_tile.png");
            }

            interval += 1;
            row -= 1;

        }

        row = initialRow;
        interval = 1;

        for (int i = initialCol + 2 * numberOfCols; i > initialCol + numberOfCols - 1; i--) {
            for (int j = row; j < initialRow + interval; j++) {

                grid[i][j] = new GameBackground(i, j, "assets/water_tile.png");
            }

            interval += 1;
            row -= 1;

        }

    }


    public void createHouse(int col, int row, GameBackground[][] grid) {

        for (int i = col; i < col + 5; i++) {
            for (int j = row; j < row + 5; j++) {

                if (j < row + 2) {

                    grid[i][j] = new GameBackground(i, j, "assets/roof_tiles.png");

                    continue;
                }

                grid[i][j] = new GameBackground(i, j, "assets/house_tiles.png");

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

        for (Player enemy : enemies) {
            enemy.move(x, y);
        }
    }

    public void addEnemie(Player enemie) {
        enemies.add(enemie);
    }

    public List<Player> getEnemies() {
        return enemies;
    }
}
