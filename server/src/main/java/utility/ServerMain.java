package utility;

import common.*;
import common.commands.*;
import App.Collection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import Readers.*;
import sun.misc.Signal;

/**
 * Главненький
 */
public class ServerMain {
    public static void main(String[] args) throws IOException {
        Signal.handle(new Signal("INT"), sig ->  {
            System.out.println("\n" + "Контрлцешное завершение программы");
            System.exit(0);
        });
        int port;
       ConsoleSourceReader bufferReader = new ConsoleSourceReader();
        boolean serverCreated = false;
        while (!serverCreated) {
            System.out.print("Введите порт:  ");
            try {
                port = Integer.parseInt(bufferReader.getLine());
            } catch (NumberFormatException e){
                System.out.println("Формат неправильный");
                continue;
            }
            serverCreated = CreateServer.create(port);
        }
        System.out.println("Советик: введите help, чтобы увидеть доступные команды.");
        try {
            SQLConnector.ConnectionToDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        while (!CreateServer.server.isClosed()) {
            Socket socket = CreateServer.server.accept();
            new Thread(new NewUser(socket)).start();
            System.out.println("Новое подключение: "+socket.getLocalAddress()+socket.getPort());

        }

    }


}

