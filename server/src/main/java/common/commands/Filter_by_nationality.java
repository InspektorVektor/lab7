package common.commands;

import App.Collection;
import App.Country;
import App.Person;
import common.*;
import utility.SQLConnector;

import java.net.Socket;

/**
 * Команда "Вывести вле элементы определённой национальности"
 */
public class Filter_by_nationality implements Commandable {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод "выполнить"
     */
    @Override
    public String execute(String s, Socket socket, String user) {
        SQLConnector.loadAllPersons();
        if (Country.getValue(s) == null) {
            return ("такого типа нет братик");
        }
        if (Collection.getPeople().size() == 0) {
            return ("Коллекция пуста, милорд");
        } else {
            for (Person r : Collection.getPeople()) {
                if(r.getNationality().str.equals(s))
                    return ("  " + r.toString());
            }
             return "Элементов не найдено";
        }
    }


    @Override
    public String getInfo() {
        return "filter_by_nationality : вывести элементы, значения поля nationality которых равно заданному";
    }

    @Override
    public String getName() {
        return "filter_by_nationality";
    }

}
