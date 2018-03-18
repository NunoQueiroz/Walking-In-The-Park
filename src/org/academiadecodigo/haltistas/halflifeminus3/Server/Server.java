package org.academiadecodigo.haltistas.halflifeminus3.Server;

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

    public Server(int portNumber) {
        this.portNumber = portNumber;
        this.clientHandlers = new LinkedList<>();
    }

    public void start() {

        ClientHandler clientHandler = null;

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        try {


            serverSocket = new ServerSocket(portNumber);

            while (true) {

                Socket socket = serverSocket.accept();

                clientHandler = new ClientHandler(this, socket);

                clientHandlers.add(clientHandler);

                cachedPool.submit(clientHandler);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void broadcast(String message, ClientHandler clientHandler) {

        for (ClientHandler ch : clientHandlers) {

            if (ch == clientHandler) {
                continue;
            }
            ch.sendMessage(message);
        }

    }

}
