package common.commands;

import App.Collection;
import common.*;
import utility.SQLConnector;

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
        SQLConnector.loadAllPersons();
        return ("\n \nКоллекция представляет собой: " + Collection.getPeople().getClass().getName() + "\n" +
                "В коллекции: " + Collection.getPeople().size() + " элементов" + "\n");

    }

    @Override
    public String getInfo() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }
}
