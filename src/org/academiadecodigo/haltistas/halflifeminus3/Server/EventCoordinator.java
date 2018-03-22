package org.academiadecodigo.haltistas.halflifeminus3.Server;

import org.academiadecodigo.haltistas.halflifeminus3.Client.Bullet;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;

import java.util.Timer;
import java.util.TimerTask;

public class EventCoordinator {

    private Timer gameCycle;
    private Server server;
    private ClientHandler clientHandler;


    public EventCoordinator(Server server, ClientHandler clientHandler) {

        gameCycle = new Timer();
        this.server = server;
        this.clientHandler = clientHandler;


    }

    public void events() {

        gameCycle.schedule(new Collision(), 60, 60);

    }


    public class Collision extends TimerTask {


        @Override
        public void run() {

            int playerNum = 0;
            for (Player player : server.getPlayerList()) {
                for (Bullet bullet : server.getBulletList()) {

                    bullet.move();

                    if ((player.colToX() <= bullet.getX() && player.playerWidth() >= bullet.getX())
                            && player.rowToY() <= bullet.getY() && player.playerHeight() >= bullet.getY()) {

                        server.broadcast(playerNum + " hit");

                    }

                }
                playerNum++;
            }

        }


    }


}
