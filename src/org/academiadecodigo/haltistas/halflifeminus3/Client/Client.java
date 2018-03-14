package org.academiadecodigo.haltistas.halflifeminus3.Client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

    }


    private Socket connection;



    public Client(int port){

        try {
            connection = new Socket("localhost", port);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }


    }








}
