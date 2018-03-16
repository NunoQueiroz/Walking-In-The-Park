package org.academiadecodigo.haltistas.halflifeminus3;

import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Controlls implements MouseHandler, KeyboardHandler {

    private Player player;
    private volatile boolean clicked;

    public Controlls(Player player) {

        clicked = false;
        this.player = player;
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

    private Rectangle jogador;
    private Picture bullet;
    private volatile boolean shoot;

    private int numberOfCycles = 30;


    private double posXtoShoot;
    private double posYtoShoot;

    @Override
    public void mouseClicked(MouseEvent event){

        shoot = true;

        double posXmouseCursor = event.getX();
        double posYmouseCursor = event.getY();

        posXtoShoot = (posXmouseCursor-jogador.getX())/numberOfCycles;
        posYtoShoot = (posYmouseCursor-jogador.getY())/numberOfCycles;

    }

    public void shoot() {
        int t = 0;

        while (t <= numberOfCycles) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            bullet.translate(posXtoShoot, posYtoShoot);
            t++;

        }


        while (!shoot) {

        }

        bullet.translate(jogador.getX() - jogador.getX(), jogador.getY() - jogador.getY());
        shoot();
    }


    @Override
    public void mouseMoved(MouseEvent event){

    }


}
