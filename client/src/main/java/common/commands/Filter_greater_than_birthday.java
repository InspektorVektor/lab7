package common.commands;

import common.Commandable;

import java.net.Socket;

/**
 * Команда "Вывести старых"
 */
public class Filter_greater_than_birthday implements Commandable {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для вывода всех элементов, значения поля birthday которых больше заданного
     */

    @Override
    public String execute(String s, Socket socket, String user) {
        return null;
    }

    @Override
    public String getInfo() {
        return "filter_greater_than_birthday : вывести элементы, значения поля birthday которых больше заданного";
    }
    @Override
    public String getName() {
        return "filter_greater_than_birthday";
    }

}

