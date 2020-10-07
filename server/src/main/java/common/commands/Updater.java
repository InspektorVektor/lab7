package common.commands;

import common.*;
import Readers.*;
import App.*;
import utility.*;

import java.net.Socket;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Команда  "ПРАВКИ!"
 */
public class Updater implements Commandable {
    static boolean changedName = false;  // показывает, было ли добавлено имя элемента
    static boolean  changedCoordinates = false; // показывает, было ли добавлено поле coordinates
    static boolean  changedHairColor = false; //показывает, было ли добавлено поле HairColor
    static boolean changedNationality = false; // показывает, было ли добавлено поле Nationality
    static boolean changedLocation = false; // показывает, было ли добавлено поле Location
    static boolean changedBirthday = false; // показывает, было ли добавлено поле Birthday
    static boolean changedHeight = false; // показывает, было ли добавлено поле Height
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для изменения значений элемента по id
     */
    @Override
    public String execute(String s, Socket socket,String user) {
        SQLConnector.loadAllPersons();
        s = s.trim();
        Integer id = Checker.intChecker(s);
        Person r = Collection.searchById(id);
        if (r == null) {
            return ("похоже элемента с таким айди не существует");

        }
        if (!r.getUser().equals(user)) return "Элемент не принадлежит вам.";
        new ServerSender().send(socket,"Состояние элемента сейчас: " + r.toString(), 0);
        Person person = new Person();
        person.setCreationDate(r.getCreationDate());
        String s2 = (String) new ServerReceiver().receive(socket);
        String[] arrayOfStrings = s2.split(" ");
        person.setId(id);
        person.setUser(user);
        person.setName(arrayOfStrings[0]);
        person.setCoordinates(new Coordinates(Double.parseDouble(arrayOfStrings[1]), Double.parseDouble(arrayOfStrings[2])));
        if (!arrayOfStrings[3].equals("null"))
            person.setLocation(new Location(Float.parseFloat(arrayOfStrings[3]), Float.parseFloat(arrayOfStrings[4]), Integer.parseInt(arrayOfStrings[5]), arrayOfStrings[6]));
        if (!arrayOfStrings[3].equals("null"))
        person.setHeight(Double.parseDouble(arrayOfStrings[7]));
        person.setBirthday(Timestamp.valueOf(LocalDateTime.parse(arrayOfStrings[8])));
        person.setHairColor(Color.getValue(arrayOfStrings[9]));
        person.setNationality(Country.getValue(arrayOfStrings[10]));
        Person person1 = Collection.searchById(id);
        Collection.getPeople().remove(person1);
        Collection.getPeople().add(person);
        SQLConnector.uploadAllPerson();
        return ("\n" + "Вы достигли успеха в замене элемента по айди!");
    }

    @Override
    public String getInfo() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному.";
    }

    @Override
    public String getName() {
        return "update";
    }
}
