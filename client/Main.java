package client;
import client.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the GUI on the event-dispatching thread
        // SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
                ClientGUI gui = new ClientGUI();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(500, 500);
                gui.setVisible(true);
            // }
        // });
    }
}
