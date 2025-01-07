import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class CaptureServerIpForClient extends JDialog {

    private JTextField serverTextIP = new JTextField("127.0.0.1", 20);  // Text area where user enters IP address of chat server.
    private JButton okButton = new JButton("Ok"); // 'Ok' Button that confirms entered server IP address..
    private String serverIpAddress; // Actual chat server IP address.

    /**
     * Constructor method.
     */
    public CaptureServerIpForClient() {

        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Set IP address of chat server");
        setLayout(new FlowLayout());

        // Customize appearance and add tooltip.
        serverTextIP.setForeground(Color.GRAY);
        serverTextIP.setToolTipText("Please enter the server IP of the chat server");

        // Adds event listener which listens to <Enter> key event.
        serverTextIP.addActionListener((ActionListener) (event) -> {

            JOptionPane.showConfirmDialog(CaptureServerIpForClient.this,
                    "IP address of the chat server: " + serverTextIP.getText(),
                    "Confirm Server IP address", JOptionPane.DEFAULT_OPTION);
            setVisible(false);
            dispose();
        });

        // Adds key event listener that either enables / disables the 'Ok' button.
        serverTextIP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {

                String content = serverTextIP.getText();
                if (!content.equals("")) {
                    okButton.setEnabled(true);
                } else {
                    okButton.setEnabled(false);
                }
            }
        });

        // Adds action listener for the button.
        okButton.addActionListener((ActionListener) (event) -> {

            JOptionPane.showConfirmDialog(CaptureServerIpForClient.this,
                    "IP Address of the chat server: " + serverTextIP.getText(),
                    "Confirm Server IP address", JOptionPane.DEFAULT_OPTION);
            setServerIpAddress(serverTextIP.getText());
            setVisible(false);
            dispose();
        });

        // Create the window where the user will enter the IP address of the chat server.
        add(serverTextIP);
        add(okButton);
        setSize(325, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    /**
     * Getter method.
     *
     * @return  Actual chat server IP address.
     */
    public String getServerIpAddress() {
        return serverIpAddress;
    }

    /**
     * Setter method.
     *
     * @param serverIpAddress Actual chat server IP address.
     */
    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }
}
