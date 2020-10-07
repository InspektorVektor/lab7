package common.commands;

import common.Commandable;

import java.net.Socket;

/**
 * Команда "УДОЛИ!"
 */
public class Remove_greater implements Commandable {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * метод удаления элемента коллекции по его id
     */
    @Override
    public String execute(String par1, Socket socket, String user) {
        return "пусто";
    }

    @Override
    public String getInfo() {
        return "remove_greater : удалить элемент из коллекции по его id";
    }
    @Override
    public String getName() {
        return "remove_greater";
    }
}