package org.academiadecodigo.haltistas.halflifeminus3.Client;


import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {

    private double x;
    private double y;

    private double finalX;
    private double finalY;

    private static final int NUMBER_CYCLES = 30;

    private Picture bullet;
    private volatile boolean shoot = false;


    public Bullet(int col, int row) {
        this.x = Grid.PADDING + col * Grid.CELLSIZE;
        this.y = Grid.PADDING + row * Grid.CELLSIZE;


        bullet = new Picture(x, y, "assets/bullet.png");

    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setFinalX(double finalX) {
        this.finalX = finalX;
    }

    public void setFinalY(double finalY) {
        this.finalY = finalY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;

    }


    public void move() {

        int t = 0;
        double posXtoShoot = (finalX - x) / NUMBER_CYCLES;
        double posYtoShoot = (finalY - y) / NUMBER_CYCLES;

        while (t <= NUMBER_CYCLES) {
            try {
                Thread.sleep(NUMBER_CYCLES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            bullet.translate(posXtoShoot, posYtoShoot);
            t++;


            while (!shoot) {

            }

            //bullet.translate(jogador.getX() - jogador.getX(), jogador.getY() - jogador.getY());
            //shoot();

        }

    }


}
