package org.academiadecodigo.haltistas.halflifeminus3.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String hostName;
    private int portNumber;
    private PrintWriter printWriter;
    private Player player;

    public Client(String hostName, int portNumber, Player player) {

        this.hostName = hostName;
        this.portNumber = portNumber;
        this.player = player;

    }

    public void start() {

        try {

            socket = new Socket(hostName, portNumber);

            new Thread(new DataReceiver()).start();

            printWriter = new PrintWriter(socket.getOutputStream());


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

}
