package org.academiadecodigo.haltistas.halflifeminus3.Client;

public class PlayerCommandList {

    public static String player (int playerNumber, int col, int row) {

        return "M " + playerNumber + " " + col + " " + row;

    }

    public static String bullet (double initialX, double initialY, double finalX, double finalY) {

        return "B " + initialX + " " + initialY + " " + finalX + " " + finalY;
    }
}
