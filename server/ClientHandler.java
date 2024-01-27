package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.Ticket;


public class ClientHandler implements Runnable {
    private Socket socket;
    private ServerSock serverSock;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket, ServerSock serverSock) {
        this.socket = socket;
        this.serverSock = serverSock;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                // Receive a Ticket from the client
                Ticket ticket = (Ticket) in.readObject();

                // Broadcast the received ticket to all connected clients
                serverSock.broadcast(ticket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendTicket(Ticket ticket) {
        try {
            // Send a ticket to the client
            out.writeObject(ticket);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
