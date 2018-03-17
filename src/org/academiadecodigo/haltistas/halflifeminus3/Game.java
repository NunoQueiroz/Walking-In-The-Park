package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;

public class Game {

    private Grid grid;
    private Camera camera;
    private Controls controls;

    public Game() {
        grid = new Grid();
        camera = new Camera(0,0,grid);
        controls = new Controls(camera);

    }

    public void start() {
        grid.init();
        camera.init();
        controls.init();


    }
}
