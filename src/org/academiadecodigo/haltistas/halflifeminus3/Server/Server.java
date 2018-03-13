package org.academiadecodigo.haltistas.halflifeminus3.Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        Server server = new Server(9090);

        server.start();

    }


    private ServerSocket serversocket;
    private List<ClientHandler> clientHandlerList;


    public Server(int port) {

        try {
            serversocket = new ServerSocket(port);
            clientHandlerList = new LinkedList<>();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    public void start() {

        try {

            ExecutorService cashedPool = Executors.newCachedThreadPool();

            while (true) {

                Socket clientSocket = serversocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                clientHandlerList.add(clientHandler);
                cashedPool.submit(clientHandler);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void broadcast(String message) throws IOException {

        for (int i = 0; i < clientHandlerList.size(); i++) {

            clientHandlerList.get(i).send(message);

        }


    }


    private class ClientHandler implements Runnable {

        private Socket connection;


        private ClientHandler(Socket connection) {

            this.connection = connection;

        }



        @Override
        public void run() {

            try {

                while (true) {

                    synchronized (clientHandlerList) {

                        String message = messageReceived();
                        broadcast(message);
                    }

                }


            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                close();
            }

        }


        private void send(String message) throws IOException {

            PrintWriter serverMessage = new PrintWriter(connection.getOutputStream(), true);
            serverMessage.println(message);

        }

        private String messageReceived() throws IOException {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in.readLine();

        }

        private void close() {

            synchronized (clientHandlerList) {
                try {

                    connection.close();
                    clientHandlerList.remove(this);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }


}
