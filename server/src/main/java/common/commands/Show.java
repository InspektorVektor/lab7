package common.commands;

import App.Collection;
import common.*;
import App.Person;
import utility.SQLConnector;

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
    public String execute(String S, Socket socket,String user) {
        SQLConnector.loadAllPersons();
        if (Collection.getPeople().size() == 0) {
            return ("\n\nКоллекция пуста, милорд\n\n");
        } else {
            String string = "";
            for (Person r : Collection.getPeople()) {
                string += "\n" + r.toString() + "\n";
            }
            return (string);

        }
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
