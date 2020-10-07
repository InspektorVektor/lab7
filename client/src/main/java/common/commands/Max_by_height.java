package common.commands;

import common.CommandWithoutArg;

import java.net.Socket;

/**
 * Команда "МАКСИМАЛЬНЫЙ"
 */
public class Max_by_height implements CommandWithoutArg {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * метод для вывода любого объект из коллекции, значение поля height которого является максимальным
     */
    @Override
    public String execute(String par1, Socket socket, String user) {
        return null;
    }

    @Override
    public String getInfo() {
        return "max_by_height: вывести любой объект из коллекции, значение поля height которого является максимальным";
    }
    @Override
    public String getName() {
        return "max_by_height";
    }
}