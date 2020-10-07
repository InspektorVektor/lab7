package utility;

import common.CommandWithoutArg;
import common.Commandable;
import common.commands.Add;
import common.commands.Add_if_max;
import common.commands.ExecuteScript;
import common.commands.History;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс - сортировщик
 */
public class Invoker {
    public static boolean isLogged = false;
    private static Map<String, Commandable> commands = new TreeMap<>();


    public static void regist(Commandable ... commandables) {
        for (Commandable command : commandables) {
            commands.put(command.getName(), command);
        }
    }

    /**
     * Получить коллекцию команд
     *
     * @return map
     */
    public static Map<String, Commandable> getCommandCollection() {
        return commands;
    }

    /**
     * Исполнить
     *
     * @param s строчечка
     */
    public static Map<Commandable, String> execute(String s) {
        if (s == null) return null;
        try {
            Map<Commandable, String> commandStringMap = new HashMap<>();
            String name[] = s.split(" ", 2);
            Commandable command = commands.get(name[0].toLowerCase());
            if (s.equals("")) {
            } else if (name[0].toLowerCase().equals("help")) {
                History.addInArray(name[0]);
                command.execute("", null, null);
            } else if (name[0].toLowerCase().equals("history")) {
                command.execute("", null, null);
                History.addInArray(name[0]);
            } else if (name[0].toLowerCase().equals("execute_script")) {
                History.addInArray(name[0]);
                new ExecuteScript().execute(name[1]);
            } else if (name[0].toLowerCase().equals("add")) {
                History.addInArray(name[0]);
                String string = Add.makeString();
                commandStringMap.put(command, string);
                return commandStringMap;
            } else if (name[0].toLowerCase().equals("add_if_max")) {
                History.addInArray(name[0]);
                String string = Add_if_max.makeString();
                commandStringMap.put(command, string);
                return commandStringMap;
            } else if (name[0].toLowerCase().equals("exit")) {
                History.addInArray(name[0]);
                commandStringMap.put(command, null);
                command.execute("", null, null);
            } else if (name[0].toLowerCase().equals("update")) {
                History.addInArray(name[0]);
                String ID;
                if (name.length == 2) ID = name[1].trim();
                else ID = null;
                if (ID == null || ID.equals("")) {
                    System.out.println("Вы не ввели ID для апдейтинга.");
                    return null;
                }
                commandStringMap.put(command, ID);
                ClientSender.send(commandStringMap);
                String a = (String) ClientReceiver.receive();
                System.out.println(a);
                if (a.startsWith("Состояние элемента сейчас:")) {
                    String s1 = Add.makeString();
                    ClientSender.send(s1);
                    System.out.println(ClientReceiver.receive());
                }
            } else {
                if (commands.get(name[0]) == null) {
                    System.out.println("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
                } else {
                    try {
                        CommandWithoutArg commandWithoutArg = (CommandWithoutArg) commands.get(name[0]);
                        try {
                            String st = name[1];
                            System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        } catch (Exception e) {
                            commandStringMap.put(commands.get(name[0]), null);
                            return commandStringMap;
                        }
                    } catch (Exception e) {
                        try {
                            String st = name[2];
                            System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        } catch (IndexOutOfBoundsException e1) {
                            try {
                                commandStringMap.put(commands.get(name[0]), name[1]);
                                return commandStringMap;
                            } catch (IndexOutOfBoundsException e2) {
                                System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                            }
                        }
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
            return null;
        } catch (InterruptedException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


