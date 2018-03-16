package org.academiadecodigo.haltistas.halflifeminus3.Client;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private List<Bullet> bullets;
    private volatile boolean shoot;
    private int col;
    private int row;

    private static final int MAX_BULLETS = 12;


    public Player() {
        this.bullets = new LinkedList<>();


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

    /*public int getMinRow(){
        return row - 2*CELLSIZE;
    }

    public int getMinCol(){
        return col - CELLSIZE;
    }*/

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


}



