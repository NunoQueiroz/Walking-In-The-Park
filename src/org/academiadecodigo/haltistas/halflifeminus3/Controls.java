package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controls implements KeyboardHandler {

    private Player player;
    private volatile boolean clicked;
    private Grid grid;


    public Controls(Player player, Grid grid) {

        clicked = false;
        this.player = player;
        this.grid = grid;
    }


    public void move() {

        Keyboard k = new Keyboard(this);


        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_RIGHT);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event);

        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_LEFT);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event1);

        KeyboardEvent event4 = new KeyboardEvent();
        event4.setKey(KeyboardEvent.KEY_UP);
        event4.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event4);

        KeyboardEvent event5 = new KeyboardEvent();
        event5.setKey(KeyboardEvent.KEY_DOWN);
        event5.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event5);
    }

    public boolean getClicked() {

        return clicked;

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {


            case KeyboardEvent.KEY_RIGHT:
                clicked = true;
                player.setCol(1);
                grid.moveRight();
                break;

            case KeyboardEvent.KEY_LEFT:
                clicked = true;
                player.setCol(-1);
                grid.moveLeft();
                break;

            case KeyboardEvent.KEY_UP:
                clicked = true;
                player.setRow(-1);
                grid.moveUp();
                break;

            case KeyboardEvent.KEY_DOWN:
                clicked = true;
                player.setRow(1);
                grid.moveDown();
                break;

            default:

                System.out.println("Deu erro no teclado");
                break;

        }

    }

    public void reset() {
        clicked = false;
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
