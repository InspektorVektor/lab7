package utility;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
/**
 * Отдаватель
 */
public class ClientSender {
    public static String host;
    public static int port;
    public static Boolean serverisconnected = false;

    public static void send(Object o) throws IOException, ClassNotFoundException, InterruptedException {
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ClientReceiver.socket.getOutputStream());
            objectOutputStream.writeObject(o);

        } catch (IOException e) {
            serverisconnected = false;
            ClientSender.reconnect();
        }
    }

    public static void reconnect() throws InterruptedException, IOException {
        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(3000);
            serverisconnected = true;
            ClientReceiver.socket = socket;
            socket.setSoTimeout(0);

        } catch (ConnectException e) {
            System.out.println("Возникла некоторая ошибка,пробую переподключится.");
            Thread.sleep(2000);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}

