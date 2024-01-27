package client;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import shared.Ticket;

public class ClientSock {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ClientSock() {
        try {
            // Establish a connection to the server
            socket = new Socket("localhost", 6666);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String name, String destination, String date) {
        try {
            // Create a new Ticket and send it to the server
            Ticket ticket = new Ticket(name, destination, date);
            out.writeObject(ticket);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[][] receiveData() {
        try {
            // Receive an ArrayList<Ticket> from the server
            ArrayList<Ticket> data = (ArrayList<Ticket>) in.readObject();

            // Convert the ArrayList<Ticket> to a 2D String array
            String[][] tableData = new String[data.size()][3];
            for (int i = 0; i < data.size(); i++) {
                Ticket ticket = data.get(i);
                tableData[i][0] = ticket.getName();
                tableData[i][1] = ticket.getDestination();
                tableData[i][2] = ticket.getDate();
            }
            return tableData;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
