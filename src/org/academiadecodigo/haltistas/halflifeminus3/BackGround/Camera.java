package org.academiadecodigo.haltistas.halflifeminus3.BackGround;

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
                System.out.println("drawing " + col + " " + row);
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
            grid.getGrid()[backCol + CAMERA_WIDTH][row].draw();

        }

        grid.translateGrid(-1, 0);
        player.debug();
        backCol++;

    }

    public void moveLeft() {


        if (backCol <= 0) {
            return;

        }

        for (int row = topRow; row < topRow + CAMERA_HEIGHT; row++) {
            grid.getGrid()[backCol + CAMERA_WIDTH - 1][row].delete();
            grid.getGrid()[backCol - 1][row].draw();

        }

        grid.translateGrid(1, 0);
        player.debug();
        backCol--;

    }

    public void moveUp() {
        if (topRow <= 0) {
            return;

        }


        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            grid.getGrid()[col][topRow + CAMERA_HEIGHT - 1].delete();
            grid.getGrid()[col][topRow - 1].draw();

        }

        grid.translateGrid(0, 1);
        player.debug();
        topRow--;
    }


    public void moveDown() {
        if (topRow + CAMERA_HEIGHT >= Grid.MAX_ROW) {
            return;

        }

        for (int col = backCol; col < backCol + CAMERA_WIDTH; col++) {
            grid.getGrid()[col][topRow].delete();
            grid.getGrid()[col][topRow + CAMERA_HEIGHT].draw();

        }

        grid.translateGrid(0, -1);
        player.debug();
        topRow++;
    }

    public Player getPlayer () {
        return player;
    }

    public boolean isInView(Player enemy) {

        return (backCol + 1 < enemy.getLogicalCol() && backCol + CAMERA_WIDTH - 1 > enemy.getLogicalCol()
                && topRow + 1 < enemy.getLogicalRow() && topRow + CAMERA_HEIGHT - 1 > enemy.getLogicalRow());

    }


}
