package org.academiadecodigo.haltistas.halflifeminus3.Server;


import org.academiadecodigo.haltistas.halflifeminus3.Client.Bullet;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;

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

        //server.start();

        EventCoordinator eventCoordinator = new EventCoordinator(server);
        eventCoordinator.eventScheduler();

    }


    private ServerSocket serversocket;
    private List<ClientHandler> clientHandlerList;
    private List<Player> playerList;
    private List<Bullet> bulletList;

    public Server(int port) {

        try {
            playerList = new LinkedList<>();
            bulletList = new LinkedList<>();
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

    public List<ClientHandler> getClientHandlerList() {
        return clientHandlerList;
    }

    private class ClientHandler implements Runnable {

        private Socket connection;
        private String messageReceived;


        private ClientHandler(Socket connection) {

            this.connection = connection;
            playerList.add(new Player());

        }


        @Override
        public void run() {

            try {

                while (true) {

                    messageReceived = messageReceived();

                    System.out.println(messageReceived);

                    String decodedMessage = stringDecoder(messageReceived);

                    broadcast(decodedMessage);


                }


            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                close();
            }

        }

        private void broadcast(String message) throws IOException {

            for (int i = 0; i < clientHandlerList.size(); i++) {

                /*if (clientHandlerList.get(i).equals(this)) {
                    continue;
                }*/

                clientHandlerList.get(i).send(message);

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

            try {

                connection.close();
                clientHandlerList.remove(this);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        public String getMessageReceived() {

            return messageReceived;

        }


        public String stringDecoder(String messageReceive) {

            String[] splitedMessage = messageReceive.split(" +");

            switch (splitedMessage[0]) {

                case "M":

                    playerList.get(new Integer(splitedMessage[1])).setCol(new Integer(splitedMessage[2]));
                    playerList.get(new Integer(splitedMessage[1])).setRow(new Integer(splitedMessage[3]));

                    return messageReceive;


                case "B":

                    bulletList.add(new Bullet());
                    return playerBulletColision();

                default:

                    System.out.println("String decoder error");
                    break;

            }

            return null;

        }

        public String playerBulletColision() {

            if (playerList.size() == 0 && bulletList.size() == 0) {
                return "no players or bullets";
            }

            for (int i = 0; i < bulletList.size(); i++) {

                for (int j = 0; j < playerList.size(); i++) {

                    if (playerList.get(j).getCol() == bulletList.get(i).getX() ||
                            (playerList.get(j).getRow() <= bulletList.get(i).getY())) {

                        return "hit";

                    }
                }

            }

            return "no hit";
        }
    }
}
