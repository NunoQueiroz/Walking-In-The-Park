package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Client;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;

public class Game {

    private Grid grid;
    private Camera camera;
    private Controls controls;
    private Client client;
    private Player player;

    public Game() {

        player = new Player();
        grid = new Grid();
        camera = new Camera(0, 0, grid, player);
        controls = new Controls(camera);
        client = new Client("localhost", 9000, this);

    }

    public void start() {

        grid.init();
        camera.init();
        controls.init();
        client.start();

    }

}
