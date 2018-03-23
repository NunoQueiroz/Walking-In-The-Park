package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Controls implements KeyboardHandler, MouseHandler {

    private Camera camera;
    private volatile boolean pressedKey;
    private volatile boolean shooted;
    private double finalX;
    private double finalY;

    public Controls(Camera camera) {

        this.camera = camera;
        this.pressedKey = false;
        this.shooted = false;

    }

    public void init() {

        Keyboard k = new Keyboard(this);
        Mouse m = new Mouse(this);

        int[] keys = {
                KeyboardEvent.KEY_RIGHT,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_LEFT,
                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_W,
                KeyboardEvent.KEY_A,
                KeyboardEvent.KEY_S,
                KeyboardEvent.KEY_D,
        };

        for (int key : keys) {

            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(event);
        }

        m.addEventListener(MouseEventType.MOUSE_CLICKED);
        m.addEventListener(MouseEventType.MOUSE_MOVED);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                camera.moveRight();
                camera.getPlayer().playerMoveRight();
                pressedKey = true;
                break;
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_A:
                camera.moveLeft();
                camera.getPlayer().playerMoveLeft();
                pressedKey = true;
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                camera.moveUp();
                camera.getPlayer().playerMoveUp();
                pressedKey = true;
                break;
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_S:
                camera.moveDown();
                camera.getPlayer().playerMoveDown();
                pressedKey = true;
                break;
            default:
                System.out.println("JVM");
                break;
        }

        camera.showEnemiesInView();

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {


        finalX = mouseEvent.getX();
        finalY = mouseEvent.getY();
        pressedKey = true;
        shooted = true;
        camera.getPlayer().shoot(finalX, finalY);

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }


    public boolean isPressedKey() {
        return pressedKey;
    }


    public void resetPressedKey() {
        pressedKey = false;
    }

    public boolean shooted() {
        return shooted;
    }

    public void resetShooted() {
        shooted = false;
    }

    public double getFinalX() {
        return finalX;
    }

    public double getFinalY() {
        return finalY;
    }
}
