package org.academiadecodigo.haltistas.halflifeminus3.BackGround;

import org.academiadecodigo.haltistas.halflifeminus3.Game;
import org.academiadecodigo.haltistas.halflifeminus3.GameMenu;
import org.academiadecodigo.haltistas.halflifeminus3.sound.Sound;

public class Main {
    public static void main(String[] args) {

        GameMenu gameMenu = new GameMenu();
        Thread sound = new Thread(new Sound("/assets/FeelGood.wav"));

        sound.start();


        gameMenu.init();


    }
}