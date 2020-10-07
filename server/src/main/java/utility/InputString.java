package utility;

import common.commands.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс - обработчик сигналов с консоли сервера
 */
public class InputString extends Thread {

    /**
     * Бег
     */
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Exit exit = new Exit();
        Help help = new Help();
        while (true) {
            try {
                System.out.print("Введите команду для сервера: ");
                String s = reader.readLine();
                if (s == null | s.trim().equals("")) continue;
                switch (s.trim().toLowerCase()) {
                    case "exit":
                        exit.execute("NotNull",null,null);
                        break;
                    case "help":
                        System.out.println("Список всех доступных команд:\n   " +
                                exit.getInfo() +
                                "\n   " + help.getInfo()
                        );
                        break;
                    default:
                        System.out.println("Неизвестная команда.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            } catch (Exception e) {
                continue;
            }
        }
    }
}