package org.academiadecodigo.haltistas.halflifeminus3.Server;

import java.util.Timer;
import java.util.TimerTask;

public class EventCoordinator {

    private Timer eventScheduler;
    private Server server;

    public EventCoordinator(Server server) {

        this.server = server;
        eventScheduler = new Timer();

    }

    public void eventScheduler() {

        eventScheduler.schedule(new Schedueler(), 10000, 10000);


    }


    private class Schedueler extends TimerTask {


        @Override
        public void run() {

            synchronized (this) {

                server.start();

            }


        }
    }


}
