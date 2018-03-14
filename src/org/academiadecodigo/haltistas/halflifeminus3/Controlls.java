package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controlls implements KeyboardHandler {

    private KeyboardEvent right;
    private KeyboardEvent left;
    private KeyboardEvent down;
    private KeyboardEvent up;
    private Keyboard k;
    private Grid mapGrid;

    public Controlls(Grid mapGrid) {
        this.mapGrid = mapGrid;
    }

    public void start() {
        k = new Keyboard(this);

        right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(right);

        left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(left);

        up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(up);

        down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(down);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.equals(right)) {
        }

        if (keyboardEvent.equals(left)) {
        }

        if (keyboardEvent.equals(up)) {
        }

        if (keyboardEvent.equals(down)) {
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}
