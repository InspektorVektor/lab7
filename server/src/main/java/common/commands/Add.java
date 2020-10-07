package common.commands;

import common.*;
import App.*;
import utility.SQLConnector;

import java.net.Socket;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Команда "Добавить-ка ещё!"
 */
public class Add implements CommandWithoutArg {
    static boolean hasName = false;  // показывает, было ли добавлено имя элемента
    static boolean  hasCoordinates = false; // показывает, было ли добавлено поле coordinates
    static boolean  hasHairColor = false; //показывает, было ли добавлено поле HairColor
    static boolean hasNationality = false; // показывает, было ли добавлено поле Nationality
    static boolean hasLocation = false; // показывает, было ли добавлено поле Location
    static boolean hasBirthday = false; // показывает, было ли добавлено поле Birthday
    static boolean hasHeight = false; // показывает, было ли добавлено поле Height

    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для добавления новых элементов в коллекцию
     *
     * @param string строка с полями объекта people
     */
    @Override
    public String execute(String string, Socket socket, String user) {
        SQLConnector.loadAllPersons();
        String[] s = string.split(" ");
        Person person = new Person();
        person.setId(SQLConnector.getNewId());
        person.setCreationDate(Timestamp.from(Instant.now()));
        person.setName(s[0]);
        person.setCoordinates(new Coordinates(Double.parseDouble(s[1]), Double.parseDouble(s[2])));
        if (!s[3].equals("null")) person.setLocation(new Location(Float.parseFloat(s[3]), Float.parseFloat(s[4]), Integer.parseInt(s[5]), s[6]));
        if (!s[7].equals("null")) person.setHeight(Double.parseDouble(s[7]));
        person.setBirthday(Timestamp.valueOf(LocalDateTime.parse(s[8])));
        person.setHairColor(Color.getValue(s[9]));
        person.setNationality(Country.getValue(s[10]));
        person.setUser(user);
        Collection.getPeople().add(person);
        SQLConnector.uploadAllPerson();
        return ("Объект был добавлен в коллекцию.");
    }

    /**
     * Метод для создания строки
     *
     * @return строка
     */
    public static String makeString() {
        return null;
    }

    /**
     * Метод добавления имени в элемент
     */
    protected static void addName() {
    }

    /**
     * Метод добавления координат в элемент
     */
    protected static void addCoordinates() {
    }

    /**
     * Метод добавления поля location в элемент
     */
    protected static void addLocation() {
    }


    /**
     * Метод добавления поля birthday в элемент
     */
    protected static void addBirthday() {
    }

    /**
     * Метод добавления поля Height в элемент
     */
    protected static void addHeight() {
    }
    /**
     * Метод добавления поля HairColor в элемент
     */
    protected static void addHairColor() {
    }
    /**
     * Метод добавления поля Nationality в элемент
     */
    protected static void addNationality() {
    }


    @Override
    public String getInfo() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}