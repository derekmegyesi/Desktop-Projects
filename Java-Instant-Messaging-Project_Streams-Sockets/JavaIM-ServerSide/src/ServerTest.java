import javax.swing.JFrame;

/**
 *
 * This project is a simple Java (Swing based) Instant Messenger (IM) application that utilizes Streams and Sockets.
 *
 * It consists of both a Server side and a Client side application, each of which are installed on different machines.
 * Up to 100 client session can be connected at any time. The default port is 6789.
 *
 * The client connected chat session will continue until the client types 'END' in the session.
 *
 */

public class ServerTest {

    /**
     * Main (driver) for the Server project.
     */
    public static void main(String[] args) {

        // Prompt the user for the port of the chat server.
        SetPortForServer setPortForServer = new SetPortForServer();

        // Commence the chat server session.
        Server chatServer = new Server(setPortForServer.getPortNumber());
        chatServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatServer.startRunning();
    }
}