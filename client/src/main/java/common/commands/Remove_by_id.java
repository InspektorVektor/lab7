package common.commands;

import common.Commandable;

import java.net.Socket;

/**
 * Команда "УДОЛИ!"
 */
public class Remove_by_id implements Commandable {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * метод удаления элемента коллекции по его id
     */
    @Override
    public String execute(String s, Socket socket, String user) {
        return null;
    }

    @Override
    public String getInfo() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
    @Override
    public String getName() {
        return "remove_by_id";
    }
}
