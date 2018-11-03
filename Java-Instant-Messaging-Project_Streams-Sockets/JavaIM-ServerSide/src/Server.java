import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

    private JTextField userText; // Area where message is sent before I send it.
    private JTextArea chatWindow;  // Conversation itself (both mine and others).
    private ObjectOutputStream output; // Output stream.
    private ObjectInputStream input; // Input stream.
    private ServerSocket server; // Server connection config.
    private int portNumber; // Port Number.
    private Socket connection; // The socket connection.

    /**
     * Constructor method.
     *
     * @param port Port Number.
     */
    public Server(String port) {

        // Setup the Server side of the chat server.
        setTitle("Instant Messenger Project - Server side");
        this.portNumber = Integer.parseInt(port);
        userText = new JTextField();
        userText.setEditable(false); // default case.
        userText.addActionListener(
                event -> {
                    sendMessage(event.getActionCommand());
                    userText.setText("");
                }
        );
        // Create chat-window where all the messages get displayed.
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(325, 150);
        setVisible(true);
    }

    /**
     * Set up and run the server.
     */
    public void startRunning() {
        try {
            // Port 6789 is a dummy port for testing, which can be changed.
            // The 100 is the maximum people waiting to connect.
            server = new ServerSocket(portNumber, 100);

            while (true) {
                try {
                    // Connect and start the conversation.
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                } catch (EOFException eofException) {
                    showMessage("\n Server ended the connection! ");
                } finally {
                    closeStream();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Wait for connection, then create socket and display connection information.
     *
     * @throws IOException If the unable to create socket connection.
     */
    private void waitForConnection() throws IOException {
        showMessage("Waiting for someone to connect... \n");
        connection = server.accept();
        showMessage("Now connected to " + connection.getInetAddress().getHostName());
    }

    /**
     * Get stream to send and receive data.
     *
     * @throws IOException If the unable to create the I/O streams.
     */
    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now setup. \n");
    }

    /**
     * During the chat conversation.
     *
     * @throws IOException If the unable to parse data.
     */
    private void whileChatting() throws IOException {
        String message = "You are now connected.";
        sendMessage(message);

        ableToType(true); // Allow client (user) to type into the text box (public display).
        do {
            try {
                message = (String) input.readObject();
                showMessage("\n" + message);
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("\n Cannot parse data that the user sent.");
            }
            // Have a conversation until user ends the chat by typing 'END'.
        } while (!message.equals("CLIENT - END"));
    }

    /**
     * Close streams and sockets after done chatting.
     */
    private void closeStream() {
        showMessage("\n Closing connections... \n");
        ableToType(false); // Prevent the user from entering data.
        try {
            output.close(); // Closes the output path to the client.
            input.close(); // Closes the input path to the server, from the client.
            connection.close(); //Closes the connection between the server and the client.
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Send a message to the client.
     *
     * @param message Message to be sent to the client (assumes socket connection is established).
     */
    private void sendMessage(String message) {
        try {
            output.writeObject("SERVER - " + message);
            output.flush();
            showMessage("\nSERVER - " + message);
        } catch (IOException ioException) {
            chatWindow.append("\n Error.  Unable to send message.  Please retry message.");
        }
    }

    /**
     * Updates chat window.
     *
     * @param text Text that is appended to the chat window.
     */
    private void showMessage(final String text) {
        SwingUtilities.invokeLater(
                () -> chatWindow.append(text)
        );
    }

    /**
     * Let the user enter data into their text box.
     *
     * @param typeFlag Boolean flag that either prevents (or allows) the user to enter data.
     */
    private void ableToType(final boolean typeFlag) {
        SwingUtilities.invokeLater(
                () -> userText.setEditable(typeFlag)
        );
    }
}

