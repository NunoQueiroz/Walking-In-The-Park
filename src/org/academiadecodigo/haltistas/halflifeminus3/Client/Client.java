package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Grid;
import org.academiadecodigo.haltistas.halflifeminus3.Controls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Client {

    private Socket socket;
    private String hostName;
    private int portNumber;
    private PrintWriter printWriter;
    private Player player;
    private Controls controls;
    private Map<Integer, Player> playerList;
    private List<Bullet> bulletList;
    private Camera camera;

    public Client(String hostName, int portNumber, Player player, Controls controls, Camera camera) {

        this.hostName = hostName;
        this.portNumber = portNumber;
        this.player = player;
        this.controls = controls;
        this.playerList = new HashMap<>();
        this.bulletList = new LinkedList<>();
        this.camera = camera;

    }

    public void start() {

        try {

            socket = new Socket(hostName, portNumber);

            new Thread(new DataReceiver()).start();

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) {

                if (controls.isPressedKey()) {

                    int col = player.getLogicalCol();
                    int row = player.getLogicalRow();
                    String message = PlayerCommandList.player(0, col, row);


                    if (controls.shooted()) {
                        System.out.println("mandou mensagem");
                        double initialX = player.getLogicalCol() * Grid.CELLSIZE + Grid.PADDING;
                        double initialY = player.getLogicalRow() * Grid.CELLSIZE + Grid.PADDING;
                        double finalX = controls.getFinalX();
                        double finalY = controls.getFinalY();
                        String bulletMessage = PlayerCommandList.bullet(initialX, initialY, finalX, finalY);
                        printWriter.println(bulletMessage);

                        controls.resetShooted();
                    }


                    System.out.println(message);
                    printWriter.println(message);


                    controls.resetPressedKey();

                }

                player.bulletsMove();
                drawBullet();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public class DataReceiver implements Runnable {

        private BufferedReader bufferedReader;

        @Override
        public void run() {

            try {

                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                while (true) {

                    String message = bufferedReader.readLine();
                    addBullet(message);
                    moveEnemy(message);
                    System.out.println(message);

                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(socket);
            }

        }
    }

    private void close(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBullet() {

        synchronized (bulletList) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < bulletList.size(); i++) {

                if (camera.bulletIsInView(bulletList.get(i))) {
                    bulletList.get(i).draw();
                    bulletList.get(i).move();

                } else {
                    bulletList.get(i).delete();
                    bulletList.remove(i);
                }

            }
        }
    }

    private void addBullet(String bulletMessage) {

        synchronized (bulletList) {

            String[] bulletData = bulletMessage.split(" ");

            if (!bulletData[0].equals("B")) {
                return;
            }


            double initialX = Double.parseDouble(bulletData[1]);
            double initialY = Double.parseDouble(bulletData[2]);
            double finalX = Double.parseDouble(bulletData[3]);
            double finalY = Double.parseDouble(bulletData[4]);

            Bullet bullet = new Bullet(initialX, initialY, finalX, finalY);
            bulletList.add(bullet);
            bullet.bulletInit();
        }
    }

    private void moveEnemy(String message) {

        if (message.charAt(0) != 'M') {
            return;
        }


        String[] enemyPlayerData = message.split(" +");


        int playerNum = Integer.parseInt(enemyPlayerData[1]);
        System.out.println(playerNum);
        int newCol = Integer.parseInt(enemyPlayerData[2]);
        System.out.println(newCol);
        int newRow = Integer.parseInt(enemyPlayerData[3]);
        System.out.println(newRow);


        Player enemy = playerList.get(playerNum);


        if (enemy == null) {
            System.out.println("new player added");
            playerList.put(playerNum, new Player());
            enemy = playerList.get(playerNum);
            enemy.initEnemyPlayer(newCol, newRow);
            return;
        }


        int lastCol = enemy.getLogicalCol();
        int lastRow = enemy.getLogicalRow();

        int translateX = newCol - lastCol;
        int translateY = newRow - lastRow;

        enemy.move(translateX, translateY);

        enemy.setPlayerCol(newCol);
        enemy.setPlayerRow(newRow);

        if (!camera.isInView(enemy)) {
            enemy.delete();
        } else {
            enemy.draw();
        }

    }


}