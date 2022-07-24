import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServerGui extends JFrame {
    private JButton startServerButton;
    private JPanel mainPanel;
    private JButton stopServerButton;

    private SocketFunction socketFunction = new SocketFunction();
//    private SocketFunction socketFunction;

    ServerGui() {
        setContentPane(mainPanel);
        setTitle("Server");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketFunction.start(6666);
                    System.out.println("Server start at port 6666");
                } catch (IOException | AWTException ex) {
                    ex.printStackTrace();
                } catch (NativeHookException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketFunction.stop();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException, AWTException, NativeHookException, InterruptedException {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        ServerGui serverGui = new ServerGui();
//        SocketFunction socketFunction = new SocketFunction();
//        socketFunction.start(6666);







    }

}
