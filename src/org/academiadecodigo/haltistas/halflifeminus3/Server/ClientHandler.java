package org.academiadecodigo.haltistas.halflifeminus3.Server;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingParameterStyle;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Bullet;
import org.academiadecodigo.haltistas.halflifeminus3.Client.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Server server;
    private Socket clientConnection;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private EventCoordinator eventCoordinator;


    public ClientHandler(Server server, Socket socket) {

        server.getPlayerList().add(new Player());
        this.server = server;
        this.clientConnection = socket;
        this.eventCoordinator = new EventCoordinator(server, this);

    }

    @Override
    public void run() {

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            printWriter = new PrintWriter(clientConnection.getOutputStream(), true);

            while (true) {

                String message = bufferedReader.readLine();

              //  System.out.println(message);

                stringDecoder(message);
                //eventCoordinator.events();
                System.out.println(message);
                server.broadcast(message, this);

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            close(clientConnection);
        }

    }

    public void stringDecoder(String clientMessage) {
        String[] messageData = clientMessage.split(" ");

        switch (messageData[0]) {

            case "M":
                int playerNum = Integer.parseInt(messageData[1]);
                int logicalCol = Integer.parseInt(messageData[2]);
                int logicalRow = Integer.parseInt(messageData[3]);

                server.getPlayerList().get(playerNum).setPlayerCol(logicalCol);
                server.getPlayerList().get(playerNum).setPlayerRow(logicalRow);
                break;

            case "B":
                double initialX = Double.parseDouble(messageData[1]);
                double initialY = Double.parseDouble(messageData[2]);
                double finalX = Double.parseDouble(messageData[3]);
                double finalY = Double.parseDouble(messageData[4]);


                server.getBulletList().add(new Bullet(initialX, initialY, finalX, finalY));
                break;
            default:
                System.out.println("String decoder error");
                break;

        }
    }

    public void sendMessage(String message) {
        printWriter.println(message);
    }

    private void close(Socket clientConnection) {
        try {
            clientConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
