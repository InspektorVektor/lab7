package common.commands;

import App.Collection;
import App.Person;
import common.*;
import utility.SQLConnector;

import java.net.Socket;
import java.util.HashSet;

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
        SQLConnector.loadAllPersons();
        HashSet<Person> peopleToSave = new HashSet<>();
        Collection.getPeople().stream().filter(x->!(x.getUser().equals(user))).forEach(peopleToSave::add);
        Collection.setPeople(peopleToSave);
        SQLConnector.uploadAllPerson();
        return ("\n \nКоллекция была оwчищена от ваших обьектов, как картошечка для супчика \n \n");
    }


    @Override
    public String getInfo() {
        return "clear : очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
