package org.academiadecodigo.haltistas.halflifeminus3.BackGround;

import org.academiadecodigo.haltistas.halflifeminus3.Client.Bullet;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;

public class Camera {

    public static final int CAMERA_WIDTH = 24;
    public static final int CAMERA_HEIGHT = 14;

    private int backCol;
    private int topRow;
    private Grid grid;
    private Player player;


    public Camera(int backCol, int topRow, Grid grid, Player player) {
        this.backCol = backCol;
        this.topRow = topRow;
        this.grid = grid;
        this.player = player;

    }

    public void init() {

        int colP = (backCol + Camera.CAMERA_WIDTH) / 2 - 1;
        int rowP = (topRow + Camera.CAMERA_HEIGHT) / 2 - 1;

        grid.translateGrid(-backCol, -topRow);

        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            for (int row = topRow; row < topRow + CAMERA_HEIGHT; row++) {
                grid.getGrid()[col][row].draw();

            }

        }

        player.init(colP, rowP);
    }

    public void moveRight() {


        if (backCol + CAMERA_WIDTH >= Grid.MAX_COL) {
            return;

        }


        for (int row = topRow; row < topRow + CAMERA_HEIGHT; row++) {
            grid.getGrid()[backCol][row].delete();
        }

        grid.translateGrid(-1, 0);

        for (int row = topRow; row < topRow + CAMERA_HEIGHT; row++) {
            grid.getGrid()[backCol + CAMERA_WIDTH][row].draw();
        }

        player.debug();
        backCol++;

    }

    public void moveLeft() {


        if (backCol <= 0) {
            return;

        }

        for (int row = topRow; row < topRow + CAMERA_HEIGHT; row++) {
            grid.getGrid()[backCol + CAMERA_WIDTH - 1][row].delete();
        }

        grid.translateGrid(1, 0);

        for (int row = topRow; row < topRow + CAMERA_HEIGHT; row++) {
            grid.getGrid()[backCol - 1][row].draw();
        }

        player.debug();
        backCol--;

    }

    public void moveUp() {
        if (topRow <= 0) {
            return;

        }


        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            grid.getGrid()[col][topRow + CAMERA_HEIGHT - 1].delete();
        }

        grid.translateGrid(0, 1);

        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            grid.getGrid()[col][topRow - 1].draw();
        }


        player.debug();
        topRow--;
    }


    public void moveDown() {
        if (topRow + CAMERA_HEIGHT >= Grid.MAX_ROW) {
            return;

        }

        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            grid.getGrid()[col][topRow].delete();


        }

        grid.translateGrid(0, -1);

        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            grid.getGrid()[col][topRow + CAMERA_HEIGHT].draw();
        }


        player.debug();
        topRow++;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isInView(Player enemy) {

        return (backCol + 1 < enemy.getLogicalCol() && backCol + CAMERA_WIDTH - 1 > enemy.getLogicalCol()
                && topRow + 1 < enemy.getLogicalRow() && topRow + CAMERA_HEIGHT - 1 > enemy.getLogicalRow());

    }


    public boolean bulletIsInView(Bullet bullet) {
        int backColToX = (backCol + 1) * Grid.CELLSIZE + Grid.PADDING;
        int fronColToX = (backCol + CAMERA_WIDTH - 1) * Grid.CELLSIZE + Grid.PADDING;
        int topRowToY = (topRow + 1) * Grid.CELLSIZE + Grid.PADDING;
        int botRowToY = (topRowToY + CAMERA_HEIGHT - 1) * Grid.CELLSIZE + Grid.PADDING;


        return (backColToX < bullet.getX() && fronColToX > bullet.getX()
                && topRowToY < bullet.getY() && botRowToY > bullet.getY());
    }
}
