package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Bullet;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Controller implements MouseHandler, KeyboardHandler {

    private Player player;
    private volatile boolean clicked;
    private Bullet bullet;

    public Controller(Player player, Bullet bullet) {

        clicked = false;
        this.player = player;
        this.bullet = bullet;

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

        Mouse m = new Mouse(this);
        m.addEventListener(MouseEventType.MOUSE_CLICKED);
        m.addEventListener(MouseEventType.MOUSE_MOVED);
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
                break;

            case KeyboardEvent.KEY_LEFT:

                clicked = true;
                player.setCol(-1);
                break;

            case KeyboardEvent.KEY_UP:

                clicked = true;
                player.setRow(-1);
                break;

            case KeyboardEvent.KEY_DOWN:

                clicked = true;
                player.setRow(1);
                break;

            default:

                System.out.println("");
                break;

        }

    }

    public void reset()
    {
        clicked = false;
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    @Override
    public void mouseClicked(MouseEvent event) {

        bullet.setFinalX(event.getX());
        bullet.setFinalY(event.getY());

        player.createBullet();


    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }
}


