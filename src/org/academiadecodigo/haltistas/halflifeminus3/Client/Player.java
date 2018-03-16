package org.academiadecodigo.haltistas.halflifeminus3.Client;

import java.util.LinkedList;
import java.util.List;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private List<Bullet> bullets;
    private volatile boolean shoot;
    private int col;
    private int row;
    private Picture picture;

    public Player (int col, int row) {
        this.col = col;
        this.row = row;
        this.bullets = new LinkedList<>();
    }

    private static final int MAX_BULLETS = 12;

    public void init () {
        this.picture = new Picture (col * Grid.CELLSIZE + Grid.PADDING, row * Grid.CELLSIZE + Grid.PADDING, "assets/roof_tiles.png");
        picture.draw();
    }

    public void translate (int moveCol, int moveRow) {

        picture.translate(moveCol * Grid.CELLSIZE, moveRow * Grid.CELLSIZE);
    }

    public void delete(){

        this.picture.delete();

    }

    public void draw(){

        this.picture.draw();

    }

    public void setCol(int col) {

        this.col += col;
    }


    public void setRow(int row) {

        this.row += row;

    }

    public int getCol() {

        return col;
    }

    public int getRow() {

        return row;

    }

    public void createBullet() {

        if (bullets.size() < MAX_BULLETS) {

            bullets.add(new Bullet(col, row));
        }


    }


    public void shoot() {


        if (bullets.isEmpty()) {
            return;
        }


    }


    @Override
    public String toString() {
        return "Player{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}



