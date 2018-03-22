package org.academiadecodigo.haltistas.halflifeminus3.Client;

import org.academiadecodigo.haltistas.halflifeminus3.BackGround.Camera;
import org.academiadecodigo.haltistas.halflifeminus3.Controls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {

    private Socket socket;
    private String hostName;
    private int portNumber;
    private PrintWriter printWriter;
    private Player player;
    private Controls controls;
    private Map<Integer, Player> playerList;
    private Camera camera;


    public Client(int portNumber, Player player, Controls controls, Camera camera) {

        this.portNumber = portNumber;
        this.player = player;
        this.controls = controls;
        this.playerList = new HashMap<>();
        this.camera = camera;

    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
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
                    System.out.println(message);
                    printWriter.println(message);

                    controls.resetPressedKey();

                }

                player.bulletsMove();

            }

        } catch (IOException e) {
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

    // 192.168.1.15
    public void moveEnemy(String message) {

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
            enemy.setEnemyCol(newCol);
            enemy.setEnemyRow(newRow);
            enemy.initEnemyPlayer();
            return;
        }


        int lastCol = enemy.getLogicalCol();
        int lastRow = enemy.getLogicalRow();

        int translateX = newCol - lastCol;
        int translateY = newRow - lastRow;

        enemy.move(translateX, translateY);

        enemy.setEnemyCol(newCol);
        enemy.setEnemyRow(newRow);

        if (!camera.isInView(enemy)) {
            enemy.delete();
        } else {
            enemy.draw();
        }

    }


}
