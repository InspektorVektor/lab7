package common.commands;

import common.Commandable;

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
        return null;
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
