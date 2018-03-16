package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.haltistas.halflifeminus3.Controller;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
        Player player = new Player(10, 10);

        Grid grid = new Grid(50, 50, 0, 0, player);

        grid.init();
        player.init();

        Client client = new Client(9090, player, 0, grid);
        client.start();

    }


    private Socket connection;
    private Player player;
    private int playerNumber;
    private Controller controller;
    private volatile int col;
    private volatile int row;
    private Bullet bullet;
    private HashMap<Integer, Player> alltheplayers;
    private Grid grid;

    public Client(int port, Player player, int playerNumber, Grid grid) {

        try {
            this.controller = new Controller(player,bullet,grid);
            this.grid = grid;
            this.playerNumber = playerNumber;
            this.player = player;
            connection = new Socket("localhost", port);
            this.alltheplayers = new HashMap<>();
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

        private BufferedReader serverMessage;

        public ReceiveInfo(){
            try {
                serverMessage = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }catch (IOException e){
                e.printStackTrace();
            }

        }


        public String receive() throws IOException {

            return serverMessage.readLine();

        }

        public void move(String message) {

            //System.out.println(message);
            String[] split = message.split(" ");

            if (split[0].equals("M")) {

                System.out.println("dafuq");
                Player enemy = alltheplayers.get(Integer.parseInt(split[1]));

                if (enemy == null) {
                    enemy = new Player(Integer.parseInt(split[2]), Integer.parseInt(split[3]));

                    alltheplayers.put(Integer.parseInt(split[1]), enemy);
                    enemy.init();
                    System.out.println(Integer.parseInt(split[2]) + " " + Integer.parseInt(split[3]));
                }

                int col = enemy.getCol();
                int row = enemy.getRow();
                //System.out.println("col " + col + " row " + row);

                enemy.setCol(Integer.parseInt(split[2]) - col);
                enemy.setRow(Integer.parseInt(split[3]) - row);

                enemy.translate(Integer.parseInt(split[2]) - col, Integer.parseInt(split[3]) - row);

                if (!grid.isInView(enemy)) {
                    System.out.println("delete");
                    enemy.delete();
                } else {
                    System.out.println("show");
                    enemy.draw();
                }
            }
        }


        @Override
        public void run() {


            try {

                while (true) {


                    String messageReceived = receive();
                    move(messageReceived);
                    System.out.println(messageReceived);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
