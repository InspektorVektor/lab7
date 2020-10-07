package common.commands;

import App.Collection;
import common.*;
import Readers.*;
import utility.SQLConnector;

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
        double height;  SQLConnector.loadAllPersons();
        try { height = Checker.doubleChecker(par1); }
        catch (NullPointerException e) {
            return "должно быть дабл";

        }
        if (!(height > 0)) {
            return "должно быть больше 0";

        }


        if (Collection.getPeople().size() > 0) {
            Collection.getPeople().removeIf(r -> r.getHeight() > height && r.getUser().equals(user));
            SQLConnector.uploadAllPerson();
            return ("Элементы успешно удалёны из коллекции. Вот.");

        } else
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