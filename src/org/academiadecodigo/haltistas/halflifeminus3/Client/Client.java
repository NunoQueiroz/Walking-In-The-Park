package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.haltistas.halflifeminus3.Controller;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {

        Player player = new Player();
        Client client = new Client(9090, player, 0);
        client.start();

    }


    private Socket connection;
    private Player player;
    private int playerNumber;
    private Controller controller;
    private volatile int col;
    private volatile int row;
    private Bullet bullet;


    public Client(int port, Player player, int playerNumber) {

        try {
            this.controller = new Controller(player,bullet);
            this.playerNumber = playerNumber;
            this.player = player;
            connection = new Socket("localhost", port);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

    public void start() {

        try {

            controller.move();

            ExecutorService cashedPoll = Executors.newCachedThreadPool();

            cashedPoll.submit(new ReceiveInfo());

            PrintWriter out = new PrintWriter(connection.getOutputStream());

            while (true) {

                if (controller.getClicked()) {

                    col = player.getCol();
                    row = player.getRow();

                    out.println(PlayerCommandList.playerPosition(playerNumber, col, row));

                    out.flush();

                    controller.reset();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public class ReceiveInfo implements Runnable {

        public String receive() throws IOException {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String mess = in.readLine();
            return mess;

        }


        @Override
        public void run() {


            try {

                while (true) {


                    String messageReceived = receive();
                    System.out.println(messageReceived);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
