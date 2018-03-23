package org.academiadecodigo.haltistas.halflifeminus3.Server;

import org.academiadecodigo.haltistas.halflifeminus3.Client.Bullet;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int portNumber;
    private List<ClientHandler> clientHandlers;
    private ServerSocket serverSocket = null;
    private List<Player> playerList;
    private List<Bullet> bulletList;

    public Server(int portNumber) {
        this.portNumber = portNumber;
        this.clientHandlers = new LinkedList<>();
        playerList = new LinkedList<>();
        bulletList = new LinkedList<>();
    }

    public void start() {

        ClientHandler clientHandler = null;

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        try {

            int count = 0;

            serverSocket = new ServerSocket(portNumber);

            while (true) {

                Socket socket = serverSocket.accept();

                clientHandler = new ClientHandler(this, socket);

                clientHandlers.add(clientHandler);
                clientHandler.sendMessage("YOUARENUMBER " + count);
                broadcast("M " + count + " 11 6");

                count++;
                cachedPool.execute(clientHandler);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void broadcast(String message) {

        for (ClientHandler ch : clientHandlers) {

            ch.sendMessage(message);
        }

    }

    public List<Player> getPlayerList(){

        return playerList;

    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }
}
