package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {

    private double inicialX;
    private double inicialY;
    private double finalX;
    private double finalY;
    private int numberCicles;
    private Picture picture;

    public Bullet(double initialX, double initialY, double finalX, double finalY) {
        this.inicialX = initialX;
        this.inicialY = initialY;
        this.finalX = finalX;
        this.finalY = finalY;


    }

    public void bulletInit() {
        picture = new Picture(inicialX, inicialY, "assets/bone_2.png");
    }

    public int numberOfCicles() {

        if (finalX < inicialX + 100 && finalX > inicialX - 100 && finalY < finalY + 100 && finalY > finalY - 100) {
            return numberCicles = 1;
        }

        if (finalX < inicialX + 200 && finalX > inicialX - 200 && finalY < finalY + 200 && finalY > finalY - 200) {
            return numberCicles = 6;
        }

        if (finalX < inicialX + 300 && finalX > inicialX - 300 && finalY < finalY + 300 && finalY > finalY - 300) {
            return numberCicles = 8;
        }

        if (finalX < inicialX + 400 && finalX > inicialX - 400 && finalY < finalY + 400 && finalY > finalY - 400) {
            return numberCicles = 20;
        }

        return 0;
    }





    public void move() {

        numberOfCicles();

        double translateX = (finalX - inicialX) / numberCicles;
        double translateY = (finalY - inicialY) / numberCicles;

        picture.translate(translateX, translateY);

    }

    public double getX() {
        return picture.getX();
    }

    public double getY() {
        return picture.getY();
    }


    public void delete() {
        picture.delete();
    }

    public void draw() {
        picture.draw();
    }

}
