package common;

import java.io.Serializable;
import java.net.Socket;

/**
 * Интерфейс Commandable
 */
public interface Commandable extends Serializable {
    /**
     * Экзекьюте
     *
     * @param par1 Входная строка
     */
     String execute(String par1, Socket socket, String user);

    /**
     * Получить немножко информации о команде
     *
     * @return строчечка информации
     */
    String getName();
    String getInfo();
}