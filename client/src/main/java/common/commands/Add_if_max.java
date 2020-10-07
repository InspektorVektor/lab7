package common.commands;

import common.CommandWithoutArg;

import java.net.Socket;

/**
 * Команда "Добавить-ка ещё!" (но только если человек самый высокий)
 */
public class Add_if_max implements CommandWithoutArg {
    static boolean hasName = false;  // показывает, было ли добавлено имя элемента
    static boolean hasCoordinates = false; // показывает, было ли добавлено поле coordinates
    static boolean hasHairColor = false; //показывает, было ли добавлено поле HairColor
    static boolean hasNationality = false; // показывает, было ли добавлено поле Nationality
    static boolean hasLocation = false; // показывает, было ли добавлено поле Location
    static boolean hasBirthday = false; // показывает, было ли добавлено поле Birthday
    static boolean hasHeight = false; // показывает, было ли добавлено поле Height

    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для добавления новых элементов в коллекцию (но только если человек самый высокий)
     *
     * @param string строка с полями объекта people
     */
    @Override
    public String execute(String string, Socket socket, String user) {
        return null;
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
     * Метод добавления поля Location в элемент
     */
    protected static void addLocation() {
    }


    /**
     * Метод добавления поля Birthday в элемент
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
        return "add_if_max {element} : добавить новый элемент в коллекцию (но только если самый высокий)";
    }
    @Override
    public String getName() {
        return "add_if_max";
    }
}