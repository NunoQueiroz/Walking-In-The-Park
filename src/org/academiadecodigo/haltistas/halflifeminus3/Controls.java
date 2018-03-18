package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controls implements KeyboardHandler {

    private Camera camera;


    public Controls(Camera camera) {

        this.camera = camera;
    }

    public void init() {

        Keyboard k = new Keyboard(this);

        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_RIGHT);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event);

        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_LEFT);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event1);

        KeyboardEvent event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_UP);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event2);

        KeyboardEvent event3 = new KeyboardEvent();
        event3.setKey(KeyboardEvent.KEY_DOWN);
        event3.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event3);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                camera.moveRight();
                camera.getPlayer().setLogicalCol(1);
                break;
            case KeyboardEvent.KEY_LEFT:
                camera.moveLeft();
                camera.getPlayer().setLogicalCol(-1);
                break;
            case KeyboardEvent.KEY_UP:
                camera.moveUp();
                camera.getPlayer().setLogicalRow(-1);
                break;
            case KeyboardEvent.KEY_DOWN:
                camera.moveDown();
                camera.getPlayer().setLogicalRow(1);
                break;
            default:
                System.out.println("JVM");
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {


    }
}
