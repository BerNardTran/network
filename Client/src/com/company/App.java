package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class App extends JFrame{
    private JTextField appInput;
    private JButton startAppButton;
    private JButton stopAppButton;
    private JPanel mainPanel;
    private SocketFunction socketFunction;
    private String app = "";
    public App(SocketFunction mySocketFunction) {
        socketFunction = mySocketFunction;
        setContentPane(mainPanel);
        setTitle("Client");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        startAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app = appInput.getText();
                try {
                    socketFunction.startApp(app);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        stopAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app = appInput.getText();
                try {
                    socketFunction.endApp(app);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
