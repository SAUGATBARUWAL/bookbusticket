package client;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame {
    private JTextField nameField, destinationField, dateField;
    private JButton bookButton, refreshButton;
    private JTable dataTable;
    private ClientSock clientSock;

    public ClientGUI() {
        // Initialize the ClientSock
        clientSock = new ClientSock();

        // Create form for user input
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("Destination:"));
        destinationField = new JTextField();
        formPanel.add(destinationField);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);
        bookButton = new JButton("Book Ticket");
        formPanel.add(bookButton);

        // Create table to display data
        dataTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(dataTable);

        // Create refresh button
        refreshButton = new JButton("Refresh");

        // Add components to frame
        this.add(formPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(refreshButton, BorderLayout.SOUTH);

        // Add action listeners
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String destination = destinationField.getText();
                String date = dateField.getText();
                clientSock.sendData(name, destination, date);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[][] data = clientSock.receiveData();
                updateTable(data);
            }
        });
    }

    private void updateTable(String[][] data) {
        // Assuming columnNames is an array of column names
        String[] columnNames = {"Name", "Destination", "Date"};
        dataTable.setModel(new DefaultTableModel(data, columnNames));
    }
}
