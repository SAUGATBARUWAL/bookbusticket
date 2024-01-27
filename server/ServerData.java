package server;
import java.sql.*;
import shared.*;

public class ServerData {
    private Connection conn;

    public ServerData() {
        try {
            // Connect to the MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmybus", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTicket(Ticket ticket) {
        try {
            // Prepare a SQL statement
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tickets (name, destination, date) VALUES (?, ?, ?)");

            // Set the values
            stmt.setString(1, ticket.getName());
            stmt.setString(2, ticket.getDestination());
            stmt.setString(3, ticket.getDate());

            // Execute the statement
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket[] getAllTickets() {
        try {
            // Prepare a SQL statement
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tickets");

            // Execute the statement and get the result
            ResultSet rs = stmt.executeQuery();

            // Convert the result to an array of Tickets
            Ticket[] tickets = new Ticket[100]; // Assuming there are at most 100 tickets
            int i = 0;
            while (rs.next()) {
                tickets[i] = new Ticket(rs.getString("name"), rs.getString("destination"), rs.getString("date"));
                i++;
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
