package utility;

import common.Commandable;
import common.commands.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Главненький
 */
public class ClientMain {

    public static boolean work = true; // переменная, отвечающая за выход из программы. Как только она станет false, программа завершается
    public static BufferedReader reader = null;
    public static int port;
    public static InetAddress address;


    /**
     * psvm
     *
     * @param args аргументики
     */
    public static void main(String[] args) {
Invoker.regist(new Add(),new Add_if_max(),new Clear(),new ExecuteScript(),new Exit(),new Filter_by_nationality(),new Filter_greater_than_birthday(),new Help(),new History(),new Info(),new Login(),new Max_by_height(),new Register(),new Remove_by_id(),new Remove_greater(),new Show(),new Updater());
        reader = new BufferedReader(new InputStreamReader(System.in));
        while(!ClientSender.serverisconnected) {
            try {
                System.out.print("Введите хост сервера\n~ ");
                ClientSender.host = reader.readLine();
                System.out.print("Введите порт сервера\n~ ");
                int port = Integer.parseInt(reader.readLine());
                ClientSender.port = port;
                ClientSender.reconnect();
            } catch (Exception e) {
                System.out.println("Введены некорректные данные");
            }
        }
        System.out.println("Клиент запущен, подключась к серверу.");
        while (true) {
            while (!Invoker.isLogged){
                try {
                    if (!ClientSender.serverisconnected) ClientSender.reconnect();
                    System.out.println("Введите login,чтоб авторизоваться | Введите register,чтоб зарегистрироваться | Доступ к другим командам ограничен.");
                    System.out.print("$ ");
                    String s = reader.readLine();
                    if (s.equals(""));
                    else {
                        Map<Commandable, String> commandparamMap = Invoker.execute(s);
                        if (commandparamMap != null) {
                            if (commandparamMap.entrySet().iterator().next().getKey().getInfo().equals("login") || commandparamMap.entrySet().iterator().next().getKey().getInfo().equals("register")) {
                                ClientSender.send(commandparamMap);
                                try {
                                    String a = (String) ClientReceiver.receive();
                                    if (a.equals("true")) {
                                        System.out.println("Вы успешно авторизованы.");
                                        Invoker.isLogged = true;
                                    }else System.out.println(a);
                                } catch (SocketTimeoutException e) {
                                    System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else System.out.println("Другие команды запрещены,авторизуйтесь.");
                    }
                } catch (InterruptedException | ClassNotFoundException | SocketException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            while (ClientSender.serverisconnected) {
                try {
                    System.out.println("Введите команду для отправки на сервер: ");
                    System.out.print("$ ");
                    String s = reader.readLine();
                    Map<Commandable, String> commandparamMap = Invoker.execute(s);
                    if (commandparamMap != null) {
                        ClientSender.send(commandparamMap);
                        try {
                            System.out.println(ClientReceiver.receive());
                        } catch (SocketTimeoutException e) {
                            System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (NoSuchElementException | ClassNotFoundException | InterruptedException | SocketException e) {
                    System.out.println("Программа прервана пользователем.");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

