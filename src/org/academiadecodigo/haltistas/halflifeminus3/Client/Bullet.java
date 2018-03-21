package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {

    private double inicialX;
    private double inicialY;
    private double finalX;
    private double finalY;
    private static final int NUMBER_CICLES = 20;
    private Picture picture;

    public Bullet(double initialX, double initialY, double finalX, double finalY) {
        this.inicialX = initialX;
        this.inicialY = initialY;
        this.finalX = finalX;
        this.finalY = finalY;
        this.picture = new Picture(initialX, initialY, "assets/bullet.png");

    }

    public void move() {

        picture.draw();
        double translateX = (finalX - inicialX) / NUMBER_CICLES;
        double translateY = (finalY - inicialY) / NUMBER_CICLES;

        picture.translate(translateX, translateY);

    }

    public double getX() {
        return picture.getX();
    }

    public double getY() {
        return picture.getY();
    }

    public double getFinalX() {
        return finalX;
    }

    public double getFinalY() {
        return finalY;
    }

    public void delete() {
        picture.delete();
    }

    public void draw() {
        picture.draw();
    }
}
