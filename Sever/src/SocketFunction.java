import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SocketFunction implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static PrintWriter out;
    private BufferedReader in;
    private BufferedOutputStream bufferedOutputStream;

    Runtime runtime = Runtime.getRuntime();

    private KeyStrokeAction keyStrokeAction;
    private Thread thread;
    public ScreenShortAction action = new ScreenShortAction();

    public void start(int port) throws IOException, AWTException, NativeHookException, InterruptedException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());

        String inputLine;
        while ((inputLine = in.readLine()) != "exist") {
            if ("screenShort".equals(inputLine)) {
               BufferedImage bufferedImage = action.screenShort();
               ImageIO.write(bufferedImage,"png",bufferedOutputStream);
            }   

            if ("shutdown".equals(inputLine)) {
                runtime.exec("shutdown -s -t 5");
            }

            if ("keyStrokeStart".equals(inputLine)) {
                keyStrokeAction = new KeyStrokeAction();
                thread = new Thread(keyStrokeAction);

                thread.start();
//                thread.join();
                System.out.println(thread.getName());
            }

            if ("keyStrokeEnd".equals(inputLine)) {
                System.out.println("stop");
                keyStrokeAction.stop();
            }

            if ("getKeyStroke".equals(inputLine)) {
                String keyStroke = keyStrokeAction.getKeyStroke();
                    out.println(keyStroke);
            }

            if (inputLine.contains("startProcess")) {
                String processId = inputLine.split("-")[1];
                Process process = new ProcessBuilder(processId).start();
//                Process process = Runtime.getRuntime().exec(processId);

            }

            if (inputLine.contains("endProcess")) {
                String processId = inputLine.split("-")[1];
                String cmd = "taskkill /F /PID " + processId;
                Runtime.getRuntime().exec(cmd);
            }


            if (inputLine.contains("startApp")) {
                String processId = inputLine.split("-")[1];
                Process process = new ProcessBuilder(processId).start();
//                Process process = Runtime.getRuntime().exec(processId);

            }

            if (inputLine.contains("endApp")) {
                String processId = inputLine.split("-")[1];
                String cmd = "taskkill /F /PID " + processId;
                Runtime.getRuntime().exec(cmd);
            }

            if ("stop".equals(inputLine)) {
                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
            }



//            if ("hello server".equals(inputLine)) {
//                out.println("hello client");
//            }
//            else {
//                out.println("unrecognised greeting");
//            }
        }

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public void sendKey(String key) {
        out.println(key);
    }

    @Override
    public void run() {

    }
}
