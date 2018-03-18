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


    public Controls(Camera camera) {

        this.camera = camera;
    }

    public void init() {

        Keyboard k = new Keyboard(this);
        Mouse m = new Mouse(this);

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

        m.addEventListener(MouseEventType.MOUSE_CLICKED);
        m.addEventListener(MouseEventType.MOUSE_MOVED);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                camera.moveRight();
                break;
            case KeyboardEvent.KEY_LEFT:
                camera.moveLeft();
                break;
            case KeyboardEvent.KEY_UP:
                camera.moveUp();
                break;
            case KeyboardEvent.KEY_DOWN:
                camera.moveDown();
                break;
            default:
                System.out.println("JVM");
                break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {


    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        double finalX = mouseEvent.getX();
        double finalY = mouseEvent.getY();

        camera.getPlayer().shoot(finalX, finalY);

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
