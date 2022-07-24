package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShowKeyStroke extends JFrame{
    private JPanel ShowKeyStroke;
    private JButton startButton;
    private JButton stopButton;
    private JTextField keyStrokeField;
    private JButton getKeyButton;
    private JButton clearKeyButton;

    private SocketFunction socketFunction;

    private String key = "";

    public ShowKeyStroke (SocketFunction mySocketFunction) {
        socketFunction = mySocketFunction;
        setContentPane(ShowKeyStroke);
        setTitle("Client");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketFunction.keyStrokeStart();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketFunction.keyStrokeEnd();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        getKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String key = socketFunction.getKeyStroke();
                    keyStrokeField.setText(key);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        clearKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyStrokeField.setText("");
            }
        });
    }

}
