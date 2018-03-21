package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Player {

    private int col;
    private int row;

    private int logicalCol;
    private int logicalRow;

    private Picture picture;
    private LinkedList<Bullet> bullets;
    private static final int MAX_BULLETS = 5;
    private Bullet[] bulletsList;

    public Player() {


    }

    public void init(int colP, int rowP) {
        this.col = colP;
        this.row = rowP;

        this.picture = new Picture(col * Grid.CELLSIZE + Grid.PADDING,
                row * Grid.CELLSIZE + Grid.PADDING, "assets/player_sprite3.png");
        this.bullets = new LinkedList<>();
        this.bulletsList = new Bullet[MAX_BULLETS];

        picture.draw();
        this.logicalCol = col;
        this.logicalRow = row;
    }

    public void initEnemyPlayer() {

        int initialCol = logicalCol * Grid.CELLSIZE + Grid.PADDING;
        int initialRow = logicalRow * Grid.CELLSIZE + Grid.PADDING;
        this.picture = new Picture(initialCol, initialRow, "assets/player_sprite3.png");
    }


    public void setPlayerCol(int col) {
        this.logicalCol = col;
    }

    public void setPlayerRow(int row) {
        this.logicalRow = row;
    }

    public int getLogicalCol() {
        return logicalCol;
    }

    public int getLogicalRow() {
        return logicalRow;
    }

    public void setLogicalCol(int logicCol) {

        this.logicalCol += logicCol;
    }

    public void setLogicalRow(int logicRow) {

        this.logicalRow += logicRow;
    }

    public void playerMoveUp() {

        int limit = Camera.CAMERA_HEIGHT / 2 - 1;

        if (logicalRow <= limit) {
            return;
        }

        setLogicalRow(-1);

    }


    public void playerMoveDown() {

        int limit = Grid.MAX_ROW - Camera.CAMERA_HEIGHT / 2 - 1;

        if (logicalRow >= limit) {
            return;
        }

        setLogicalRow(1);

    }

    public void playerMoveRight() {

        int limit = Grid.MAX_COL - Camera.CAMERA_WIDTH / 2 - 1;

        if (logicalCol >= limit) {
            return;
        }

        setLogicalCol(1);

    }

    public void playerMoveLeft() {

        int limit = Camera.CAMERA_WIDTH / 2 - 1;

        if (logicalCol <= limit) {
            return;
        }

        setLogicalCol(-1);

    }

    public void move(int colMove, int rowMove) {

        picture.translate(colMove * Grid.CELLSIZE, rowMove * Grid.CELLSIZE);
    }

    public void shoot(double finalX, double finalY) {


        double inicialX = (col + 1) * Grid.CELLSIZE;
        double inicialY = (row + 1) * Grid.CELLSIZE;

        for (int i = 0; i < MAX_BULLETS; i++) {

            if (bulletsList[i] == null) {
                bulletsList[i] = new Bullet(inicialX, inicialY, finalX, finalY);
                break;
            }
        }


    }

    public void bulletsMove() throws InterruptedException {

        if (bulletsList[0] == null) {
            return;
        }


        for (int i = 0; i < 1; i++) {
            Thread.sleep(30);
            bulletsList[i].move();

            if (!bulletInView(i)) {
                bulletsList[i].delete();
                bulletsList[i] = null;
            }

        }
    }

    public boolean bulletInView(int index) {

        int right = (col + Camera.CAMERA_WIDTH / 2) * Grid.CELLSIZE + Grid.PADDING;
        int left = Grid.PADDING;
        int top = Grid.PADDING;
        int bottom = (row + Camera.CAMERA_HEIGHT / 2) * Grid.CELLSIZE + Grid.PADDING;
        double bulletX = bulletsList[index].getX();
        double bulletY = bulletsList[index].getY();

        return left < bulletX && right > bulletX && top < bulletY && bottom > bulletY;
    }

    public void debug() {
        picture.delete();
        picture.draw();
    }

    public void delete() {
        picture.delete();
    }

    public void draw() {
        picture.draw();
    }


    public int colToX() {
        return logicalCol * Grid.CELLSIZE + Grid.PADDING;
    }

    public int rowToY() {
        return logicalRow * Grid.CELLSIZE + Grid.PADDING;
    }

    public int playerWidth() {

        return (logicalCol + 2) * Grid.CELLSIZE + Grid.PADDING;

    }

    public int playerHeigth() {

        return (logicalRow + 2) * Grid.CELLSIZE + Grid.PADDING;

    }


}
