package org.academiadecodigo.haltistas.halflifeminus3.BackGround;

import com.sun.corba.se.impl.orbutil.graph.GraphImpl;
import org.academiadecodigo.haltistas.halflifeminus3.BackGround.GameBackGround;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CollidableBackGround extends GameBackGround {


    public void createHouse (Picture[][] grid, int col, int row) {

        for (int i = col; i < col + 5; i++) {
            for (int j = row; j < row + 5; j++) {

                if (j < row + 2) {

                    grid[i][j] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/roof_tiles.png");

                    continue;
                }

                grid[i][j] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/house_tiles.png");

            }
        }

    }

    public void createWall(Picture[][] grid, int totalCol, int totalRow) {
        for (int i = 0; i < totalCol; i++) {
            for (int j = 0; j < totalRow; j++) {
                if (i > 0 && i < totalCol - 1) {

                    grid[i][0] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, Grid.PADDING, "assets/wall_tile.png");
                    grid[i][totalRow - 2] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, (totalRow - 2) * Grid.CELLSIZE + Grid.PADDING, "assets/wall_tile.png");
                    continue;

                }

                grid[i][j] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/wall_tile.png");

            }
        }
    }

    public void createLake(Picture[][] grid, int initialRow, int initialCol, int numberOfCols) {

        int row = initialRow;
        int interval = 1;

        for (int i = initialCol; i < initialCol + numberOfCols; i++) {
            for (int j = row; j < initialRow + interval; j++) {

                grid[i][j] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/water_tile.png");
            }

            interval += 1;
            row -= 1;

        }

        row = initialRow;
        interval = 1;

        for (int i = initialCol + 2* numberOfCols; i > initialCol + numberOfCols - 1; i--) {
            for (int j = row; j < initialRow + interval; j++) {

                grid[i][j] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/water_tile.png");
            }

            interval += 1;
            row -= 1;

        }

    }



}
