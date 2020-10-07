package common.commands;

import App.Collection;
import common.*;
import Readers.*;
import App.Person;
import utility.SQLConnector;

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
        int removeId = Checker.intChecker(s);  SQLConnector.loadAllPersons();
        Person r = Collection.searchById(removeId);
        if (r == null) {
            return ("нет такого");
        }
        if (r.getUser().equals(user)) {
            Collection.getPeople().remove(r);
            SQLConnector.uploadAllPerson();
            return ("Элемент успешно удалён из коллекции. Вот.");
        }
        else return "Элемент не принадлежит вам.";
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
