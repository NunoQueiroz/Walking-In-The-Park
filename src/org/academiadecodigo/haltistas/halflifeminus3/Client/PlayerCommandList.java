package org.academiadecodigo.haltistas.halflifeminus3.Client;

public class PlayerCommandList {



    public static String playerPosition(int playerNum, int col, int row){

        return "M " + playerNum + " " + col + " " + row;


    }


    public static String initialBulletPosition(int x, int y){


        return "B " + x + " " + y;

    }

    public static String finalBulletPostion(int x, int y){

        return "B " + x + " " + y;

    }




}
