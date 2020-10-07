package common.commands;

import common.*;

import java.net.Socket;

/**
 * Команда "ВЫХОДА НЕТ"
 */
public class Exit implements CommandWithoutArg{
    /**
     * Метод для прекращения работы программы
     */
    @Override
    public String execute(String s, Socket socket, String user) {
        if (s != null) {
            System.out.println("\nСервер завершает свою работу.");
            System.exit(0);
        }
        System.out.println("Клиент нас покинул. Продолжу сидеть в одиночестве...");
        return null;
    }
    @Override
    public String getName() {
        return "exit";
    }
    @Override
    public String getInfo() {
        return "exit : завершить программу ";
    }
}
