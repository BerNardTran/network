package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ShowScreenShort extends JFrame{
    private JPanel imagePanel;

    public ShowScreenShort(BufferedImage bufferedImage) {
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        getContentPane().add(label, BorderLayout.CENTER);
        setTitle("Image");
        setSize(bufferedImage.getWidth()/2,bufferedImage.getHeight()/2);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
