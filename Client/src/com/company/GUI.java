package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JTextField ipInput;
    private JButton connectButton;
    private JButton screenShortButton;
    private JButton appRunning;
    private JButton processRunningButton;
    private JButton keyStrokeButton;
    private JButton editRegistryButton;
    private JButton shutDownButton;
    private JButton exitButton;

    private String ip;
    private SocketFunction socketFunction = new SocketFunction();
    private ShowScreenShort showScreenShort;

    public GUI() throws Exception{
        setContentPane(mainPanel);
        setTitle("Client");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ip = ipInput.getText();
                    socketFunction.startConnection(ip,6666);
                    System.out.println("Connection is started");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
//127.0.0.1
        processRunningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process process = new Process(socketFunction);
            }
        });

        appRunning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App app = new App(socketFunction);
            }
        });

        screenShortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage res = null;
                try {
                    res = socketFunction.screenShort();
                    showScreenShort = new ShowScreenShort(res);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println(res);
            }
        });

        keyStrokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowKeyStroke showKeyStroke = new ShowKeyStroke(socketFunction);
            }
        });

        shutDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketFunction.shutdown();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketFunction.stopConnection();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public static void main (String[] args) throws Exception{
        GUI mainFrame = new GUI();

//        SocketFunction socketFunction = new SocketFunction();
//        socketFunction.startConnection("127.0.0.1",6666);
//        String response = socketFunction.processRunning();
//        System.out.println(response);
    }

}
