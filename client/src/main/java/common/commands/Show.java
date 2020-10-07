package common.commands;

import common.CommandWithoutArg;

import java.net.Socket;

/**
 * Команда "ГЛЯДИ! "
 */
public class Show implements CommandWithoutArg {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для отображения всех элементов коллекции
     */
    @Override
    public String execute(String S, Socket socket, String user) {
        return null;
    }

    @Override
    public String getInfo() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    @Override
    public String getName() {
        return "show";
    }
}
