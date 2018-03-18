package org.academiadecodigo.haltistas.halflifeminus3.Server;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingParameterStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Server server;
    private Socket clientConnection;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public ClientHandler(Server server, Socket socket) {

        this.server = server;
        clientConnection = socket;

    }

    @Override
    public void run() {

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            printWriter = new PrintWriter(clientConnection.getOutputStream(), true);

            while (true) {

                String message = bufferedReader.readLine();
                server.broadcast(message, this);

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            close(clientConnection);
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
