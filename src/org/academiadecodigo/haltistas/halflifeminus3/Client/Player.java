package org.academiadecodigo.haltistas.halflifeminus3.Client;

public class Player {


    private int col;
    private int row;








    public void setCol(int col) {

        this.col += col;

    }


    public void setRow(int row) {

        this.row += row;

    }

    public int getCol(){

        return col;
    }

    public int getRow(){

        return row;

    }

    /*public int getMinRow(){
        return row - 2*CELLSIZE;
    }

    public int getMinCol(){
        return col - CELLSIZE;
    }*/


}
