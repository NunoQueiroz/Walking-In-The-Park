package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Client;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;
import org.academiadecodigo.haltistas.halflifeminus3.sound.Sound;

public class Game {

    private Grid grid;
    private Camera camera;
    private Controls controls;
    private Client client;
    private Player player;
    private Sound sound;


    public Game(String ip) {

        player = new Player();
        grid = new Grid();
        camera = new Camera(0, 0, grid, player);
        controls = new Controls(camera);
        client = new Client(9000, player, controls, camera);
        client.setHostName(ip);
        //sound = new Sound("assets/FeelsGood.wav");

    }


    public void start() {

        System.out.println("START");
        grid.init();
        camera.init();
        controls.init();
        client.start();

    }


}
