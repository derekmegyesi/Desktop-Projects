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

public class ClientTest {

    /**
     * Main (driver) for the Client project.
     */
    public static void main(String[] args) {

        // Prompt the user for the IP address of the chat server.
        CaptureServerIpForClient captureServerIpForClient = new CaptureServerIpForClient();

        // Prompt the user for the port of the chat server.
        CapturePortForClient capturePortForClient = new CapturePortForClient(
                captureServerIpForClient.getServerIpAddress());

        // Commence the chat client session.
        Client chatClient = new Client(
                captureServerIpForClient.getServerIpAddress(),
                capturePortForClient.getPortNumber());

        chatClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatClient.startRunning();
    }
}