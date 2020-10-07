package common.commands;

import common.Commandable;

import java.net.Socket;

/**
 * Команда  "ПРАВКИ!"
 */
public class Updater implements Commandable {
    static boolean changedName = false;  // показывает, было ли добавлено имя элемента
    static boolean changedCoordinates = false; // показывает, было ли добавлено поле coordinates
    static boolean changedHairColor = false; //показывает, было ли добавлено поле HairColor
    static boolean changedNationality = false; // показывает, было ли добавлено поле Nationality
    static boolean changedLocation = false; // показывает, было ли добавлено поле Location
    static boolean changedBirthday = false; // показывает, было ли добавлено поле Birthday
    static boolean changedHeight = false; // показывает, было ли добавлено поле Height
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Метод для изменения значений элемента по id
     */
    @Override
    public String execute(String s, Socket socket, String user) {
        return null;
    }
    @Override
    public String getName() {
        return "update";
    }
    @Override
    public String getInfo() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному.";
    }
}
