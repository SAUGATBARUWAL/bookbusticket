package server;
import java.io.*;
import java.net.*;
import java.util.*;
import shared.*;

public class ServerSock {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public ServerSock() {
        clients = new ArrayList<>();
        try {
            // Create a server socket
            serverSocket = new ServerSocket(6666);
            System.out.println("Server started on port 6666");

            while (true) {
                // Listen for incoming client connections
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                // Create a new ClientHandler for the connected client
                ClientHandler client = new ClientHandler(socket, this);
                clients.add(client);

                // Start the ClientHandler in a new thread
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Ticket ticket) {
        // Send a Ticket to all connected clients
        for (ClientHandler client : clients) {
            client.sendTicket(ticket);
        }
    }
}



