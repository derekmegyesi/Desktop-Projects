import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SetPortForServer extends JDialog {

    private JTextField portTextNumber = new JTextField("6789", 10);  // Text area where user enters port # of server.
    private JButton okButton = new JButton("Ok"); // 'Ok' Button that confirms entered port #.
    private String portNumber; // Actual chat server port #.
    private String serverIpAddress; // Actual chat server IP address (this machine).

    /**
     * Constructor method.
     */
    public SetPortForServer() {

        this.serverIpAddress = getServerIpAddress();

        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Set Port # of chat server");
        setLayout(new FlowLayout());

        // Customize appearance and add tooltip.
        portTextNumber.setForeground(Color.GRAY);
        portTextNumber.setToolTipText("Please enter the Port # of the chat server");

        // Adds event listener which listens to <Enter> key event.
        portTextNumber.addActionListener((ActionListener) (event) -> {

            JOptionPane.showConfirmDialog(SetPortForServer.this,
                    "Server IP address and Port # of the chat server: " + serverIpAddress + ":" + portTextNumber.getText(),
                    "Confirm Server IP address and Port #", JOptionPane.DEFAULT_OPTION);

            setPortNumber(portTextNumber.getText());
            setVisible(false);
            dispose();
        });

        // Adds key event listener that either enables / disables the 'Ok' button.
        portTextNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {

                String content = portTextNumber.getText();
                if (!content.equals("")) {
                    okButton.setEnabled(true);
                } else {
                    okButton.setEnabled(false);
                }
            }
        });

        // Adds action listener for the button.
        okButton.addActionListener((ActionListener) (event) -> {

            JOptionPane.showConfirmDialog(SetPortForServer.this,
                    "Server IP address and Port # of the chat server: " + serverIpAddress + ":" + portTextNumber.getText(),
                    "Confirm Server IP address and Port #", JOptionPane.DEFAULT_OPTION);
            setPortNumber(portTextNumber.getText());
            setVisible(false);
            dispose();
        });

        // Create the window where the user will enter the port number of the chat server.
        add(portTextNumber);
        add(okButton);
        setSize(250, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    /**
     * Getter method.
     *
     * @return  Actual chat server port #.
     */
    public String getPortNumber() {
        return portNumber;
    }

    /**
     * Setter method.
     *
     * @param portNumber Actual chat server port #.
     */
    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * Private method that returns the IP Address of the machine that the server (this machine) is running on.
     *
     * @return  Host IP address of localhost machine.
     */
    private String getServerIpAddress() {

        InetAddress ip;
        String hostIpAddress = "127.0.0.1";

        try {
            ip = InetAddress.getLocalHost();
            hostIpAddress = ip.getHostAddress();

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return hostIpAddress;
    }
}
