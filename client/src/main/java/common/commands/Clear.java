package common.commands;

import common.CommandWithoutArg;

import java.net.Socket;

/**
 * Команда "ЧИСТИЛЬЩИК "
 */
public class Clear implements CommandWithoutArg {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для очистки коллекции, подаваемой на вход
     */
    @Override
    public String execute(String par1, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return "clear";
    }
    @Override
    public String getInfo() {
        return "clear : очистить коллекцию";
    }
}
