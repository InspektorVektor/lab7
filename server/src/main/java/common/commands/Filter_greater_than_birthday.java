package common.commands;

import App.Collection;
import App.Person;
import common.*;
import utility.SQLConnector;

import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Команда "Вывести старых"
 */
public class Filter_greater_than_birthday implements Commandable {
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для вывода всех элементов, значения поля birthday которых больше заданного
     */

    @Override
    public String execute(String s, Socket socket, String user) {
        SQLConnector.loadAllPersons();
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(s);
        } catch (DateTimeParseException e){
            return ("формат не тот");

        }
        if (Collection.getPeople().size() == 0) {
            return ("Коллекция пуста");
        } else {
            for (Person r : Collection.getPeople()) {
                if(r.getBirthday().toLocalDateTime().isAfter(localDateTime))
                    return ("  " + r.toString());
            }
            return "Элементов не найдено";
        }
    }

    @Override
    public String getInfo() {
        return "filter_greater_than_birthday : вывести элементы, значения поля birthday которых больше заданного";
    }

    @Override
    public String getName() {
        return "filter_greater_than_birthday";
    }

}

