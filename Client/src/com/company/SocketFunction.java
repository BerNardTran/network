package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class SocketFunction {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public BufferedReader getIn() {
        return in;
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void startProcess(String processId) throws IOException {
        out.println("startProcess-"+processId);
    }

    public void endProcess(String processId) throws IOException {
        out.println("endProcess-"+processId);
    }

    public void startApp(String processId) throws IOException {
        out.println("startApp-"+processId);
    }

    public void endApp(String processId) throws IOException {
        out.println("endApp-"+processId);
    }

    public BufferedImage screenShort() throws IOException {
        out.println("screenShort");
        InputStream inputStream = clientSocket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
        return bufferedImage;
    }

    public void keyStrokeStart() throws IOException {
        out.println("keyStrokeStart");
    }

    public void keyStrokeEnd() throws IOException {
        out.println("keyStrokeEnd");
    }

    public String getKeyStroke() throws IOException {
        out.println("getKeyStroke");
        String resp = in.readLine();
        return resp;
    }

    public String shutdown() throws IOException {
        out.println("shutdown");
        String resp = in.readLine();
        return resp;
    }
    public void stopConnection() throws IOException {
        out.println("exist");
        in.close();
        out.close();
        clientSocket.close();
    }
}
