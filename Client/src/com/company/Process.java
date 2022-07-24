package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Process extends JFrame{
    private JTextField processIdInput;
    private JButton startProcessButton;
    private JButton stopProcessButton;
    private JPanel processPanel;

    private SocketFunction socketFunction;

    private String processId = "";

    public Process(SocketFunction mySocketFunction) {
        socketFunction = mySocketFunction;
        setContentPane(processPanel);
        setTitle("Client");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        startProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String processId = processIdInput.getText();
                try {
                    socketFunction.startProcess(processId);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        stopProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String processId = processIdInput.getText();
                try {
                    socketFunction.endProcess(processId);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
