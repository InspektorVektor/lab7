package common.commands;

import common.Commandable;

import java.net.Socket;


/**
 * Команда "ИНФОРМБЮРО"
 */
public class Info implements Commandable {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Отображение актуальной информации о коллекции
     */
    @Override
    public String execute(String par1, Socket socket, String user) {
        return null;

    }
    @Override
    public String getName() {
        return "info";
    }
    @Override
    public String getInfo() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
