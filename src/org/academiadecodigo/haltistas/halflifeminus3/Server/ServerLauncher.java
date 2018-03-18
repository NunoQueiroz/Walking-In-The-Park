package org.academiadecodigo.haltistas.halflifeminus3.Server;

public class ServerLauncher {

    public static void main(String[] args) {

        int portNumber = 9000;
        new Server(portNumber).start();

    }
}
