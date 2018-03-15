package org.academiadecodigo.haltistas.halflifeminus3.BackGround;


import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CollidableBackGround extends GameBackGround {

    public void init (Picture[][] grid, int totalCol, int totalRow) {

        createHouse(10,10, grid);
        createLake(grid,25, 20, 5 );
        createWall(totalCol, totalRow, grid);
        createHouse(35,35, grid);
        createLake(grid, 5, 33, 5 );
    }

    public void createHouse(int col, int row, Picture[][] grid) {

        for (int i = col; i < col + 5; i++) {
            for (int j = row; j < row + 5; j++) {

                if(j < row + 2){

                    grid[i][j] =new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/roof_tiles.png");

                    continue;
                }

                grid[i][j] = new Picture(i * Grid.CELLSIZE + Grid.PADDING, j * Grid.CELLSIZE + Grid.PADDING, "assets/house_tiles.png");

            }
        }

    }

    public void createWall(int totalCol, int totalRow, Picture[][] grid) {
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
